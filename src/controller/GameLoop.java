package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import model.*;
import view.View;

import java.io.IOException;
import java.util.ArrayList;


public class GameLoop {

    private Canvas canvas;
    public Integer enemiesNr;
    public Integer spawnedEnemies;
    public long enemiesSpawnTime;
    private long lastSpawn;
    private long lastHit;
    private long hitRateTime;
    private ArrayList<Enemy> enemies;
    private ArrayList<Tower> towers;
    private ArrayList<Laser> lasers;
    private Model model;
    private Map map;
    private boolean paused;
    private int killedEnemies;
    private int gamePoints;
    private GameController controller;

    Object monitor = new Object();
    private AnimationTimer animationTimer;


    public Object getMonitor() {
        return monitor;
    }

    public GameLoop(Canvas mainCanvas, Model m, GameController gameController) {
        controller = gameController;
        model = m;
        map = model.getMap();
        canvas = mainCanvas;
        enemiesNr = Map.getEnemiesNr();
        enemiesSpawnTime = Map.getEnemiesSpawnTime();
        spawnedEnemies = 0;
        killedEnemies = 0;
        hitRateTime = Tower.getHitRateTime();
        gamePoints = 0;
        Model.currentPlayer.setCoins(Model.currentPlayer.getBasicCoins());
        paused = false;
        lastSpawn = System.nanoTime();
        lastHit = System.nanoTime();
        enemies = new ArrayList<>();
        towers = model.getTowerList();
        lasers = new ArrayList<>();
        for (int i = 0; i < enemiesNr; i++)
            enemies.add(new Enemy(map.getStartXPosition(), map.getStartYPosition()));
    }

    public void setPaused(boolean paused) {
        if (paused)
            animationTimer.stop();
        else
            animationTimer.start();
    }

    public void startGame() {
        Model.currentPlayer.setCoins(Model.currentPlayer.getBasicCoins());
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                synchronized (monitor) {
                    while (paused)
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
                spawnEnemy(currentNanoTime);
                enemyPhysics();
                laserPhysic();
                calculateEnemiesDamage();
                calculateCastleDamage();
                updateView();
                if (killedEnemies == enemiesNr)
                    gameWon();
            }
        };
        animationTimer.start();
    }


    public int getGamePoints() {
        return gamePoints;
    }

    public void restartGame() {
        model.getTowerList().clear();
        enemies = null;
        towers = null;
        lasers = null;
        enemiesNr = 0;
        spawnedEnemies = 0;
        paused = false;
    }

    public void endGame() {
        enemies.clear();
        towers.clear();
        lasers.clear();
        enemiesNr = 0;
        spawnedEnemies = 0;
        paused = false;
    }

    private void gameWon() {
        Model.currentPlayer.setPoints(Model.currentPlayer.getPoints() + gamePoints);
        animationTimer.stop();
        enemies.clear();
        towers.clear();
        lasers.clear();
        enemiesNr = 0;
        spawnedEnemies = 0;
        paused = false;
        killedEnemies = 0;
        try {
            controller.pressMenuButton(GameController.GameState.WIN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gameLoose() {
        Model.currentPlayer.setPoints((int) (Model.currentPlayer.getPoints() + 0.5 * gamePoints));
        animationTimer.stop();
        enemies.clear();
        towers.clear();
        lasers.clear();
        enemiesNr = 0;
        spawnedEnemies = 0;
        paused = false;
        try {
            controller.pressMenuButton(GameController.GameState.LOOSE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateEnemiesDamage() {
        long now = System.nanoTime();
        if (now - lastHit > hitRateTime) {
            for (Enemy e : enemies)
                if (e.isUnderLaser() & e.isAlive()) {
                    e.getHit(Laser.getDamage());
                    if (!e.isAlive()) {
                        killedEnemies++;
                        gamePoints += e.getMaxHealth() / 10;
                        Model.currentPlayer.gainCoins(e.getMaxHealth() / 5);
                        controller.updateCoinsAndPoints();
                    }
                }
            lastHit = System.nanoTime();
        }
    }

    private void calculateCastleDamage() {
        for (Enemy e : enemies)
            if (e.isInCastle() && e.isAlive()) {
                Model.currentPlayer.increaseCurrentHealthPoints(-1);
                controller.updateHealthPoints();
                e.enemyDeath();
                killedEnemies++;
            }
        if (Model.currentPlayer.getCurrentHealthPoints() <= 0)
            gameLoose();
    }

    private void spawnEnemy(long now) {
        if (spawnedEnemies >= enemiesNr)
            return;
        if (now - lastSpawn > enemiesSpawnTime) {
            enemies.get(spawnedEnemies).setAlive(true);
            spawnedEnemies++;
            lastSpawn = System.nanoTime();
        }

    }

    private void enemyPhysics() {
        for (Enemy e : enemies)
            if (e.isAlive())
                e.physics(1);
    }

    private void laserPhysic() {
        lasers.clear();
        ArrayList<Enemy> enemiesInRange = new ArrayList<>();
        for (Enemy e : enemies)
            e.setUnderLaser(false);
        for (Tower t : towers) {
            Enemy closestEnemy = null;
            int minDistance = t.getRange();
            for (Enemy e : enemies) {
                if (e.isAlive() && isInRange(t, e))
                    enemiesInRange.add(e);
            }
            for (Enemy e : enemiesInRange) {
                int currentDistance = distance(t, e);
                if (currentDistance < minDistance) {
                    closestEnemy = e;
                    minDistance = currentDistance;
                }
            }
            if (closestEnemy != null) {
                closestEnemy.setUnderLaser(true);
                closestEnemy.increaseShootingLasersNo(true);
                lasers.add(new Laser(t.getCenterX(), t.getCenterY(), closestEnemy.getCenterX(), closestEnemy.getCenterY()));
            }
            enemiesInRange.clear();
        }
    }

    private int distance(Tower t, Enemy e) {
        return (int) Math.sqrt((e.getCenterX() - t.getCenterX()) * (e.getCenterX() - t.getCenterX()) + (e.getCenterY() - t.getCenterY()) * (e.getCenterY() - t.getCenterY()));
    }

    private boolean isInRange(Tower t, Enemy e) {
        return (e.getCenterX() - t.getCenterX()) * (e.getCenterX() - t.getCenterX()) + (e.getCenterY() - t.getCenterY()) * (e.getCenterY() - t.getCenterY()) < t.getRange() * t.getRange();
    }

    private void drawEnemies() {
        for (Enemy e : enemies)
            if (e.isAlive())
                View.drawEnemy(canvas, e);
    }

    private void drawTowers() {
        for (Tower t : towers)
            View.drawTower(canvas, t);
    }

    private void drawLasers() {
        for (Laser l : lasers)
            View.drawLaser(canvas, l);
    }

    private void updateView() {
        View.drawMap(map, canvas);
        drawEnemies();
        drawTowers();
        drawLasers();
    }
}

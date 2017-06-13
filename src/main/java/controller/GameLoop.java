package main.java.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import main.java.model.*;
import main.java.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

class GameLoop {

    private final Object monitor = new Object();
    private Canvas canvas;
    private Integer enemiesNr;
    private Integer spawnedEnemies;
    private long enemiesSpawnTime;
    private long lastSpawn;
    private long lastLaserHit;
    private long lastBombHit;
    private long hitRateTime;
    private long bombShowTime;
    private ArrayList<Enemy> enemies;
    private ArrayList<Tower> towers;
    private ArrayList<Laser> lasers;
    private ArrayList<Bomb> bombs;
    private Model model;
    private Map map;
    private boolean paused;
    private int killedEnemies;
    private int gamePoints;
    private GameController controller;
    private AnimationTimer animationTimer;
    private View view;

    GameLoop(Canvas mainCanvas, Model m, GameController gameController) {
        controller = gameController;
        view = gameController.getView();
        model = m;
        map = model.getMap();
        canvas = mainCanvas;
        enemiesNr = Map.getEnemiesNr();
        enemiesSpawnTime = Map.getEnemiesSpawnTime();
        spawnedEnemies = 0;
        killedEnemies = 0;
        hitRateTime = Tower.getHitRateTime();
        bombShowTime = 1000000000;
        gamePoints = 0;
        model.currentPlayer.setCoins(model.currentPlayer.getBasicCoins());
        paused = false;
        lastSpawn = System.nanoTime();
        lastLaserHit = System.nanoTime();
        lastBombHit = System.nanoTime();
        enemies = new ArrayList<>();
        towers = model.getTowerList();
        bombs = model.getBombList();
        lasers = new ArrayList<>();
        for (int i = 0; i < enemiesNr; i++)
            enemies.add(new Enemy(Map.getStartXPosition(), Map.getStartYPosition()));
    }

    Object getMonitor() {
        return monitor;
    }

    void setPaused(boolean paused) {
        if (paused)
            animationTimer.stop();
        else
            animationTimer.start();
    }

    void startGame() {
        model.currentPlayer.setCoins(model.currentPlayer.getBasicCoins());
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
                bombPhysic();
                calculateEnemiesDamage();
                calculateCastleDamage();
                updateView();
                if (killedEnemies == enemiesNr)
                    gameWon();
            }
        };
        animationTimer.start();
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

    private void bombPhysic() {
        for (Bomb b : bombs) {
            if (b.isActive()) {
                for (Enemy e : enemies)
                    if (e.isAlive() && isInRange(b, e)) {
                        e.setUnderBomb(true);
                    }
                b.setActive(false);
            }
        }
    }

    private void calculateEnemiesDamage() {
        calculateLaserDamage();
        calculateBombDamage();
    }

    private void calculateCastleDamage() {
        for (Enemy e : enemies)
            if (e.isInCastle() && e.isAlive()) {
                model.currentPlayer.increaseCurrentHealthPoints(-1);
                controller.updateHealthPoints();
                e.enemyDeath();
                killedEnemies++;
            }
        if (model.currentPlayer.getCurrentHealthPoints() <= 0)
            gameLoose();
    }

    private void updateView() {
        view.drawMap(map, canvas);
        drawEnemies();
        drawTowers();
        drawLasers();
        drawBombs();
    }

    private void gameWon() {
        model.currentPlayer.setPoints(model.currentPlayer.getPoints() + gamePoints);
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

    private boolean isInRange(Tower t, Enemy e) {
        return (e.getCenterX() - t.getCenterX()) * (e.getCenterX() - t.getCenterX()) + (e.getCenterY() - t.getCenterY()) * (e.getCenterY() - t.getCenterY()) < t.getRange() * t.getRange();
    }

    private int distance(Tower t, Enemy e) {
        return (int) Math.sqrt((e.getCenterX() - t.getCenterX()) * (e.getCenterX() - t.getCenterX()) + (e.getCenterY() - t.getCenterY()) * (e.getCenterY() - t.getCenterY()));
    }

    private boolean isInRange(Bomb b, Enemy e) {
        return (e.getCenterX() - b.getCenterX()) * (e.getCenterX() - b.getCenterX()) + (e.getCenterY() - b.getCenterY()) * (e.getCenterY() - b.getCenterY()) < b.getRange() * b.getRange();
    }

    private void calculateLaserDamage() {
        long now = System.nanoTime();
        if (now - lastLaserHit > hitRateTime) {
            for (Enemy e : enemies)
                if (e.isUnderLaser() & e.isAlive()) {
                    e.getHit(Laser.getDamage());
                    if (!e.isAlive())
                        updateAfterDeath(e);
                }
            lastLaserHit = System.nanoTime();
        }
    }

    private void calculateBombDamage() {
        long now = System.nanoTime();
        Iterator bomb = bombs.iterator();
        while (bomb.hasNext()) {
            Bomb b = (Bomb) bomb.next();
            if (now - b.getExplodeNanoTime() < bombShowTime) {
                for (Enemy e : enemies)
                    if (e.isUnderBomb() & e.isAlive()) {
                        System.out.println("bombs: " + b.getDamage());
                        System.out.println("enemy: " + e.isUnderBomb());
                        e.getBombHit(b.getDamage());
                        e.setUnderBomb(false);
                        if (!e.isAlive())
                            updateAfterDeath(e);
                    }
                lastBombHit = System.nanoTime();
            } else
                bomb.remove();
        }

    }

    private void gameLoose() {
        model.currentPlayer.setPoints((int) (model.currentPlayer.getPoints() + 0.5 * gamePoints));
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

    private void drawEnemies() {
        for (Enemy e : enemies)
            if (e.isAlive())
                view.drawEnemy(canvas, e);
    }

    private void drawTowers() {
        for (Tower t : towers)
            view.drawTower(canvas, t);
    }

    private void drawLasers() {
        for (Laser l : lasers)
            view.drawLaser(canvas, l);
    }

    private void drawBombs() {
        for (Bomb b : bombs)
            view.drawBombs(canvas, b);
    }

    private void updateAfterDeath(Enemy e) {
        killedEnemies++;
        gamePoints += e.getMaxHealth() / 10;
        model.currentPlayer.gainCoins(e.getMaxHealth() / 5);
        controller.updateCoinsAndPoints();
    }

    int getGamePoints() {
        return gamePoints;
    }

    void restartGame() {
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
}

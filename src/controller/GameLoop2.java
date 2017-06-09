package controller;

import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import model.*;
import view.View;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Thread.sleep;

public class GameLoop2 {

/*moje*/

    private Canvas canvas;
    public Integer enemiesNr;
    public Integer spawnedEnemies;
    public Integer enemiesSpawnTime;
    private long lastSpawn;
    private long lastHit;
    private long hitRateTime;
    private ArrayList<Enemy> enemies;
    private ArrayList<Tower> towers;
    private ArrayList<Laser> lasers;
    private boolean end;
    private boolean firstPlay;
    private Model model;
    private Map map;
    private boolean paused;
    private int inGameEnemies;


    Object monitor = new Object();
    private AnimationTimer animationTimer;


    public Object getMonitor() {
        return monitor;
    }


    //    /** Queue for events */
//    private final BlockingQueue<QueueEvent> blockingQueue;
//    /** Sound things */
//    private final SoundManager sound;
//    /** Counts timeouts and put event into queue */
//    private final Timer timer;
//    /** Game model */


    public GameLoop2(Canvas mainCanvas, Model m) {
        System.out.println("GameLoop()");
        //View.drawEnemy(mainCanvas,new Enemy(10,10));
        model = m;
        map = model.getMap();
        canvas = mainCanvas;
        enemiesNr = Map.getEnemiesNr();
        enemiesSpawnTime = Map.getEnemiesSpawnTime();
        spawnedEnemies = 0;
        inGameEnemies = 0;
        hitRateTime = Tower.getHitRateTime();
        end = false;
        paused = false;
        lastSpawn = System.nanoTime();
        lastHit = System.nanoTime();
        enemies = new ArrayList<>();
        towers = model.getTowerList();
        lasers = new ArrayList<>();

        //enemiesNr = 1;
        for(int i=0; i < enemiesNr; i++)
            enemies.add(new Enemy(map.getStartXPosition(), map.getStartYPosition()));



//        this.blockingQueue = new LinkedBlockingQueue<QueueEvent>();
//        this.sound = new SoundManager();
//        this.timer = new Timer();
//        this.model = new Model(sound);
//        this.view = new View(blockingQueue);
//
//        ExecutorService executor = Executors.newCachedThreadPool();
//        executor.execute(new Thread(timer));
//        executor.shutdown();
    }

    public void setPaused(boolean paused) {
        if(paused)
            animationTimer.stop();
        else
            animationTimer.start();
    }

    public void startGame() {
        //
//        Timer timer = new java.util.Timer();
//        timer.schedule(new TimerTask() {
        final long[] lastNanoTime = {System.nanoTime()};
        animationTimer = new AnimationTimer(){
            @Override
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime[0]) / 1000000000.0;
                lastNanoTime[0] = currentNanoTime;

                synchronized (monitor) {
                    while (paused)
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
//                    now = System.currentTimeMillis();
//                    if(now - lastRender > 100)
                spawnEnemy(currentNanoTime);
                enemyPhysics();
                laserPhysic();
                calculateEnemiesDamage();

//                    Platform.runLater(() -> {
//                        lastRender[0] = System.currentTimeMillis();
                    updateView();
//                        System.out.println("-----"+(now[0] - lastRender[0])+"ms----");
//                    });


            }
        };
        animationTimer.start();
//        System.out.println("drawMap2() GC Map: "+map.fileNum);
    }
    public void restartGame() {
    }
    public void endGame() {
        canvas.getGraphicsContext2D().clearRect(0,0,768.0,1056.0);
        enemies.clear();
        towers.clear();
        lasers.clear();
        enemiesNr = 0;
        spawnedEnemies = 0;
        paused = false;
        end = true;
    }

    private void calculateEnemiesDamage() {
//        long now = System.currentTimeMillis();
//        System.out.println("now - lastHit: " + (now-lastHit));
//        if(now-lastHit > hitRateTime) {
//        System.out.println("calculate()");
            for (Enemy e : enemies)
                if (e.isUnderLaser() & e.isAlive()){
                    e.getHit(Laser.getDamage());
                    if(!e.isAlive()){
                        inGameEnemies--;

                    }
//                    System.out.println("damage: " + Laser.getDamage());
//                    System.out.println("now-lastHit: " + (now-lastHit));
//                    System.out.println("current health: " + e.getCurrentHealth() +" max health: "+ e.getMaxHealth());
                }
    }
    private void spawnEnemy(long now) {
        if(spawnedEnemies >= enemiesNr)
            return;
        //System.out.println("spawn" + (spawnedEnemies+1));
        if(now-lastSpawn > enemiesSpawnTime){
            enemies.get(spawnedEnemies).setAlive(true);
            spawnedEnemies++;
            inGameEnemies++;
            lastSpawn = System.nanoTime();
        }

    }
    private void enemyPhysics() {

//        System.out.println("enemyPhisic()");
        for (Enemy e : enemies)
            if (e.isAlive())
                e.physics(1);
    }
    private void laserPhysic() {
        lasers.clear();
        ArrayList<Enemy> enemiesInRange = new ArrayList<>();
        for(Enemy e: enemies)
            e.setUnderLaser(false);
        for(Tower t: towers) {
            Enemy closestEnemy = null;
            int minDistance = t.getRange();
            for (Enemy e : enemies) {
                // Przeciwnicy w zasięgu
                if (e.isAlive() && isInRange(t, e))
                    enemiesInRange.add(e);
            }
            for(Enemy e: enemiesInRange){
                //wyszukuje najbliższego
                int currentDistance = distance(t,e);
                if(currentDistance < minDistance) {
                    closestEnemy = e;
                    minDistance  = currentDistance;
                }
            }
            if(closestEnemy != null){
                closestEnemy.setUnderLaser(true);
                closestEnemy.increaseShootingLasersNo(true);
                lasers.add(new Laser(t.getCenterX(), t.getCenterY(), closestEnemy.getCenterX(), closestEnemy.getCenterY()));
            }
            enemiesInRange.clear();
        }
    }

    private int distance(Tower t, Enemy e) {
        return (int) Math.sqrt((e.getCenterX() - t.getCenterX())*(e.getCenterX() - t.getCenterX()) + (e.getCenterY()- t.getCenterY())*(e.getCenterY()- t.getCenterY()));
    }
    private boolean isInRange(Tower t, Enemy e) {
//        System.out.println(" Range: "+t.getRange());
        return (e.getCenterX() - t.getCenterX())*(e.getCenterX() - t.getCenterX()) + (e.getCenterY()- t.getCenterY())*(e.getCenterY()- t.getCenterY()) < t.getRange()*t.getRange();
    }

    private void drawEnemies(){
        for(Enemy e: enemies)
            if(e.isAlive())
                View.drawEnemy(canvas,e);
    }
    private void drawTowers(){
        for(Tower t: towers)
            View.drawTower(canvas, t);
    }
    private void drawLasers(){
        for(Laser l: lasers)
            View.drawLaser(canvas, l);
    }
    private void updateView() {
        View.drawMap(map, canvas);
        drawEnemies();
        drawTowers();
        drawLasers();
    }
}

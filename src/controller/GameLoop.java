package controller;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import model.Enemy;
import model.Map;
import model.Player;
import model.Model;
import view.View;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Thread.sleep;

public class GameLoop implements Runnable {

    private Thread thread;

    private static double fps = 60.0;

    private Frame frame;
//    public static Image[] tilesetGround = new Image[100];
//    public static Image[] tilesetAir = new Image[100];
//    public static Image[] tilesetRes = new Image[100];
//    public static Image[] tilesetMob = new Image[100];

    int counter = 0;

    public static int myWidth, myHeight;
    public static int coins = 10, life = 100;
    private static int killed = 0, killsToWin = 0;
    public static int level = 1, maxLevel = 3;

    private static boolean gameWon = false;

//    public static Point mse = new Point(0, 0);
//
//    public static Room room;
//    public static Save save;
//    public static Store store;

/*moje*/

    private Canvas canvas;
    public Integer enemiesNr;
    public Integer enemiesSpawnTime = 30;
    public Integer spawnedEnemies;
    private long lastSpawn;
    private ArrayList<Enemy> enemies;
    private boolean end;
    private boolean firstPlay;
    private Model model;
    private Map map;

//    /** Queue for events */
//    private final BlockingQueue<QueueEvent> blockingQueue;
//    /** Sound things */
//    private final SoundManager sound;
//    /** Counts timeouts and put event into queue */
//    private final Timer timer;
//    /** Game model */


    public GameLoop(Canvas mainCanvas, Model m) {
        System.out.println("GameLoop()");
        //View.drawEnemy(mainCanvas,new Enemy(10,10));
        model = m;
        map = model.getMap();
        canvas = mainCanvas;
        enemiesNr = map.getEnemiesNr();
        enemiesSpawnTime = model.getMap().getEnemiesSpawnTime();
        spawnedEnemies = 0;
        end = false;
        lastSpawn = System.currentTimeMillis();
        enemies = new ArrayList<>();
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

    public static void hasWon() {
        if (killed >= killsToWin) {
            killed = 0;
            gameWon = true;
        }
    }



//    public void paintComponent(Graphics g) {
//        if (firstPlay) {
//            myWidth = getWidth();
//            myHeight = getHeight();
//            define();
//
//            firstPlay = false;
//        }
//
//        if (!gameWon) {
//
//            for (int i = enemies.length - 1; i >= 0; i--) {
//                if (enemies[i].isAlive()) {
//                    View.drawEnemy(canvas,enemies[i]);
//                }
//            }
//            if (Model.currentPlayer.getCurrentHealthPoints() < 1) {
//                GraphicsContext gc = canvas.getGraphicsContext2D();
//                gc.setTextAlign(TextAlignment.CENTER);
//                gc.setTextBaseline(VPos.CENTER);
//                gc.fillText("GameOver",300,500);
//            }
//
//        } else {
//            View.gameWin();
//            GraphicsContext gc = canvas.getGraphicsContext2D();
//            gc.setTextAlign(TextAlignment.CENTER);
//            gc.setTextBaseline(VPos.CENTER);
//            gc.fillText("GameOver",300,500);
//        }
//    }

    public void startGame() {
        thread = new Thread(this);
        thread.start();
    }

    private double spawnTime = 1 * (fps),
                  spawnFrame = spawnTime - fps;

    public void enemiesSpawner() {
        if (spawnFrame >= spawnTime) {
            for (int i = 0; i < enemies.size(); i++) {
                if (!enemies.get(i).isAlive()) {
                    View.drawEnemy(canvas, enemies.get(i));
                    break;
                }
            }
            spawnFrame = 1;//-= spawnTime;
        } else {
            spawnFrame++;
        }
    }

    public static double winFrame = 1, winTime = 5 * (double) (fps);

    private void spawnEnemy() {
        long now = System.currentTimeMillis();
        if(spawnedEnemies >= enemiesNr){
            //end = true;
            return;
        }
        //System.out.println("spawn" + (spawnedEnemies+1));
        if(now-lastSpawn>enemiesSpawnTime){
            enemies.get(spawnedEnemies).setAlive(true);
            View.drawEnemy(canvas, enemies.get(spawnedEnemies));
            spawnedEnemies++;
            lastSpawn = System.currentTimeMillis();
        }

    }
    private void enemyPhysics() {
        System.out.println("enemyPhysics");
        for(Enemy e: enemies)
            if(e.isAlive())
                e.physics(1);
    }
    private void drawEnemies(){
        for(Enemy e: enemies)
            if(e.isAlive())
                View.drawEnemy(canvas,e);
    }
    private void updateView() {
        View.drawMap(map, canvas);
        drawEnemies();
    }

    @Override
    public void run(){
        System.out.println("run()");

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / fps;
        double delta = 0;
        int updates = 0, frames = 0;

        while (!end){

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            enemyPhysics();

            Platform.runLater( () -> {
                //Updateuje widok JavaFX
                System.out.println(" updateView() + spawn");
                updateView();
                spawnEnemy();
            });
            try {
//                if (timer - lastSpawn > enemiesSpawnTime) {
//
//                }
                sleep(200);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            frames++;
        }





//            if(now - lastSpawn > 5000)
//                break;

//            long now = System.nanoTime();
//            delta += (now - lastTime) / ns;
//            lastTime = now;
//
//            // Update 60 times a second
//            while (delta >= 1) {
//                //update();
//                timera++;
//                updates++;
//
//                if (Model.currentPlayer.getCurrentHealthPoints()>0 && !gameWon) {
//                    towers.physic();
//                    enemiesSpawner();
//                    for (int i = 0; i < mobs.length; i++) {
//                        if (enemies[i].inGame) mobs[i].physics(i);
//                    }
//                } else if (gameWon) {
//                    if (winFrame >= winTime) {
//                        if (level >= maxLevel) {
//                            System.exit(0);
//                        } else {
//                            gameWon = false;
//                            level++;
//                            coins = 10;
//                            define();
//                        }
//                        winFrame = 1;
//                    } else {
//                        winFrame++;
//                    }
//                }
//
//                delta--;
//            }
//
//            repaint();
//            frames++;
//
            // Keep track of and display the game's ups and fps every second
//            if (System.currentTimeMillis() - timer >= 1000) {
//                timer += 1000;
//                frame.setTitle(frame.getTitle() + " | ups: " + updates + " | fps: " + frames);
//                updates = 0;
//                frames = 0;
//            }

    }
}

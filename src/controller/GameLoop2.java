package controller;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.concurrent.Service;
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

import java.util.ArrayList;
import java.util.concurrent.*;

public class GameLoop2 {
    /*moje*/

    private Canvas canvas;
    public Integer enemiesNr;
    public Integer enemiesSpawnTime;
    public Integer spawnedEnemies;
    private long lastSpawn;
    private ArrayList<Enemy> enemies;
    private boolean firstPlay;
    private Model model;
    private Map map;



    public GameLoop2(Canvas mainCanvas, Model m) {
        System.out.println("GameLoop()");
        model = m;
        map = model.getMap();
        canvas = mainCanvas;
        enemiesNr = map.getEnemiesNr();
        enemiesSpawnTime = model.getMap().getEnemiesSpawnTime();
        spawnedEnemies = 0;
        lastSpawn = System.currentTimeMillis();
        enemies = new ArrayList<>(enemiesNr);
        for(int i=0; i < enemiesNr;i++)
            enemies.add(i, new Enemy(map.getStartXPosition(), map.getStartXPosition()));
    }

    private void spawnEnemy() {
        if(spawnedEnemies >= enemiesNr)
            return;
        System.out.println("1");
        enemies.get(spawnedEnemies).setAlive(true);
        View.drawEnemy(canvas, enemies.get(spawnedEnemies));
        spawnedEnemies++;
        lastSpawn = System.currentTimeMillis();
    }
    private void enemyPhysics() {
        for(Enemy e: enemies)
            if(e.isAlive())
                e.physics(1);
    }
    private void drawEnemies(){
        for(Enemy e: enemies)
            if(e.isAlive())
                View.drawEnemy(canvas,e);
    }
    private void update() {

        //canvas.getGraphicsContext2D().restore();

        View.drawMap(map, canvas);
        drawEnemies();
    }

    public void start() {

        while(true){
            long now = System.currentTimeMillis();
            if (now - lastSpawn > enemiesSpawnTime) {
                spawnEnemy();
            }
            enemyPhysics();

            update();

            if(now - lastSpawn > 6000)
                break;
        }

    }

}






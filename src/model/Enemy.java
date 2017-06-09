package model;

import controller.GameController;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import preferences.Preferences;

import java.awt.*;


/**
 * Created by Mateusz on 2017-05-26.
 */
public class Enemy {
    private int yPositionOnPath;
    private int xPositionOnPath;
    private int currentHealth, maxHealth;
    private int speed;
    private int gamePoints;
    private Direction direction;
    private int xC;
    private int yC; // Coordinates
    boolean isAlive;
    boolean isUnderLaser;
    int shootingLasersNo;
    Polyline polyline;
    ObservableList<Double> points;
    int pointIndex;
    private HealthBar healthBar;

    public int getCurrentHealth() {
        return currentHealth;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getSpeed() {
        return speed;
    }
    public Direction getDirection() {
        return direction;
    }
    public int getLayoutX() {
        return xC;
    }
    public int getLayoutY() {
        return yC;
    }
    public int getCenterX(){
        return xC+Map.getTileWidth()/2;
    }
    public int getCenterY(){
        return yC+Map.getTileHeight()/2;
    }

    public Enemy(int x, int y){
        xC = x;
        yC = y;
        maxHealth = Map.getEnemy_Max_Health();
        currentHealth = maxHealth;
        speed = Map.getEnemy_Speed();
        direction = Direction.RIGHT;
        isAlive = false;
        isUnderLaser = false;
        shootingLasersNo = 0;
        Path path = new Path(GameController.model.getMap());
        path.generatePolyline();
        polyline = path.getPolyline();
        points = polyline.getPoints();
        pointIndex = 0;
        xPositionOnPath = 0;
        yPositionOnPath = 0;
        healthBar = new HealthBar(currentHealth, maxHealth);
    }

    public void setUnderLaser(boolean b) {
        isUnderLaser = b;
        if(!b)
            shootingLasersNo = 0;
    }
    public void increaseShootingLasersNo(boolean b){
        if(b)
            shootingLasersNo++;
        else if(shootingLasersNo>0)
            shootingLasersNo--;
    }

    public enum Direction {
        LEFT , RIGHT, UP, DOWN
    }

    public void getHit(int hit){
        currentHealth -= (shootingLasersNo*hit);
        if(currentHealth <= 0)
            enemyDeath();
    }

    private void enemyDeath() {
        isAlive = false;
        isUnderLaser = false;
        Model.currentPlayer.gainPoints(gamePoints);
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public boolean isUnderLaser() {
        return isUnderLaser;
    }

    public void physics(int i) {
        //System.out.println("pointIndex: "+pointIndex+" xC: "+xC+" yC: "+yC+" xPositionOnPath: "+xPositionOnPath+" xPositionOnPath: "+yPositionOnPath+" DIRECTION: "+direction+" points.get(pointIndex): "+points.get(pointIndex));
        if(pointIndex >= points.size()-2){
            this.enemyDeath();
            return;
        }

        if (direction == Direction.UP) {
            yC -= speed;
        } else if (direction == Direction.DOWN) {
            yC += speed;
        } else if (direction == Direction.RIGHT) {
            xC += speed;
        } else if (direction == Direction.LEFT) {
            xC -= speed;
        }

        if (direction == Direction.UP) {
            yPositionOnPath += speed;
        } else if (direction == Direction.DOWN) {
            yPositionOnPath += speed;
        } else if (direction == Direction.RIGHT) {
            xPositionOnPath += speed;
        } else if (direction == Direction.LEFT) {
            xPositionOnPath += speed;
        }
        if(yPositionOnPath >= 48){
            pointIndex += 2;
            yPositionOnPath = 0;
        }
        if(xPositionOnPath == 48){
            pointIndex += 2;
            xPositionOnPath = 0;
        }

        if(getDirection(points,0)>0)
            direction = Direction.RIGHT;
        else if(getDirection(points,0)<0)
            direction = Direction.LEFT;
        else if(getDirection(points,1)>0)
            direction = Direction.DOWN;
        else if(getDirection(points,1)<0)
            direction = Direction.UP;

    }

    private int getDirection(ObservableList<Double> points,int p) {
        int i =0;
        int r;
        if(pointIndex <= 1)
            r = (int) (points.get(pointIndex +p )-i);
        else
            r = (int) (points.get(pointIndex+p)-points.get(pointIndex-2+p));
        //System.out.println(r);
        return r;
    }
    
    class HealthBar{
        int max, min;
        public HealthBar(int min, int max){
            this.max = max;
            this.min = min;
        }
        public int getMax() {
            return max;
        }
        public int getMin() {
            return min;
        }
        public void setMin(int min) {
            this.min = min;
        }
    }
}

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

    public Integer getCurrentHealth() {
        return currentHealth;
    }
    public Integer getMaxHealth() {
        return maxHealth;
    }
    public Integer getSpeed() {
        return speed;
    }
    public Direction getDirection() {
        return direction;
    }
    public int getxC() {
        return xC;
    }
    public int getyC() {
        return yC;
    }

    private Integer currentHealth, maxHealth;
    private Integer speed;
    private int gamePoints;
    private Direction direction;
    private int xC;
    private int yC; // Coordinates
    boolean isAlive;

    Polyline polyline;
    ObservableList<Double> points;
    int pointIndex;

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public enum Direction {
        LEFT , RIGHT, UP, DOWN
    }

    public Enemy(Integer x, Integer y){
        xC = x;
        yC = y;
//        xC -= Map.gridWidth/2;
//        yC -= Map.gridHeight/2;
        maxHealth = Preferences.Enemy_Max_Health;
        currentHealth = maxHealth;
        speed = Preferences.Enemy_Speed;
        direction = Direction.RIGHT;
        isAlive = false;
        Path path = new Path(GameController.model.getMap());
        path.generatePolyline();
        polyline = path.getPolyline();
        points = polyline.getPoints();
        pointIndex = 0;
        xPositionOnPath = 0;
        yPositionOnPath = 0;

    }
    public void getHit(Integer hit){
        currentHealth -= hit;
        if(currentHealth <= 0)
            enemyDeath();
    }

    private void enemyDeath() {
        isAlive = false;
        Model.currentPlayer.gainPoints(gamePoints);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void physics(int i) {
        //System.out.println("pointIndex: "+pointIndex+" xC: "+xC+" yC: "+yC+" xPositionOnPath: "+xPositionOnPath+" xPositionOnPath: "+yPositionOnPath+" DIRECTION: "+direction+" points.get(pointIndex): "+points.get(pointIndex));
        if(pointIndex>=points.size()-2){
            setAlive(false);
            return;
        }
        if(i == 1){
            if (direction == Direction.UP) {
                yC -= speed;
            } else if (direction == Direction.DOWN) {
                yC += speed;
            } else if (direction == Direction.RIGHT) {
                xC += speed;
            } else if (direction == Direction.LEFT) {
                xC -= speed;
            }
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
}

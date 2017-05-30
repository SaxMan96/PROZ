package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import preferences.Preferences;


/**
 * Created by Mateusz on 2017-05-26.
 */
public class Enemy {
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
    public Integer getxC() {
        return xC;
    }
    public Integer getyC() {
        return yC;
    }

    private Integer currentHealth, maxHealth;
    private Integer speed;
    private Direction direction;
    private Integer xC;
    private Integer yC; // Coordinates
    private Integer points;
    private boolean isAlive;

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public enum Direction {
        LEFT , RIGHT, UP, DOWN
    }

    public Enemy(Integer x, Integer y){
        xC = x;
        yC = y;
        maxHealth = Preferences.Enemy_Max_Health;
        currentHealth = maxHealth;
        speed = Preferences.Enemy_Speed;
        direction = Direction.RIGHT;
        isAlive = true;
    }
    public void getHit(Integer hit){
        currentHealth -= hit;
        if(currentHealth <= 0)
            enemyDeath();
    }

    private void enemyDeath() {
        isAlive = false;
        Model.currentPlayer.gainPoints(points);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void physics(int i) {
        if(i == 1){
            xC += speed;
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
    }

}

package main.java.model;

import javafx.collections.ObservableList;
import javafx.scene.shape.Polyline;
import main.java.Program.Program;
import main.java.controller.GameController;

public class Enemy {
    private boolean isAlive;
    private boolean isUnderLaser;
    private boolean isUnderBomb;
    private int shootingLasersNo;
    private Polyline polyline;
    private ObservableList<Double> points;
    private int pointIndex;
    private boolean inCastle;
    private int yPositionOnPath;
    private int xPositionOnPath;
    private int currentHealth, maxHealth;
    private int speed;
    private Player currentPlayer = Program.model.currentPlayer;
    private Direction direction;
    private int xC;
    private int yC; // Coordinates
    private HealthBar healthBar;
//    private int gamePoints;

    public Enemy(int x, int y) {
        xC = x;
        yC = y;
        maxHealth = Map.getEnemy_Max_Health();
        currentHealth = maxHealth;
        speed = Map.getEnemy_Speed();
        direction = Direction.RIGHT;
        isAlive = false;
        isUnderLaser = false;
        isUnderBomb = false;
        inCastle = false;
        shootingLasersNo = 0;
        Path path = new Path(Program.model.getMap());
        path.generatePolyline();
        polyline = path.getPolyline();
        points = polyline.getPoints();
        pointIndex = 0;
        xPositionOnPath = 0;
        yPositionOnPath = 0;
        healthBar = new HealthBar(currentHealth, maxHealth);
    }

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

    public int getCenterX() {
        return xC + Map.getTileWidth() / 2;
    }

    public int getCenterY() {
        return yC + Map.getTileHeight() / 2;
    }

    public void increaseShootingLasersNo(boolean b) {
        if (b)
            shootingLasersNo++;
        else if (shootingLasersNo > 0)
            shootingLasersNo--;
    }

    public void getHit(int hit) {
        currentHealth -= (shootingLasersNo * hit);
        if (currentHealth <= 0)
            enemyDeath();
    }
    public void getBombHit(int hit) {
        currentHealth -=  hit;
        if (currentHealth <= 0)
            enemyDeath();
    }

    public void enemyDeath() {
        isAlive = false;
        isUnderLaser = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public boolean isUnderLaser() {
        return isUnderLaser;
    }

    public void setUnderLaser(boolean b) {
        isUnderLaser = b;
        if (!b)
            shootingLasersNo = 0;
    }

    public boolean isUnderBomb() {
        return isUnderBomb;
    }

    public void setUnderBomb(boolean b) {
        isUnderBomb = b;
    }

    public boolean isInCastle() {
        return inCastle;
    }

    public void physics(int i) {
        if (pointIndex >= points.size() - 2) {
            inCastle = true;
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
        if (yPositionOnPath >= 48) {
            pointIndex += 2;
            yPositionOnPath = 0;
        }
        if (xPositionOnPath == 48) {
            pointIndex += 2;
            xPositionOnPath = 0;
        }

        if (getDirection(points, 0) > 0)
            direction = Direction.RIGHT;
        else if (getDirection(points, 0) < 0)
            direction = Direction.LEFT;
        else if (getDirection(points, 1) > 0)
            direction = Direction.DOWN;
        else if (getDirection(points, 1) < 0)
            direction = Direction.UP;

    }

    private int getDirection(ObservableList<Double> points, int p) {
        int i = 0;
        int r;
        if (pointIndex <= 1)
            r = (int) (points.get(pointIndex + p) - i);
        else
            r = (int) (points.get(pointIndex + p) - points.get(pointIndex - 2 + p));
        return r;
    }

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    class HealthBar {
        int max, min;

        HealthBar(int min, int max) {
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

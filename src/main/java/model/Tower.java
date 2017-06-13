package main.java.model;

import main.java.Program.Program;

public class Tower {
    private static Integer damage;
    private static Integer range = null;
    private static Integer cost = 100;
    private static long hitRateTime;
    private Integer xC;
    private Integer yC; // Coordinates
    private boolean isSet;
    private Player currentPlayer = Program.model.currentPlayer;

    public Tower() {
        damage = currentPlayer.getBulletDamage();
        xC = Map.getStartXPosition();
        yC = Map.getStartYPosition();
        range = currentPlayer.getTowerRange();
        isSet = false;
        hitRateTime = currentPlayer.getHitRateTime();
    }

    static Integer getDamage() {
        return damage;
    }

    public static long getHitRateTime() {
        return hitRateTime;
    }

    public static Integer getRange() {
        return range;
    }

    public static Integer getCost() {
        return cost;
    }

    public Integer getLayoutX() {
        return xC;
    }

    public Integer getLayoutY() {
        return yC;
    }

    public Integer getCenterX() {
        return xC + Map.getTileWidth() / 2;
    }

    public Integer getCenterY() {
        return yC + Map.getTileHeight() / 2;
    }

    public boolean isSet() {
        return isSet;
    }

    public void set(Integer x, Integer y) {
        xC = x;
        yC = y;
        isSet = true;
    }

}

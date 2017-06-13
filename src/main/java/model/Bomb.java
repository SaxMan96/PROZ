package main.java.model;

import main.java.Program.Program;

public class Bomb {
    private static Integer damage;
    private static Integer range = null;
    private static Integer cost = 200;
    private Integer xC;
    private Integer yC;
    private boolean isActive;
    private long explodeNanoTime;
    private Player currentPlayer = Program.model.currentPlayer;

    public Bomb() {
        damage = currentPlayer.getBombDamage();
        range = currentPlayer.getBombRange();
        isActive = false;
    }

    public static Integer getDamage() {
        return damage;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void set(Integer x, Integer y) {
        xC = x;
        yC = y;
    }

    public long getExplodeNanoTime() {
        return explodeNanoTime;
    }

    public void setExplodeNanoTime(long explodeNanoTime) {
        this.explodeNanoTime = explodeNanoTime;
    }
}

package model;

import javafx.util.Pair;

/**
 * Created by Mateusz on 2017-06-04.
 */
public class Laser {
    Pair<Integer,Integer> source;
    Pair<Integer,Integer> target;
    static int damage = Tower.getDamage();

    public Laser(int  sourceX, int sourceY, int  targetX, int targetY){
        Pair<Integer,Integer> newSource = new Pair<>(sourceX,sourceY);
        this.source = newSource;
        Pair<Integer,Integer> newTarget = new Pair<>(targetX,targetY);
        this.target = newTarget;
    }

    public Pair<Integer, Integer> getSource() {
        return source;
    }
    public Integer getSourceLayoutX() {
        return source.getKey();
    }
    public Integer getSourceLayoutY() {
        return source.getValue();
    }

    public void setSource(int  x, int y) {
        Pair<Integer,Integer> newSource = new Pair<>(x,y);
        this.source = newSource;
    }

    public Pair<Integer, Integer> getTarget() {
        return target;
    }
    public Integer getTargetLayoutX() {
        return target.getKey();
    }
    public Integer getTargetLayoutY() {
        return target.getValue();
    }

    public void setTarget(int  x, int y) {
        Pair<Integer,Integer> newTarget = new Pair<>(x,y);
        this.target = newTarget;
    }

    public static int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        Laser.damage = damage;
    }


}

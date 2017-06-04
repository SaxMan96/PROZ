package model;

import javafx.scene.image.Image;

/**
 * Created by Mateusz on 2017-05-28.
 */
public class Tower {
    private static Integer damage;
    private Integer xC;
    private Integer yC; // Coordinates
    private Integer range;
    private static Integer cost;
    private boolean isSet;
    private Integer width;
    private Integer height;
    public static Image graphic;

    public static Integer getDamage() {
        return damage;
    }
    public Integer getLayoutX() {
        return xC;
    }
    public Integer getLayoutY() {
        return yC;
    }
    public Integer getRange() {
        return range;
    }
    public static Integer getCost() {
        return cost;
    }
    public boolean isSet() {
        return isSet;
    }
    public Integer getWidth() {
        return width;
    }
    public Integer getHeight() {
        return height;
    }


    public Tower(){
        damage = 10;
        xC = Map.getStartXPosition();
        yC = Map.getStartYPosition();
        range = 100;
        isSet = false;
    }
    public void set(Integer x, Integer y){
        xC = x;
        yC = y;
        isSet = true;
    }



}

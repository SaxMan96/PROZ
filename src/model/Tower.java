package model;

import javafx.scene.image.Image;
import preferences.Preferences;

/**
 * Created by Mateusz on 2017-05-28.
 */
public class Tower {
    private static Integer damage = Preferences.Bullet_Power_MAX;
    private Integer xC;
    private Integer yC; // Coordinates
    private static Integer range;
    private static Integer cost = 100;
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
    public Integer getCenterX(){
        return xC+Map.getTileWidth()/2;
    }
    public Integer getCenterY(){
        return yC+Map.getTileHeight()/2;
    }
    public static Integer getRange() {
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
        System.out.println("Created Tower");
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

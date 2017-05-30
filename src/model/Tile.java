package model;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import preferences.Preferences;
import view.View;

import static model.Tile.TileType.*;

/**
 * Created by Mateusz on 2017-05-21.
 */
public class Tile {

    public enum TileType {
        PATH, START, FINISH, USABLE, NOT_USABLE
    }
    public enum Direction {
        LEFT , RIGHT, UP, DOWN, NODIRECTION
    }

    private static double width = (double) Preferences.getGame_Panel_Width()/Preferences.getTiles_In_Row();
    private static double height = (double) Preferences.getGame_Panel_Height()/Preferences.getTiles_In_Column();
    private boolean isUsable;
    private boolean hasDirection;
    private TileType tileType;
    public double xPosition;
    public double yPosition;
    public Integer row;
    public Integer column;

    public double getXPosition() {
        return xPosition;
    }
    public double getYPosition() {
        return yPosition;
    }
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    public boolean isUsable() {
        return isUsable;
    }
    public boolean HasDirection() {
        return hasDirection;
    }
    public TileType getTileType() {
        return tileType;
    }
    public Tile(TileType type, Integer r, Integer c){
        row = r;
        column = c;
        xPosition = (column-1) * width;
        yPosition = (row-1) * height;

        tileType = type;
        if(tileType == USABLE) {
            isUsable = true;
            hasDirection = false;
        }
        else if(tileType == PATH | tileType == START ) {
            isUsable = false;
            hasDirection = true;
        }
        else{
            isUsable = false;
            hasDirection = false;
        }

    }
}

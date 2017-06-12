package main.java.model;

import main.java.Program.Preferences;

import static main.java.model.Tile.TileType.*;

/**
 * Created by Mateusz on 2017-05-21.
 */
public class Tile {

    private static double width = (double) Preferences.getGame_Panel_Width() / Preferences.getTiles_In_Row();
    private static double height = (double) Preferences.getGame_Panel_Height() / Preferences.getTiles_In_Column();
    public double xPosition;
    public double yPosition;
    public Integer row;
    public Integer column;
    private boolean isUsable;
    private boolean hasDirection;
    private TileType tileType;
    public Tile(TileType type, Integer c, Integer r) {
        row = r;
        column = c;
        xPosition = (column - 1) * width;
        yPosition = (row - 1) * height;
        tileType = type;

        if (tileType == USABLE) {
            isUsable = true;
            hasDirection = false;
        } else if (tileType == PATH | tileType == START) {
            isUsable = false;
            hasDirection = true;
        } else {
            isUsable = false;
            hasDirection = false;
        }

    }

    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public TileType getTileType() {
        return tileType;
    }

    public enum TileType {
        PATH, START, FINISH, USABLE, TOWER_PLACE
    }

    public enum Direction {
        LEFT, RIGHT, UP, DOWN, NODIRECTION
    }
}

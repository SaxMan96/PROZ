package model;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;
import javafx.util.Pair;
import preferences.Preferences;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import static model.Tile.TileType.*;

public class Map{
    public static Integer gridHeight = Preferences.getTiles_In_Column();
    public static Integer gridWidth = Preferences.getTiles_In_Row();
    public static Integer startRow;
    public static Integer startColumn;
    public static Integer startXPosition, startYPosition;

    public static Integer enemiesNr;
    public static Integer enemiesSpawnTime;


    private static char _PATH = 'p';
    private static char _START = 's';
    private static char _FINISH = 'f';
    private static char _USABLE = '.' ;
    private static char _NOT_USABLE = 'x';

    public static Integer getEnemiesNr() {
        return enemiesNr;
    }
    public static Integer getEnemiesSpawnTime() {
        return enemiesSpawnTime;
    }
    public static Integer getGridHeight() {
        return gridHeight;
    }
    public static Integer getGridWidth() {
        return gridWidth;
    }
    public static Integer getStartRow() {
        return startRow;
    }
    public static Integer getStartColumn() {
        return startColumn;
    }

    public Map(){
        gridHeight = Preferences.getTiles_In_Column();
        gridWidth  = Preferences.getTiles_In_Row();
    }

    private ArrayList<Tile> grid;
    /*
    * I've chosen ArrayList over LinkedList or other containers due to its
    * short algorithmic runtime od get(Integer x, Integer y) operation eqals O(1),
    * while LinkedList performance is O(n).
    * */

    public static Integer getStartXPosition() {
        return startXPosition;
    }
    public static Integer getStartYPosition() {
        return startYPosition;
    }

    public ArrayList<Tile> getGrid() {
        return grid;
    }
    public Tile.TileType getTileType(Integer row, Integer col) {
        return grid.get(row*col) == null ? null : grid.get(row*col).getTileType();
    }

    private void setTileToGrid(Integer row, Integer col, Tile.TileType value) {
        Tile tile = new  Tile(value, row, col);
        grid.add(tile);
    }

    public void load(Integer fileNumber) throws IOException {
        grid = new ArrayList<Tile>(Preferences.getTiles_In_Column()*Preferences.getTiles_In_Row());
        try(BufferedReader br = new BufferedReader(new FileReader("Maps"+File.separator+"map"+fileNumber+".txt" ))) {
            Integer row = 1;
            for (String line; (line = br.readLine()) != null; row++) {
                //if(line.length()==gridWidth && row <= gridHeight)
                for (int col = 1; col < line.length()+1; col++) {
                    if(line.contains("Enemies ")){
                        line = line.replace("Enemies ","");
                        enemiesNr = Integer.parseInt(line);
                    }
                    else if(line.contains("EnemiesSpawnTime ")){
                        line = line.replace("EnemiesSpawnTime ","");
                        enemiesSpawnTime = Integer.parseInt(line);
                    }
                    else{
                        char c = line.charAt(col - 1);
                        if (c == _PATH)
                            setTileToGrid(row, col, PATH);
                        else if (c == _FINISH)
                            setTileToGrid(row, col, FINISH);
                        else if (c == _START) {
                            setTileToGrid(row, col, START);
                            startRow = row;
                            startColumn = col;
                            Integer width = Preferences.getGame_Panel_Width() / Preferences.getTiles_In_Row();
                            Integer height = Preferences.getGame_Panel_Height() / Preferences.getTiles_In_Column();
                            Double halfWidth = width*0.5;
                            Double halfHeight = height*0.5;
                            startYPosition = width* startRow - halfWidth.intValue();
                            startXPosition = height  * startColumn - halfHeight.intValue();
                        } else if (c == _USABLE)
                            setTileToGrid(row, col, USABLE);
                        else if (c == _NOT_USABLE)
                            setTileToGrid(row, col, NOT_USABLE);
                    }
                }
            }
        }
    }
}



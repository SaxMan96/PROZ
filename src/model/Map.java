package model;
import preferences.Preferences;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import static model.Tile.TileType.*;

public class Map{
    private static Integer gridHeight = Preferences.getTiles_In_Column();
    private static Integer gridWidth = Preferences.getTiles_In_Row();
    private static Integer startRow;
    private static Integer startColumn;
    private static Integer startXPosition, startYPosition;

    private static int enemiesNr;
    private static long enemiesSpawnTime;


    private static char _PATH = 'p';
    private static char _START = 's';
    private static char _FINISH = 'f';
    private static char _USABLE = '.' ;
    private static char _TOWER_PLACE = 'x';

    private static int tileWidth;
    private static int tileHeight;
    public int fileNum;
    private static int Enemy_Speed;
    private static int Enemy_Max_Health;

    public static int getTileWidth() {
        return tileWidth;
    }
    static int getTileHeight() {
        return tileHeight;
    }

    public static int getEnemiesNr() {
        return enemiesNr;
    }
    public static long getEnemiesSpawnTime() {
        return enemiesSpawnTime;
    }
    public static Integer getGridHeight() {
        return gridHeight;
    }
    static Integer getGridWidth() {
        return gridWidth;
    }
    static Integer getStartRow() {
        return startRow;
    }
    static Integer getStartColumn() {
        return startColumn;
    }
    static int getEnemy_Speed() {
        return Enemy_Speed;
    }
    static int getEnemy_Max_Health() {
        return Enemy_Max_Health;
    }

    Map(){
        gridHeight = Preferences.getTiles_In_Column();
        gridWidth  = Preferences.getTiles_In_Row();
        grid = new ArrayList<>(Preferences.getTiles_In_Column()*Preferences.getTiles_In_Row());
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
    private Tile.TileType getTileTypeFromGrid(Integer col, Integer row) {
        return grid.get(findGridElement(col,row)) == null ? null : grid.get(findGridElement(col,row)).getTileType();
    }
    private int findGridElement(int col,int row){
        return (row-1)*gridWidth+col-1;
    }

    private void setTileToGrid(Integer col, Integer row, Tile.TileType value) {
        Tile tile = new  Tile(value, col, row);
        grid.add(findGridElement(col,row),tile);
    }

    void load(Integer fileNumber) throws IOException {
        grid.clear();
        fileNum = fileNumber;
        URL resource = Map.class.getClassLoader().getResource("Maps/"+"map"+fileNumber+".txt");
        String path = null;
        if (resource != null) {
            path = resource.toString().substring(resource.toString().indexOf("file:") + 6);
        }

        if (path != null) {
            try(BufferedReader br = new BufferedReader(new FileReader(path))) {
                Integer row = 1;
                for (String line; (line = br.readLine()) != null; row++) {
                    for (int col = 1; col < line.length()+1; col++) {
                        if(line.contains("Enemies ")){
                            line = line.replace("Enemies ","");
                            enemiesNr = Integer.parseInt(line);
                        }else if(line.contains("EnemiesSpawnTime ")){
                            line = line.replace("EnemiesSpawnTime ","");
                            enemiesSpawnTime = Long.parseLong(line);
                        }else if(line.contains("Enemy_Speed ")){
                            line = line.replace("Enemy_Speed ","");
                            Enemy_Speed = Integer.parseInt(line);
                        }else if(line.contains("Enemy_Max_Health ")){
                            line = line.replace("Enemy_Max_Health ","");
                            Enemy_Max_Health = Integer.parseInt(line);
                        }
                        else{
                            char c = line.charAt(col - 1);
                            if (c == _PATH)
                                setTileToGrid(col, row, PATH);
                            else if (c == _FINISH)
                                setTileToGrid(col, row, FINISH);
                            else if (c == _START) {
                                setTileToGrid(col, row, START);
                                startRow = row;
                                startColumn = col;
                                tileWidth = Preferences.getGame_Panel_Width() / Preferences.getTiles_In_Row();
                                tileHeight = Preferences.getGame_Panel_Height() / Preferences.getTiles_In_Column();
                                startYPosition = tileWidth  * startRow;
                                startXPosition = tileHeight * startColumn;
                                startYPosition -= tileWidth;
                                startXPosition -= tileHeight;
                            } else if (c == _USABLE)
                                setTileToGrid(col, row, USABLE);
                            else if (c == _TOWER_PLACE){
                                setTileToGrid(col, row, TOWER_PLACE);
                            }
                        }
                    }
                }
            }
        }
    }

    public Tile.TileType getTileTypeFromCoordinates(int x, int y){
        return getTileTypeFromGrid(x/getTileWidth()+1,y/getTileHeight()+1);
    }
}



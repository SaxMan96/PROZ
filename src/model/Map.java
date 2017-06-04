package model;
import preferences.Preferences;

import java.io.*;
import java.util.ArrayList;

import static model.Tile.TileType.*;

public class Map{
    private static Integer gridHeight = Preferences.getTiles_In_Column();
    private static Integer gridWidth = Preferences.getTiles_In_Row();
    private static Integer startRow;
    private static Integer startColumn;
    private static Integer startXPosition, startYPosition;

    private static int enemiesNr;
    private static int enemiesSpawnTime;


    private static char _PATH = 'p';
    private static char _START = 's';
    private static char _FINISH = 'f';
    private static char _USABLE = '.' ;
    private static char _TOWER_PLACE = 'x';

    private int tileWidth;
    private int tileHeight;
    public int fileNum;

    public int getTileWidth() {
        return tileWidth;
    }
    public int getTileHeight() {
        return tileHeight;
    }
    public static int getEnemiesNr() {
        return enemiesNr;
    }
    public static int getEnemiesSpawnTime() {
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
    public Tile.TileType getTileTypeFromGrid(Integer col, Integer row) {
        return grid.get(findGridElement(col,row)) == null ? null : grid.get(findGridElement(col,row)).getTileType();
    }
    private int findGridElement(int col,int row){
//        System.out.println("row: "+row+" col: "+col+ " ret: "+((row-1)*gridWidth+col-1));
        return (row-1)*gridWidth+col-1;
    }

    private void setTileToGrid(Integer col, Integer row, Tile.TileType value) {
        Tile tile = new  Tile(value, col, row);
        //System.out.println("row "+row+" col "+col);
        grid.add(findGridElement(col,row),tile);
    }

    public void load(Integer fileNumber) throws IOException {
//        fileNum = fileNumber;
        try(BufferedReader br = new BufferedReader(new FileReader("Maps" +File.separator+"map"+fileNumber+".txt" ))) {
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

    public Tile.TileType getTileTypeFromCoordinates(int x, int y){
//        System.out.print(fileNum);
//        System.out.println("x: "+x+"y: "+y);
//        System.out.println("x/: "+(x/getTileWidth()+1)+"y/: "+(y/getTileHeight()+1));
//        System.out.println(getTileTypeFromGrid(x/getTileWidth()+1,y/getTileHeight()+1));

        return getTileTypeFromGrid(x/getTileWidth()+1,y/getTileHeight()+1);
    }
}



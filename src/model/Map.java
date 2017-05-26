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

    private static char _PATH = 'p';
    private static char _START = 's';
    private static char _FINISH = 'f';
    private static char _USABLE = '.' ;
    private static char _NOT_USABLE = 'x';



    private ArrayList<Tile> grid;
    /*
    * I've chosen ArrayList over LinkedList or other containers due to its
    * short algorithmic runtime od get(Integer x, Integer y) operation eqals O(1),
    * while LinkedList performance is O(n).
    * */
    public ArrayList<Tile> getGrid() {
        return grid;
    }
    public Tile.TileType getTileType(Integer row, Integer col) {
        return grid.get(row*col) == null ? null : grid.get(row*col).getTileType();
    }

    private void setTileToGrid(Integer row, Integer col, Tile.TileType value) {
        System.out.println("row x col"+row+col);
        Tile tile = new  Tile(value, row, col);
        grid.add(tile);
    }

    public void load(Integer fileNumber) throws IOException {
        grid = new ArrayList<Tile>(22*16);
        try(BufferedReader br = new BufferedReader(new FileReader("Maps"+File.separator+"map"+fileNumber+".txt" ))) {
            Integer row = 1;
            for (String line; (line = br.readLine()) != null; row++) {
                //if(line.length()==gridWidth && row <= gridHeight)
                    for (int col = 1; col < line.length()+1; col++) {
                        char c = line.charAt(col-1);
                        if (c == _PATH)
                            setTileToGrid(row, col, PATH);
                        else if (c == _FINISH)
                            setTileToGrid(row, col, FINISH);
                        else if (c == _START)
                            setTileToGrid(row, col, START);
                        else if (c == _USABLE)
                            setTileToGrid(row, col, USABLE);
                        else if (c == _NOT_USABLE)
                            setTileToGrid(row, col, NOT_USABLE);
                    }
                /*else{
                    System.out.println("Zły plik z mapą błąd w wierszu: " + row );
                }*/
            }
        }
    }


    public void drawMap(Canvas canvas) {
        String tileName = null;
        System.out.println("Sieze:"+getGrid().size());
        for(Tile tile: getGrid()){
            switch(tile.getTileType()){
                case START: tileName = "startTile.png"; break;
                case USABLE:tileName = "usableTile.png"; break;
                case NOT_USABLE:tileName = "notUsableTile.png"; break;
                case FINISH:tileName = "finishTile.png"; break;
                case PATH:tileName = "pathTile.png"; break;
            }
            //Image image = new Image(getClass().getResource(tileName).toExternalForm());
            Image image = new Image("file:Graphics/"+tileName);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(image, tile.getXPosition(),tile.getYPosition());
            /*gc.setTextAlign(TextAlignment.CENTER);
            gc.setTextBaseline(VPos.CENTER);
            gc.fillText(i.toString(),tile.getXPosition()+24,tile.getYPosition()+24);
            System.out.println(i);
            i++;*/
        }
    }
}



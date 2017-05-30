package model;

import javafx.scene.shape.Polyline;
import preferences.Preferences;

import java.util.ArrayList;
import java.util.function.DoubleBinaryOperator;

/**
 * Created by Mateusz on 2017-05-27.
 */
public class Path {
    public enum Direction {
        LEFT , RIGHT, UP, DOWN, END
    }
    ArrayList<Direction> directions;
    Integer currentTile;
    Integer pathLength;
    ArrayList<Tile> grid;
    Map map;
    Polyline polyline;

    public Path(Map m){
        map = m;
        directions  = new ArrayList<>(/*Preferences.getTiles_In_Column()*Preferences.getTiles_In_Row()*/);
        grid = map.getGrid();
        load((map.getStartRow()-1)*map.getGridWidth()+ (map.getStartColumn()-1)*map.getGridWidth(),Direction.END);
    }
        public void load(Integer position, Direction previous){
        if (grid.get(position).getTileType() == Tile.TileType.START || grid.get(position).getTileType() == Tile.TileType.PATH) {
            if((grid.get(position+1).getTileType() == Tile.TileType.PATH || grid.get(position+1).getTileType() == Tile.TileType.FINISH) && previous != Direction.LEFT){
                directions.add(Direction.RIGHT);
                load(position+1, Direction.RIGHT);
            }
            else if((grid.get(position+map.getGridWidth()).getTileType() == Tile.TileType.PATH || grid.get(position+map.getGridWidth()).getTileType() == Tile.TileType.FINISH) && previous != Direction.UP){
                directions.add(Direction.DOWN);
                load(position+map.getGridWidth(), Direction.DOWN);
            }
            else if((grid.get(position-1).getTileType() == Tile.TileType.PATH || grid.get(position-1).getTileType() == Tile.TileType.FINISH) && previous != Direction.RIGHT){
                directions.add(Direction.LEFT);
                load(position-1, Direction.LEFT);
            }
            else if((grid.get(position-map.getGridWidth()).getTileType() == Tile.TileType.PATH || grid.get(position-map.getGridWidth()).getTileType() == Tile.TileType.FINISH) && previous != Direction.DOWN){
                directions.add(Direction.UP);
                load(position-map.getGridWidth(), Direction.UP);
            }
        }
        else if(grid.get(position).getTileType() == Tile.TileType.FINISH ){
            directions.add(Direction.END);
        }
    }

    public void generatePolyline() {
        polyline = new Polyline();
        ArrayList<Double> currentPosition = new ArrayList<Double>();
        currentPosition.add(0.0);
        currentPosition.add(0.0);

        ArrayList<Double> path = new ArrayList<>();

        Direction previousDirection = Direction.END;
        currentPosition.set(0,currentPosition.get(0)+24.0);
        path.add(currentPosition.get(0));
        path.add(currentPosition.get(1));
        for(Direction d: directions){
            if(d == Direction.RIGHT)
                currentPosition.set(0,currentPosition.get(0)+48.0);
            else if(d == Direction.LEFT)
                currentPosition.set(0,currentPosition.get(0)-48.0);
            else if(d == Direction.DOWN)
                currentPosition.set(1,currentPosition.get(1)+48.0);
            else if(d == Direction.UP)
                currentPosition.set(1,currentPosition.get(1)-48.0);
            previousDirection = d;
            path.add(currentPosition.get(0));
            path.add(currentPosition.get(1));
            for(Double s: path)
                System.out.println(s);
        }
        polyline.getPoints().addAll(path);

    }

    public Polyline getPolyline(){
        return polyline;
    }

}

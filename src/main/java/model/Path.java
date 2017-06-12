package main.java.model;

import javafx.scene.shape.Polyline;

import java.util.ArrayList;

class Path {
    Integer currentTile;
    Integer pathLength;
    private ArrayList<Direction> directions;
    private ArrayList<Tile> grid;
    private Polyline polyline;
    Path(Map m) {
        directions = new ArrayList<>();
        grid = m.getGrid();
        load((Map.getStartRow() - 1) * Map.getGridWidth() + (Map.getStartColumn() - 1) * Map.getGridWidth(), Direction.END);
    }

    private void load(Integer position, Direction previous) {
        if (grid.get(position).getTileType() == Tile.TileType.START) {
            if ((grid.get(position + 1).getTileType() == Tile.TileType.PATH || grid.get(position + 1).getTileType() == Tile.TileType.FINISH) && previous != Direction.LEFT) {
                load(position + 1, Direction.RIGHT);
            } else if ((grid.get(position + Map.getGridWidth()).getTileType() == Tile.TileType.PATH || grid.get(position + Map.getGridWidth()).getTileType() == Tile.TileType.FINISH) && previous != Direction.UP) {
                load(position + Map.getGridWidth(), Direction.DOWN);
            } else if ((grid.get(position - 1).getTileType() == Tile.TileType.PATH || grid.get(position - 1).getTileType() == Tile.TileType.FINISH) && previous != Direction.RIGHT) {
                load(position - 1, Direction.LEFT);
            } else if ((grid.get(position - Map.getGridWidth()).getTileType() == Tile.TileType.PATH || grid.get(position - Map.getGridWidth()).getTileType() == Tile.TileType.FINISH) && previous != Direction.DOWN) {
                load(position - Map.getGridWidth(), Direction.UP);
            }
        }
        if (grid.get(position).getTileType() == Tile.TileType.PATH) {
            if ((grid.get(position + 1).getTileType() == Tile.TileType.PATH || grid.get(position + 1).getTileType() == Tile.TileType.FINISH) && previous != Direction.LEFT) {
                directions.add(Direction.RIGHT);
                load(position + 1, Direction.RIGHT);
            } else if ((grid.get(position + Map.getGridWidth()).getTileType() == Tile.TileType.PATH || grid.get(position + Map.getGridWidth()).getTileType() == Tile.TileType.FINISH) && previous != Direction.UP) {
                directions.add(Direction.DOWN);
                load(position + Map.getGridWidth(), Direction.DOWN);
            } else if ((grid.get(position - 1).getTileType() == Tile.TileType.PATH || grid.get(position - 1).getTileType() == Tile.TileType.FINISH) && previous != Direction.RIGHT) {
                directions.add(Direction.LEFT);
                load(position - 1, Direction.LEFT);
            } else if ((grid.get(position - Map.getGridWidth()).getTileType() == Tile.TileType.PATH || grid.get(position - Map.getGridWidth()).getTileType() == Tile.TileType.FINISH) && previous != Direction.DOWN) {
                directions.add(Direction.UP);
                load(position - Map.getGridWidth(), Direction.UP);
            }
        } else if (grid.get(position).getTileType() == Tile.TileType.FINISH) {
            directions.add(Direction.END);
        }
    }

    void generatePolyline() {
        polyline = new Polyline();
        ArrayList<Double> currentPosition = new ArrayList<>();
        currentPosition.add(0.0);
        currentPosition.add(0.0);
        ArrayList<Double> path = new ArrayList<>();
        currentPosition.set(0, currentPosition.get(0) + 24.0);
        path.add(currentPosition.get(0));
        path.add(currentPosition.get(1));
        for (Direction d : directions) {
            if (d == Direction.RIGHT)
                currentPosition.set(0, currentPosition.get(0) + 48.0);
            else if (d == Direction.LEFT)
                currentPosition.set(0, currentPosition.get(0) - 48.0);
            else if (d == Direction.DOWN)
                currentPosition.set(1, currentPosition.get(1) + 48.0);
            else if (d == Direction.UP)
                currentPosition.set(1, currentPosition.get(1) - 48.0);

            path.add(currentPosition.get(0));
            path.add(currentPosition.get(1));
        }
        polyline.getPoints().addAll(path);
    }

    Polyline getPolyline() {
        return polyline;
    }

    public enum Direction {
        LEFT, RIGHT, UP, DOWN, END
    }

}

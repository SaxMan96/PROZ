package model;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;

public class Map {
    public Integer gridHeight = 9;
    public Integer gridWidth = 12;

    private static char _PATH = 'p';
    private static char _START = 's';
    private static char _FINISH = 'f';
    private static char _USABLE = ' ' ;
    private static char _NOT_USABLE = 'x';

    private static Integer PATH = 0;
    private static Integer START = 1;
    private static Integer FINISH = 2;
    private static Integer USABLE = 3;
    private static Integer NOT_USABLE = 4;

    private ArrayList<ArrayList<Integer>> grid = new ArrayList<ArrayList<Integer>>();
    /*
    * I've choosen ArrayList over LinkedList or other containers due to its
    * short algorithmic runtime od get(Integer x, Integer y) operation eqals O(1),
    * while LinkedList performance is O(n).
    * */
    public Integer get(Integer row, Integer col) {
        return grid.get(row) == null ? null : grid.get(row).get(col);
    }

    private void set(Integer row, Integer col, Integer value) {
        ArrayList<Integer> r = grid.get(row);
        if (r == null) {
            r = new ArrayList<Integer>();
            r.set(col,value);
            grid.set(row, r);
        }
        r.set(col, value);
    }
    public void load(File fileName) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            Integer row = 0;
            for (String line; (line = br.readLine()) != null; row++) {
                for (int col = 0; col < line.length(); col++) {
                    char c = line.charAt(col);
                    if (c == _PATH)
                        set(row, col, PATH);
                    else if (c == _FINISH)
                        set(row, col, FINISH);
                    else if (c == _START)
                        set(row, col, START);
                    else if (c == _USABLE)
                        set(row, col, USABLE);
                    else if (c == _NOT_USABLE)
                        set(row, col, NOT_USABLE);
                }
            }
        }
    }


}



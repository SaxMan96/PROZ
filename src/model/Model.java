package model;

import controller.Controller;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Mateusz on 2017-03-31.
 */
public class Model{

    private long isPlayed;
    private long isPased;

/*
    private GameMap map;
    private MapLoader mapLoader;
    private CollisionDetector collisionDetector;
    private BombCalculator bombCalculator;
    static SoundManager sound;
*/
    public Player currentPlayer= new Player();
    public ArrayList<Pair<String,String>> existingPlayers = new ArrayList<Pair<String, String>>();
    //                    Player, File

    public void loadPlayers() throws IOException {
        // File folder = new File("F:" + File.separator + "EiTI Infa" + File.separator + "Semestr 4" + File.separator + "PROZ - Programowanie Zdarzeniowe" + File.separator + "Project" + File.separator + "Players");
        File folder = new File("Players");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                //BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Players/player1.txt")));
                Scanner scan = new Scanner(new File("Players"+File.separator+file.getName()));
                //System.out.println(file.getName());
                //System.out.println(scan.nextLine());
                Pair<String,String> pair = new Pair(file.getName(),scan.nextLine());
                existingPlayers.add(pair);
            }
        }
    }

    public void setNewPlayer() {

    }
}

/*
public class Model extends Thread {
    @Override
    public void run() {
        super.run();
        while (true) {
        }
    }

    */
/*
Thread watek
time start*//*

}
*/


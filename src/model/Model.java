package model;

import javafx.scene.control.Alert;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Mateusz on 2017-03-31.
 */
public class Model{

    private static long isPlayed;
    private static long isPased;

    private static Map map;
    /*
       private MapLoader mapLoader
       private CollisionDetector collisionDetector;
       private BombCalculator bombCalculator;
       public static SoundManager sound;
    */
    public static Player currentPlayer;
    public static ArrayList<Pair<String,String>> existingPlayers;

    public Map getMap() {
        return map;
    }
    //                    Player, File


    public void set() throws IOException {
        map = new Map();
        currentPlayer = new Player();
        existingPlayers = new ArrayList<>();
        loadPlayersFromFile();
    }
    public void loadPlayersFromFile() throws IOException {
        // File folder = new File("F:" + File.separator + "EiTI Infa" + File.separator + "Semestr 4" + File.separator + "PROZ - Programowanie Zdarzeniowe" + File.separator + "Project" + File.separator + "Players");
        File folder = new File("Players");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile() && file./*getName().*/toString().contains("player")) {
                Scanner scan = new Scanner(new File("Players" +File.separator+file.getName()));
                Pair<String,String> pair = new Pair(file.getName(),scan.nextLine());
                existingPlayers.add(pair);
            }
        }
    }
    public void setNewPlayer(String text){
        currentPlayer.setName(text);
    }
    public void setExistingPlayer(String playerName) throws IOException {
        loadExactPlayer(findPlayerFileName(playerName));
    }
    private String findPlayerFileName(String playerName){
        Pair p = null;
        for(Pair pair: existingPlayers)
            if (pair.getValue() == playerName)
            {
                p = pair;
                break;
            }
        if(p != null)
            return (String) p.getKey();
        else
            return null;
    }

    private void loadExactPlayer(String fileName) throws IOException {
            try(BufferedReader br = new BufferedReader(new FileReader("..\\Players" +File.separator+fileName))) {
            String line;
            line = br.readLine();
            currentPlayer.setName(line);
            line = br.readLine();
            currentPlayer.setCoins(Integer.parseInt(line));
            line = br.readLine();
            currentPlayer.setBulletPower(Integer.parseInt(line));
            line = br.readLine();
            currentPlayer.setBulletSpeed(Integer.parseInt(line));
            line = br.readLine();
            currentPlayer.setBombRange(Integer.parseInt(line));
            line = br.readLine();
            currentPlayer.setBombPower(Integer.parseInt(line));
            line = br.readLine();
            currentPlayer.setHealthPoints(Integer.parseInt(line));
            line = br.readLine();
            currentPlayer.setAchievements(line);
            line = br.readLine();
            currentPlayer.setMissions(line);
            }
    }

    public void loadMap(Integer mapNumber) throws IOException {
        //System.out.println("Map number: "+mapNumber+"(model.loadMap)");
        map.load(mapNumber);
    }

    public void deleteCurrentPlayer() {
        currentPlayer = null;
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


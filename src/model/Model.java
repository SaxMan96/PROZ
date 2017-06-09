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
    public static ArrayList<Tower> towerList = null;



    public static Player currentPlayer;
    public static ArrayList<Pair<String,String>> existingPlayers;

    public Map getMap() {
        return map;
    }
    public static ArrayList<Tower> getTowerList() {
        return towerList;
    }
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void set() throws IOException {
        map = new Map();
        towerList = new ArrayList<>();
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
            try(BufferedReader br = new BufferedReader(new FileReader("file:\\Players" +File.separator+fileName))) {

            String line;
            line = br.readLine();
            line = line.replace("Name ","");
            System.out.println(line);
            currentPlayer.setName(line);
            line = br.readLine();
            line = line.replace("Coins ","");
            currentPlayer.setCoins(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("Points ","");
            currentPlayer.setPoints(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("Tower_Range ","");
            currentPlayer.setTowerRange(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("Bullet_Damage ","");
            currentPlayer.setBulletDamage(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("Hit_Rate_Time ","");
            currentPlayer.setHitRateTime(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("Bomb_Range ","");
            currentPlayer.setBombRange(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("Bomb_Damage ","");
            currentPlayer.setBombDamage(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("Health_Points ","");
            currentPlayer.setHealthPoints(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("Achievements ","");
            currentPlayer.setAchievements(line);
            line = br.readLine();
            line = line.replace("Missions ","");
            currentPlayer.setMissions(line);

            }
    }

    public void loadMap(Integer mapNumber) throws IOException {
        System.out.println("Map number: "+mapNumber+"(model.loadMap)");
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


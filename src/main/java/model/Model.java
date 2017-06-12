package main.java.model;

import javafx.scene.control.Alert;
import javafx.util.Pair;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {

    public static Player currentPlayer;
    public static ArrayList<Pair<String, String>> existingPlayers;
    private static ArrayList<Tower> towerList = null;
    private static Map map;

    public static ArrayList<Tower> getTowerList() {
        return towerList;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Map getMap() {
        return map;
    }

    public void set() throws IOException {
        map = new Map();
        towerList = new ArrayList<>();
        currentPlayer = new Player();
        existingPlayers = new ArrayList<>();

        loadPlayersFromFile();
    }

    public void loadPlayersFromFile() throws IOException {
        URL resource = getClass().getResource("/main/resources/Players");

        String path = null;
        if (resource != null) {
            path = resource.toString().substring(resource.toString().indexOf("file:") + 6);
        }
        File folder = null;
        if (path != null) {
            folder = new File(path);
        }
        File[] listOfFiles = new File[0];
        if (folder != null) {
            listOfFiles = folder.listFiles();
        }
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && file.toString().contains("player")) {
                    Scanner scan = new Scanner(new File(path + File.separator + file.getName()));
                    Pair<String, String> pair = new Pair(file.getName(), scan.nextLine().replace("Name ", ""));
                    existingPlayers.add(pair);
                }
            }
        }
    }

    public void setNewPlayer(String text) {
        currentPlayer = new Player();
        currentPlayer.setName(text);
    }

    public void setExistingPlayer(String playerName) throws IOException {
        loadExactPlayer(findPlayerFileName(playerName));
    }

    private void loadExactPlayer(String fileName) throws IOException {
        URL resource = getClass().getResource("/main/resources/Players");

        String path = null;
        if (resource != null)
            path = resource.toString().substring(resource.toString().indexOf("file:") + 6);

        try (BufferedReader br = new BufferedReader(new FileReader(path  + "/" +  fileName))) {
            currentPlayer = new Player();
            String line;
            line = br.readLine();
            line = line.replace("Name ", "");
            currentPlayer.setName(line);
            line = br.readLine();
            line = line.replace("Coins ", "");
            currentPlayer.setCoins(Integer.parseInt(line));
            currentPlayer.setBasicCoins(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("Points ", "");
            currentPlayer.setPoints(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("Tower_Range ", "");
            currentPlayer.setTowerRange(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("Bullet_Damage ", "");
            currentPlayer.setBulletDamage(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("Hit_Rate_Time ", "");
            currentPlayer.setHitRateTime(Long.parseLong(line));
            line = br.readLine();
            line = line.replace("Bomb_Range ", "");
            currentPlayer.setBombRange(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("Bomb_Damage ", "");
            currentPlayer.setBombDamage(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("Health_Points ", "");
            currentPlayer.setHealthPoints(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("Achievements ", "");
            currentPlayer.setAchievements(line);
            line = br.readLine();
            line = line.replace("Missions ", "");
            currentPlayer.setMissions(line);
            line = br.readLine();
            line = line.replace("TowerRangeLevel ", "");
            currentPlayer.setTowerRangeLevel(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("BulletDamageLevel ", "");
            currentPlayer.setBulletDamageLevel(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("HitRateTimeLevel ", "");
            currentPlayer.setHitRateTimeLevel(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("BombRangeLevel ", "");
            currentPlayer.setBombRangeLevel(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("BombPowerLevel ", "");
            currentPlayer.setBombPowerLevel(Integer.parseInt(line));
            line = br.readLine();
            line = line.replace("HealthPointsLevel ", "");
            currentPlayer.setHealthPointsLevel(Integer.parseInt(line));
            currentPlayer.setAsANewPlayer(false);
            currentPlayer.setFileName(fileName);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error File");
            alert.setHeaderText(null);
            alert.setContentText("Corrupted main.resources.preferences file");
            alert.showAndWait();
        }
    }

    private String findPlayerFileName(String playerName) {
        Pair p = null;
        for (Pair pair : existingPlayers)
            if (pair.getValue() == playerName) {
                p = pair;
                break;
            }
        if (p != null)
            return (String) p.getKey();
        else
            return null;
    }

    public void loadMap(Integer mapNumber) throws IOException {
        map.load(mapNumber);
    }

    public void saveCurrentPlayer() {
        try {
            PrintWriter writer;
            String filePath = "";
            URL resource;
            if (currentPlayer.isNewPlayer()) {
                resource = getClass().getResource("/main/resources/Players/player" + existingPlayers.size() + ".txt");
                if (resource != null) {
                    filePath = resource.toString().substring(resource.toString().indexOf("file:") + 6);
                }
                filePath = filePath.replace(String.valueOf(existingPlayers.size()), String.valueOf(existingPlayers.size() + 1));
            } else {
                resource = getClass().getResource("/main/resources/Players/" +  currentPlayer.getFileName());
                if (resource != null) {
                    filePath = resource.toString().substring(resource.toString().indexOf("file:") + 6);
                }
            }


            writer = new PrintWriter(filePath, "UTF-8");

            writer.println("Name " + currentPlayer.getName());
            writer.println("Coins " + currentPlayer.getCoins());
            writer.println("Points " + currentPlayer.getPoints());
            writer.println("Tower_Range " + currentPlayer.getTowerRange());
            writer.println("Bullet_Damage " + currentPlayer.getBulletDamage());
            writer.println("Hit_Rate_Time " + currentPlayer.getHitRateTime());
            writer.println("Bomb_Range " + currentPlayer.getBombRange());
            writer.println("Bomb_Damage " + currentPlayer.getBombDamage());
            writer.println("Health_Points " + currentPlayer.getHealthPoints());
            writer.println("Achievements " + currentPlayer.getAchievements());
            writer.println("Missions " + currentPlayer.getMissions());
            writer.println("TowerRangeLevel " + currentPlayer.getTowerRangeLevel());
            writer.println("BulletDamageLevel " + currentPlayer.getBulletDamageLevel());
            writer.println("HitRateTimeLevel " + currentPlayer.getHitRateTimeLevel());
            writer.println("BombRangeLevel " + currentPlayer.getBombRangeLevel());
            writer.println("BombPowerLevel " + currentPlayer.getBombPowerLevel());
            writer.println("HealthPointsLevel " + currentPlayer.getHealthPointsLevel());
            writer.close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("File Saving Error");
            alert.setHeaderText(null);
            alert.setContentText("Cannot save player.");
            alert.showAndWait();
        }

    }

    public void clearExistingPlayers() {
        existingPlayers.clear();
    }
}


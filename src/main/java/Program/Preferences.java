package main.java.Program;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Preferences extends Properties {

    private static int Tiles_In_Row;
    private static int Tiles_In_Column;
    private static int MAX_Health_Point;
    private static int MAX_Tower_Range;
    private static int MAX_Bullet_Damage;
    private static int MAX_Hit_Rate_Time;
    private static int MAX_Bomb_Range;
    private static int MAX_Bomb_Power;
    private static int Missions_Number;
    private static int Achievements_Number;
    private static int Game_Panel_Width;
    private static int Game_Panel_Height;
    private static int TowerRangeLevels;
    private static int BulletDamageLevels;
    private static int HitRateTimeLevels;
    private static int BombRangeLevels;
    private static int BombPowerLevels;
    private static int HealthPointsLevels;
    private static ArrayList<Integer> TowerRangeIncreaseCosts;
    private static ArrayList<Integer> BulletDamageIncreaseCosts;
    private static ArrayList<Integer> HitRateTimeIncreaseCosts;
    private static ArrayList<Integer> BombRangeIncreaseCosts;
    private static ArrayList<Integer> BombPowerIncreaseCosts;
    private static ArrayList<Integer> HealthPointsIncreaseCosts;

    public static Integer getTiles_In_Row() {
        return Tiles_In_Row;
    }

    public static Integer getTiles_In_Column() {
        return Tiles_In_Column;
    }

    public static int getMissions_Number() {
        return Missions_Number;
    }

    public static int getAchievements_Number() {
        return Achievements_Number;
    }

    public static int getGame_Panel_Width() {
        return Game_Panel_Width;
    }

    public static int getGame_Panel_Height() {
        return Game_Panel_Height;
    }

    public static int getTowerRangeLevels() {
        return TowerRangeLevels;
    }

    public static int getBulletDamageLevels() {
        return BulletDamageLevels;
    }

    public static int getHitRateTimeLevels() {
        return HitRateTimeLevels;
    }

    public static int getBombRangeLevels() {
        return BombRangeLevels;
    }

    public static int getBombPowerLevels() {
        return BombPowerLevels;
    }

    public static int getHealthPointsLevels() {
        return HealthPointsLevels;
    }

    public static Integer getTowerRangeIncreaseCosts(Integer level) {
        return TowerRangeIncreaseCosts.get(level);
    }

    public static Integer getBulletDamageIncreaseCosts(Integer level) {
        return BulletDamageIncreaseCosts.get(level);
    }

    public static Integer getHitRateTimeIncreaseCosts(Integer level) {
        return HitRateTimeIncreaseCosts.get(level);
    }

    public static Integer getBombRangeIncreaseCosts(Integer level) {
        return BombRangeIncreaseCosts.get(level);
    }

    public static Integer getBombDamageIncreaseCosts(Integer level) {
        return BombPowerIncreaseCosts.get(level);
    }

    public static Integer getHealthPointsIncreaseCosts(Integer level) {
        return HealthPointsIncreaseCosts.get(level);
    }

    public int getMAX_Health_Point() {
        return MAX_Health_Point;
    }

    public int getMAX_Bomb_Power() {
        return MAX_Bomb_Power;
    }

    public int getMAX_Tower_Range() {
        return MAX_Tower_Range;
    }

    public int getMAX_Bullet_Damage() {
        return MAX_Bullet_Damage;
    }

    public int getMAX_Hit_Rate_Time() {
        return MAX_Hit_Rate_Time;
    }

    public int getMAX_Bomb_Range() {
        return MAX_Bomb_Range;
    }

//
//    public void loadFromFile() throws IOException {
//        TowerRangeIncreaseCosts = new ArrayList<>();
//        BulletDamageIncreaseCosts = new ArrayList<>();
//        HitRateTimeIncreaseCosts = new ArrayList<>();
//        BombPowerIncreaseCosts = new ArrayList<>();
//        BombRangeIncreaseCosts = new ArrayList<>();
//        HealthPointsIncreaseCosts = new ArrayList<>();
//        URL resource = getClass().getResource("/main.resources/preferences/preferences.properties");
////        URL resource = getClass().getResource("/main.resources/preferences" + File.separator + "main.resources.preferences.properties");
////        FileInputStream stream = new FileInputStream("main.resources/preferences"+ File.separator+"preferences.properties");
//
//        String path = null;
//        if (resource != null) {
//            path = resource.toString().substring(resource.toString().indexOf("file:") + 6);
//        }
//        if (path != null) {
//            try (BufferedReader br = new BufferedReader(/*new InputStreamReader(stream)*/new FileReader(path))) {
//                String line;
//                line = br.readLine();
//                line = line.replace("Tiles_In_Row ", "");
//                Tiles_In_Row = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("Tiles_In_Column ", "");
//                Tiles_In_Column = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("MAX_Tower_Range ", "");
//                MAX_Tower_Range = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("MAX_Bullet_Damage ", "");
//                MAX_Bullet_Damage = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("MAX_Hit_Rate_Time ", "");
//                MAX_Hit_Rate_Time = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("MAX_Bomb_Range ", "");
//                MAX_Bomb_Range = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("MAX_Bomb_Power ", "");
//                MAX_Bomb_Power = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("MAX_Health_Point ", "");
//                MAX_Health_Point = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("Missions_Number ", "");
//                Missions_Number = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("Achievements_Number ", "");
//                Achievements_Number = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("Game_Panel_Width ", "");
//                Game_Panel_Width = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("Game_Panel_Height ", "");
//                Game_Panel_Height = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("TowerRangeLevels ", "");
//                TowerRangeLevels = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("BulletDamageLevels ", "");
//                BulletDamageLevels = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("HitRateTimeLevels ", "");
//                HitRateTimeLevels = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("BombRangeLevels ", "");
//                BombRangeLevels = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("BombPowerLevels ", "");
//                BombPowerLevels = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("HealthPointsLevels ", "");
//                HealthPointsLevels = Integer.parseInt(line);
//                line = br.readLine();
//                line = line.replace("TowerRangeIncreaseCosts ", "");
//                while (line.contains(", ")) {
//                    String firstWord = line.substring(0, line.indexOf(", "));
//                    TowerRangeIncreaseCosts.add(Integer.valueOf(firstWord));
//                    line = line.substring(line.indexOf(", ") + 1, line.length());
//                }
//                TowerRangeIncreaseCosts.add(Integer.valueOf(line));
//
//                line = br.readLine();
//                line = line.replace("BulletDamageIncreaseCosts ", "");
//                while (line.contains(", ")) {
//                    String firstWord = line.substring(0, line.indexOf(", "));
//                    BulletDamageIncreaseCosts.add(Integer.valueOf(firstWord));
//                    line = line.substring(line.indexOf(", ") + 1, line.length());
//                }
//                BulletDamageIncreaseCosts.add(Integer.valueOf(line));
//
//                line = br.readLine();
//                line = line.replace("HitRateTimeIncreaseCosts ", "");
//                while (line.contains(", ")) {
//                    String firstWord = line.substring(0, line.indexOf(", "));
//                    HitRateTimeIncreaseCosts.add(Integer.valueOf(firstWord));
//                    line = line.substring(line.indexOf(", ") + 1, line.length());
//                }
//                HitRateTimeIncreaseCosts.add(Integer.valueOf(line));
//
//                line = br.readLine();
//                line = line.replace("BombRangeIncreaseCosts ", "");
//                while (line.contains(", ")) {
//                    String firstWord = line.substring(0, line.indexOf(", "));
//                    BombRangeIncreaseCosts.add(Integer.valueOf(firstWord));
//                    line = line.substring(line.indexOf(", ") + 1, line.length());
//                }
//                BombRangeIncreaseCosts.add(Integer.valueOf(line));
//
//                line = br.readLine();
//                line = line.replace("BombPowerIncreaseCosts ", "");
//                while (line.contains(", ")) {
//                    String firstWord = line.substring(0, line.indexOf(", "));
//                    BombPowerIncreaseCosts.add(Integer.valueOf(firstWord));
//                    line = line.substring(line.indexOf(", ") + 1, line.length());
//                }
//                BombPowerIncreaseCosts.add(Integer.valueOf(line));
//
//                line = br.readLine();
//                line = line.replace("HealthPointsIncreaseCosts ", "");
//                while (line.contains(", ")) {
//
//                    String firstWord = line.substring(0, line.indexOf(", "));
//                    HealthPointsIncreaseCosts.add(Integer.valueOf(firstWord));
//                    line = line.substring(line.indexOf(", ") + 1, line.length());
//                }
//                HealthPointsIncreaseCosts.add(Integer.valueOf(line));
//
//            } catch (NullPointerException e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText("Error.");
//                alert.setContentText("Wrong main.resources.preferences file.");
//                alert.showAndWait();
//            }
//        }
//    }

    public void loadFromFile() throws IOException {
        TowerRangeIncreaseCosts = new ArrayList<>();
        BulletDamageIncreaseCosts = new ArrayList<>();
        HitRateTimeIncreaseCosts = new ArrayList<>();
        BombPowerIncreaseCosts = new ArrayList<>();
        BombRangeIncreaseCosts = new ArrayList<>();
        HealthPointsIncreaseCosts = new ArrayList<>();
//        InputStream input = getClass().getResourceAsStream("/classpath/to/my/file");
//        InputStream input = getClass().getResourceAsStream("../../main/resources/preferences/preferences.properties");
        InputStream input = new FileInputStream("../../main/resources/preferences/preferences.properties");
        load(input);
        try {
            Tiles_In_Row = Integer.parseInt(getProperty("Tiles_In_Row"));
            Tiles_In_Column = Integer.parseInt(getProperty("Tiles_In_Column"));
            MAX_Tower_Range = Integer.parseInt(getProperty("MAX_Tower_Range"));
            MAX_Bullet_Damage = Integer.parseInt(getProperty("MAX_Bullet_Damage"));
            MAX_Hit_Rate_Time = Integer.parseInt(getProperty("MAX_Hit_Rate_Time"));
            MAX_Bomb_Range = Integer.parseInt(getProperty("MAX_Bomb_Range"));
            MAX_Bomb_Power = Integer.parseInt(getProperty("MAX_Bomb_Power"));
            MAX_Health_Point = Integer.parseInt(getProperty("MAX_Health_Point"));
            Missions_Number = Integer.parseInt(getProperty("Missions_Number"));
            Achievements_Number = Integer.parseInt(getProperty("Achievements_Number"));
            Game_Panel_Width = Integer.parseInt(getProperty("Game_Panel_Width"));
            Game_Panel_Height = Integer.parseInt(getProperty("Game_Panel_Height"));
            TowerRangeLevels = Integer.parseInt(getProperty("TowerRangeLevels"));
            BulletDamageLevels = Integer.parseInt(getProperty("BulletDamageLevels"));
            HitRateTimeLevels = Integer.parseInt(getProperty("HitRateTimeLevels"));
            BombRangeLevels = Integer.parseInt(getProperty("BombRangeLevels"));
            BombPowerLevels = Integer.parseInt(getProperty("BombPowerLevels"));
            HealthPointsLevels = Integer.parseInt(getProperty("HealthPointsLevels"));
            String line = getProperty("TowerRangeIncreaseCosts");
            while (line.contains(",")) {
                String firstWord = line.substring(0, line.indexOf(","));
                TowerRangeIncreaseCosts.add(Integer.valueOf(firstWord));
                line = line.substring(line.indexOf(",") + 1, line.length());

            }
            TowerRangeIncreaseCosts.add(Integer.valueOf(line));

            line = getProperty("BulletDamageIncreaseCosts");

            while (line.contains(",")) {
                String firstWord = line.substring(0, line.indexOf(","));
                BulletDamageIncreaseCosts.add(Integer.valueOf(firstWord));
                line = line.substring(line.indexOf(",") + 1, line.length());
            }
            BulletDamageIncreaseCosts.add(Integer.valueOf(line));

            line = getProperty("HitRateTimeIncreaseCosts");
            while (line.contains(",")) {
                String firstWord = line.substring(0, line.indexOf(","));
                HitRateTimeIncreaseCosts.add(Integer.valueOf(firstWord));
                line = line.substring(line.indexOf(",") + 1, line.length());
            }
            HitRateTimeIncreaseCosts.add(Integer.valueOf(line));

            line = getProperty("BombRangeIncreaseCosts");
            while (line.contains(",")) {
                String firstWord = line.substring(0, line.indexOf(","));
                BombRangeIncreaseCosts.add(Integer.valueOf(firstWord));
                line = line.substring(line.indexOf(",") + 1, line.length());
            }
            BombRangeIncreaseCosts.add(Integer.valueOf(line));

            line = getProperty("BombPowerIncreaseCosts");
            while (line.contains(",")) {
                String firstWord = line.substring(0, line.indexOf(","));
                BombPowerIncreaseCosts.add(Integer.valueOf(firstWord));
                line = line.substring(line.indexOf(",") + 1, line.length());
            }
            BombPowerIncreaseCosts.add(Integer.valueOf(line));

            line = getProperty("HealthPointsIncreaseCosts");
            while (line.contains(",")) {

                String firstWord = line.substring(0, line.indexOf(","));
                HealthPointsIncreaseCosts.add(Integer.valueOf(firstWord));
                line = line.substring(line.indexOf(",") + 1, line.length());
            }
            HealthPointsIncreaseCosts.add(Integer.valueOf(line));

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error.");
            alert.setContentText("Wrong main.resources.preferences file.");
            alert.showAndWait();
        }
    }
}


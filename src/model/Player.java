package model;

import java.util.ArrayList;

/**
 * Created by Mateusz on 2017-05-02.
 */
public class Player {

    private String name;
    private int coins;
    private int points;
    private int bulletSpeed;
    private int bulletPower;
    private int bombRange;
    private int bombPower;
    private int healthPoints;
    private int currentHealthPoints;
    public ArrayList<Integer> missions;
    public ArrayList<Integer> achievements;

    public Player(){
        name = "Steve";
        coins = 500;
        points = 0;
        bulletPower = 1;
        bulletSpeed = 1;
        bombPower = 1;
        bombRange = 1;
        healthPoints = 1;
        currentHealthPoints = healthPoints;
        missions = new ArrayList<>();
        achievements = new ArrayList<>();

        /*for(int i =0;i<preferences.Missions_Number;i++)
            missions.add(0);
        for(int i =0;i<preferences.Achievements_Number;i++)
            achievements.add(0);*/
    }

    public String getName(){return name;}
    public void setName(String n){this.name = n;}
    public Integer getPoints() {
        return points;
    }
    public ArrayList<Integer> getMissions(){return missions;}
    public void setMissions(String line) {
        /*missions = new ArrayList<>();
        if(missions != null)
            missions.clear();*/
        for (int col = 0; col < line.length(); col++)
            missions.add(Character.getNumericValue(line.charAt(col)));
    }
    public ArrayList<Integer> getAchievements(){return achievements;}
    public void setAchievements(String line) {
        /*achievements = new ArrayList<>();*/
        if(achievements != null)
            achievements.clear();
        for (int col = 0; col < line.length(); col++)
            achievements.add(Character.getNumericValue(line.charAt(col)));
    }


    public int getCurrentHealthPoints() {
        return currentHealthPoints;
    }
    public void increaseCurrentHealthPoints(Integer i){
        currentHealthPoints += i;
    }
    public int getBulletSpeed() {
        return bulletSpeed;
    }
    public void setBulletSpeed(Integer bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }
    public int getBulletPower() {
        return bulletPower;
    }
    public void setBulletPower(Integer bulletPower) {
        this.bulletPower = bulletPower;
    }
    public int getBombRange() {
        return bombRange;
    }
    public void setBombRange(Integer bombRange) {
        this.bombRange = bombRange;
    }
    public int getBombPower() {
        return bombPower;
    }
    public void setBombPower(Integer bombPower) {
        this.bombPower = bombPower;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public void setHealthPoints(Integer healthPoints) {
        this.healthPoints = healthPoints;
    }
    public int getCoins() {
        return coins;
    }
    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public void executeCost(int cost){
        this.coins -= cost;
    }
    public void gainPoints(int p){
        this.points += p;
    }

    public void clear() {
        if(missions != null)
            missions.clear();
        if(achievements != null)
            achievements.clear();
    }
}

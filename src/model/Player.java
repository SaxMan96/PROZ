package model;

import java.util.ArrayList;

/**
 * Created by Mateusz on 2017-05-02.
 */
public class Player {

    private String name;
    private Integer coins;
    private Integer points;
    private Integer bulletSpeed;
    private Integer bulletPower;
    private Integer bombRange;
    private Integer bombPower;
    private Integer healthPoints;
    private Integer currentHealthPoints;
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


    public Integer getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    public void increaseCurrentHealthPoints(Integer i){
        currentHealthPoints += i;
    }

    public Integer getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(Integer bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public Integer getBulletPower() {
        return bulletPower;
    }

    public void setBulletPower(Integer bulletPower) {
        this.bulletPower = bulletPower;
    }

    public Integer getBombRange() {
        return bombRange;
    }

    public void setBombRange(Integer bombRange) {
        this.bombRange = bombRange;
    }

    public Integer getBombPower() {
        return bombPower;
    }

    public void setBombPower(Integer bombPower) {
        this.bombPower = bombPower;
    }

    public Integer getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Integer healthPoints) {
        this.healthPoints = healthPoints;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public void clear() {
        if(missions != null)
            missions.clear();
        if(achievements != null)
            achievements.clear();
    }

    public void gainPoints(Integer p) {
        points += p;
    }
}

package model;

import java.util.ArrayList;

/**
 * Created by Mateusz on 2017-05-02.
 */
public class Player {

    public ArrayList<Integer> missions;
    public ArrayList<Integer> achievements;
    private String name;
    private int coins;
    private int basicCoins;
    private int points;
    private int towerRange;
    private long hitRateTime;
    private int bulletDamage;
    private int bombRange;
    private int bombDamage;
    private int healthPoints;
    private int currentHealthPoints;
    private int towerRangeLevel;
    private int bulletDamageLevel;
    private int hitRateTimeLevel;
    private int bombRangeLevel;
    private int bombPowerLevel;
    private int healthPointsLevel;

    public Player() {
        name = "Steve";
        coins = 500;
        basicCoins = 500;
        points = 200;
        towerRange = 100;
        hitRateTime = 2500000000L;
        bulletDamage = 10;
        bombDamage = 0;
        bombRange = 0;
        healthPoints = 1;
        currentHealthPoints = healthPoints;
        missions = new ArrayList<>();
        achievements = new ArrayList<>(5);

        towerRangeLevel = 1;
        bulletDamageLevel = 1;
        hitRateTimeLevel = 1;
        bombPowerLevel = 1;
        bombRangeLevel = 1;
        healthPointsLevel = 1;
        for (int i = 0; i < 5; i++)
            missions.add(0);
        System.out.println(missions);
        for (int i = 0; i < 5; i++)
            achievements.add(0);
    }

    public void show() {
        System.out.println("name: " + name);
        System.out.println("coins: " + coins);
        System.out.println("basicCoins: " + basicCoins);
        System.out.println("points: " + points);
        System.out.println("towerRange: " + towerRange);
        System.out.println("hitRateTime: " + hitRateTime);
        System.out.println("bulletDamage: " + bulletDamage);
        System.out.println("bombDamage: " + bombDamage);
        System.out.println("bombRange: " + bombRange);
        System.out.println("healthPoints: " + healthPoints);
        System.out.println("currentHealthPoints: " + currentHealthPoints);
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getMissions() {
        String ret = new String();
        for(int i =0; i<missions.size();i++)
            ret += String.valueOf(missions.get(i));
        return ret;
    }

    public void setMissions(String line) {
        /*missions = new ArrayList<>();
        if(missions != null)
            missions.clear();*/
        for (int col = 0; col < line.length(); col++)
            missions.add(Character.getNumericValue(line.charAt(col)));
    }

    public String getAchievements() {
        String ret = new String();
        for(int i = 0; i<achievements.size();i++)
            ret += String.valueOf(achievements.get(i));
        System.out.println("ret "+ret);
        return ret;
    }

    public void setAchievements(String line) {
        /*achievements = new ArrayList<>();*/
        if (achievements != null)
            achievements.clear();
        for (int col = 0; col < line.length(); col++)
            achievements.add(Character.getNumericValue(line.charAt(col)));
    }

    public int getTowerRange() {
        return towerRange;
    }

    public void setTowerRange(int towerRange) {
        this.towerRangeLevel += 1;
        this.towerRange += towerRange;
    }

    public int getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    public void increaseCurrentHealthPoints(Integer i) {
        currentHealthPoints += i;
    }

    public long getHitRateTime() {
        return hitRateTime;
    }

    public void setHitRateTime(long hitRateTime) {
        this.hitRateTimeLevel += 1;
        this.hitRateTime += hitRateTime;
    }

    public int getBulletDamage() {
        return bulletDamage;
    }

    public void setBulletDamage(Integer bulletDamage) {
        this.bulletDamageLevel += 1;
        this.bulletDamage += bulletDamage;
    }

    public int getBombRange() {
        return bombRange;
    }

    public void setBombRange(Integer bombRange) {
        this.bombRangeLevel += 1;
        this.bombRange += bombRange;
    }

    public int getBombDamage() {
        return bombDamage;
    }

    public void setBombDamage(Integer bombPower) {
        this.bombPowerLevel += 1;
        this.bombDamage += bombPower;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Integer currentHealthPoints) {
        this.currentHealthPoints = currentHealthPoints;
        this.healthPoints = currentHealthPoints;
    }

    public void increaseHealthPoints(Integer healthPoints) {
        this.healthPointsLevel += 1;
        this.healthPoints += healthPoints;
        this.currentHealthPoints += healthPoints;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public int getBasicCoins() {
        return basicCoins;
    }

    public void setBasicCoins(int basicCoins) {
        this.basicCoins = basicCoins;
    }

    public int getTowerRangeLevel() {
        return towerRangeLevel;
    }

    public void setTowerRangeLevel(int towerRangeLevel) {
        this.towerRangeLevel = towerRangeLevel;
    }

    public int getBulletDamageLevel() {
        return bulletDamageLevel;
    }

    public void setBulletDamageLevel(int bulletDamageLevel) {
        this.bulletDamageLevel = bulletDamageLevel;
    }

    public int getHitRateTimeLevel() {
        return hitRateTimeLevel;
    }

    public void setHitRateTimeLevel(int hitRateTimeLevel) {
        this.hitRateTimeLevel = hitRateTimeLevel;
    }

    public int getBombRangeLevel() {
        return bombRangeLevel;
    }

    public void setBombRangeLevel(int bombRangeLevel) {
        this.bombRangeLevel = bombRangeLevel;
    }

    public int getBombPowerLevel() {
        return bombPowerLevel;
    }

    public void setBombPowerLevel(int bombPowerLevel) {
        this.bombPowerLevel = bombPowerLevel;
    }

    public int getHealthPointsLevel() {
        return healthPointsLevel;
    }

    public void setHealthPointsLevel(int healthPointsLevel) {
        this.healthPointsLevel = healthPointsLevel;
    }

    public void executePointsCost(int points) {
        this.points -= points;
    }

    public void executeCoinsCost(int coins) {
        this.coins -= coins;
    }

    public void gainPoints(int p) {
        this.points += p;
    }

    public void gainCoins(int c) {
        this.coins += c;
    }

    public void clear() {
        if (missions != null)
            missions.clear();
        if (achievements != null)
            achievements.clear();
    }

    public void missionCompleted(int mission) {
        missions.set(mission - 1, 1);
    }
}

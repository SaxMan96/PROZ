package model;

import java.util.LinkedList;

/**
 * Created by Mateusz on 2017-05-02.
 */
public class Player {
    private String name;
    private long points;
    private Integer power;
    private Integer speed;
    private LinkedList<Boolean> missions;
    private LinkedList<Boolean> achivements;

    public void Player(){
        name = "Steve";
        points = 0;
        power = 0;
        speed = 0;
    }
    public String getName(){return name;}

}

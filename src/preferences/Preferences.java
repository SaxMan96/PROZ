package preferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Mateusz on 2017-05-21.
 */
public class Preferences {
    public static Integer getTiles_In_Row() {
        return Tiles_In_Row;
    }

    public static Integer getTiles_In_Column() {
        return Tiles_In_Column;
    }

    public static Integer getBullet_Power_MAX() {
        return Bullet_Power_MAX;
    }

    public static Integer getBullet_Speed_MAX() {
        return Bullet_Speed_MAX;
    }

    public static Integer getBomb_Range_MAX() {
        return Bomb_Range_MAX;
    }

    public static Integer getBomb_Power_MAX() {
        return Bomb_Power_MAX;
    }

    public static Integer getHealth_Point_MAX() {
        return Health_Point_MAX;
    }

    public static Integer getMissions_Number() {
        return Missions_Number;
    }

    public static Integer getAchievements_Number() {
        return Achievements_Number;
    }

    public static Integer getGame_Panel_Width() {
        return Game_Panel_Width;
    }

    public static Integer getGame_Panel_Height() {
        return Game_Panel_Height;
    }

    public static Integer Tiles_In_Row;
    public static Integer Tiles_In_Column;
    public static Integer Bullet_Power_MAX;
    public static Integer Bullet_Speed_MAX;
    public static Integer Bomb_Range_MAX;
    public static Integer Bomb_Power_MAX;
    public static Integer Health_Point_MAX;
    public static Integer Missions_Number;
    public static Integer Achievements_Number;
    public static Integer Game_Panel_Width;
    public static Integer Game_Panel_Height;
    public static Integer Enemy_Max_Health;
    public static Integer Enemy_Speed;
    public static Integer Tower_Range;


    public void loadFromFile() throws IOException {
            try(BufferedReader br = new BufferedReader(new FileReader("src"+File.separator+"preferences"+File.separator+"preferences.txt"))) {
            String line;
                line = br.readLine();
                line = line.replace("Tiles_In_Row ","");
            Tiles_In_Row = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("Tiles_In_Column ","");
            Tiles_In_Column = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("Bullet_Power_MAX ","");
            Bullet_Power_MAX = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("Bullet_Speed_MAX ","");
            Bullet_Speed_MAX = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("Bomb_Range_MAX ","");
            Bomb_Range_MAX = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("Bomb_Power_MAX ","");
            Bomb_Power_MAX = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("Health_Point_MAX ","");
            Health_Point_MAX = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("Missions_Number ","");
            Missions_Number = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("Achievements_Number ","");
            Achievements_Number = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("Game_Panel_Width ","");
            Game_Panel_Width = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("Game_Panel_Height ","");
            Game_Panel_Height = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("Enemy_Max_Health ","");
            Enemy_Max_Health = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("Enemy_Speed ","");
            Enemy_Speed = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("Tower_Range ","");
            Tower_Range = Integer.parseInt(line);
        }
    }

}

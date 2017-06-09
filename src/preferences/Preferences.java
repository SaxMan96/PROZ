package preferences;

import javafx.scene.control.Alert;

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
    public static int getTower_Range() {
        return Tower_Range;
    }

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
    private static int Tower_Range;


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
                line = line.replace("MAX_Tower_Range ","");
            MAX_Tower_Range = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("MAX_Bullet_Damage ","");
            MAX_Bullet_Damage = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("MAX_Hit_Rate_Time ","");
            MAX_Hit_Rate_Time = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("MAX_Bomb_Range ","");
            MAX_Bomb_Range = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("MAX_Bomb_Power ","");
            MAX_Bomb_Power = Integer.parseInt(line);
                line = br.readLine();
                line = line.replace("MAX_Health_Point ","");
            MAX_Health_Point = Integer.parseInt(line);
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
        }
        catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error.");
            alert.setContentText("Wrong preferences file.");
            alert.showAndWait();
        }
    }

}

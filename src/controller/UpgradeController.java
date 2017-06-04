package controller;

import Program.Program;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Model;
import preferences.Preferences;
import view.View;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Mateusz on 2017-03-28.
 */
public class UpgradeController {
    public static Model model;
    public static View view;
    public static MenuController menuController;
    public static GameController gameController;

    /*@FXML */public ArrayList<RadioButton> MissionsRadioButtons;
   /* @FXML */public ArrayList<RadioButton> AchievementsRadioButtons;


    Integer mapSelected = 1;

    @FXML    RadioButton rb1;
    @FXML    RadioButton rb2;
    @FXML    RadioButton rb3;
    @FXML    RadioButton rb4;
    @FXML    RadioButton rb5;
    @FXML    RadioButton rb6;
    @FXML    RadioButton rb7;
    @FXML    RadioButton rb8;
    @FXML    RadioButton rb9;
    @FXML    RadioButton rb10;
    @FXML    RadioButton rbm1;
    @FXML    RadioButton rbm2;
    @FXML    RadioButton rbm3;
    @FXML    RadioButton rbm4;
    @FXML    RadioButton rbm5;
    @FXML    RadioButton rbm6;
    @FXML    RadioButton rbm7;
    @FXML    RadioButton rbm8;
    @FXML    RadioButton rbm9;
    @FXML    RadioButton rbm10;

    public void setRadioButtons(){
        MissionsRadioButtons = new ArrayList<>();
        AchievementsRadioButtons = new ArrayList<>();
        MissionsRadioButtons.add(rb1);
        MissionsRadioButtons.add(rb2);
        MissionsRadioButtons.add(rb3);
        MissionsRadioButtons.add(rb4);
        MissionsRadioButtons.add(rb5);
        MissionsRadioButtons.add(rb6);
        MissionsRadioButtons.add(rb7);
        MissionsRadioButtons.add(rb8);
        MissionsRadioButtons.add(rb9);
        MissionsRadioButtons.add(rb10);
        AchievementsRadioButtons.add(rbm1);
        AchievementsRadioButtons.add(rbm2);
        AchievementsRadioButtons.add(rbm3);
        AchievementsRadioButtons.add(rbm4);
        AchievementsRadioButtons.add(rbm5);
        AchievementsRadioButtons.add(rbm6);
        AchievementsRadioButtons.add(rbm7);
        AchievementsRadioButtons.add(rbm8);
        AchievementsRadioButtons.add(rbm9);
        AchievementsRadioButtons.add(rbm10);
        for(RadioButton rb: AchievementsRadioButtons)
            rb.setDisable(true);
    }
    public void deleteCurrentPlayer(){
        model.deleteCurrentPlayer();
    }

    public void setPlayer() {
        setRadioButtons();
        final ToggleGroup mtg = new ToggleGroup();
        for(RadioButton rb: MissionsRadioButtons)
            rb.setToggleGroup(mtg);

        setPlayerAchievements();
        setPlayerMissions();
        setPlayerSkills();

    }
    private void setPlayerAchievements() {
        //System.out.println(model.currentPlayer.getName()+" achievments "+model.currentPlayer.achievements.toString());
        Integer achievementNumber = 0;
        for(Integer val: model.currentPlayer.achievements){
            if(val == 1)
                selectRadioButtonAchievements(achievementNumber);
            achievementNumber++;
        }
    }

    private void setPlayerMissions() {
        //System.out.println(model.currentPlayer.getName()+ " missions    "+model.currentPlayer.missions.toString());
        Integer missionNumber = 0;
        for(Integer val: model.currentPlayer.missions){
            if(val == 1)
                selectRadioButtonMissions(missionNumber);
            missionNumber++;
        }
    }

    @FXML TextField bulletPowerPoints;
    @FXML TextField bulletPowerMaxPoints;
    @FXML Button bulletPowerIncreaseButton;
    @FXML TextField bulletSpeedPoints;
    @FXML TextField bulletSpeedMaxPoints;
    @FXML Button bulletSpeedIncreaseButton;
    @FXML TextField bombRangePoints;
    @FXML TextField bombRangeMaxPoints;
    @FXML Button bombRangeIncreaseButton;
    @FXML TextField bombPowerPoints;
    @FXML TextField bombPowerMaxPoints;
    @FXML Button bombPowerIncreaseButton;
    @FXML TextField healthPoints;
    @FXML TextField healthMaxPoints;
    @FXML Button healthIncreaseButton;
    @FXML TextField coinsTextField;

    private void setPlayerSkills() {
        bulletPowerPoints.setText(String.valueOf(model.currentPlayer.getBulletPower()));
        bulletSpeedPoints.setText(String.valueOf(model.currentPlayer.getBulletSpeed()));
        bombRangePoints.setText(String.valueOf(model.currentPlayer.getBombRange()));
        bombPowerPoints.setText(String.valueOf(model.currentPlayer.getBombPower()));
        healthPoints.setText(String.valueOf(model.currentPlayer.getHealthPoints()));

        coinsTextField.setText(String.valueOf(model.currentPlayer.getCoins()));

        bulletPowerMaxPoints.setText(Program.preferences.getBullet_Power_MAX().toString());
        bulletSpeedMaxPoints.setText(Program.preferences.getBullet_Speed_MAX().toString());
        bombRangeMaxPoints.setText(Program.preferences.getBomb_Range_MAX().toString());
        bombPowerMaxPoints.setText(Program.preferences.getBomb_Power_MAX().toString());
        healthMaxPoints.setText(Program.preferences.getHealth_Point_MAX().toString());

    }
    @FXML
    private void selectRadioButtonMissions(Integer missionNumber) {
        MissionsRadioButtons.get(missionNumber).setSelected(true);
        MissionsRadioButtons.get(missionNumber).setDisable(true);
    }
    @FXML
    private void selectRadioButtonAchievements(Integer achievementNumber) {
        AchievementsRadioButtons.get(achievementNumber).setSelected(true);
    }

    @FXML
    public void pressPLAYButton() throws IOException {
        gameController = view.setGameView();
        Program.setGameController(gameController);
        gameController.setMapNumber(mapSelected);
        gameController.drawMap();
    }
    @FXML
    public void pressBackToMENUButton() throws IOException {
        menuController = view.setMenuView();
        Program.setMenuController(menuController);
        model.currentPlayer.clear();
    }


}

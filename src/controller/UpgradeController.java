package controller;

import Program.Program;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Model;
import preferences.Preferences;
import view.View;

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

    public ArrayList<RadioButton> MissionsRadioButtons;
    public ArrayList<RadioButton> AchievementsRadioButtons;


    Integer mapSelected;

    @FXML
    RadioButton rb1;
    @FXML
    RadioButton rb2;
    @FXML
    RadioButton rb3;
    @FXML
    RadioButton rb4;
    @FXML
    RadioButton rb5;

    @FXML
    RadioButton rbm1;
    @FXML
    RadioButton rbm2;
    @FXML
    RadioButton rbm3;
    @FXML
    RadioButton rbm4;
    @FXML
    RadioButton rbm5;

    ToggleGroup missionsToggleGroup;
    @FXML
    Label playerNameLabel;
    @FXML
    TextField towerRangePoints;
    @FXML
    TextField towerRangePointsMax;
    @FXML
    Button towerRangeIncreaseButton;
    @FXML
    TextField bulletPowerPoints;
    @FXML
    TextField bulletPowerMaxPoints;
    @FXML
    Button bulletPowerIncreaseButton;
    @FXML
    TextField bulletSpeedPoints;
    @FXML
    TextField bulletSpeedMaxPoints;
    @FXML
    Button bulletSpeedIncreaseButton;
    @FXML
    TextField bombRangePoints;
    @FXML
    TextField bombRangeMaxPoints;
    @FXML
    Button bombRangeIncreaseButton;
    @FXML
    TextField bombPowerPoints;
    @FXML
    TextField bombPowerMaxPoints;
    @FXML
    Button bombPowerIncreaseButton;
    @FXML
    TextField healthPoints;
    @FXML
    TextField healthMaxPoints;
    @FXML
    Button healthIncreaseButton;
    @FXML
    TextField pointsTextField;
    @FXML
    TextField costTextField;

    public void setRadioButtons() {
        MissionsRadioButtons = new ArrayList<>();
        AchievementsRadioButtons = new ArrayList<>();
        MissionsRadioButtons.add(rb1);
        MissionsRadioButtons.add(rb2);
        MissionsRadioButtons.add(rb3);
        MissionsRadioButtons.add(rb4);
        MissionsRadioButtons.add(rb5);
        AchievementsRadioButtons.add(rbm1);
        AchievementsRadioButtons.add(rbm2);
        AchievementsRadioButtons.add(rbm3);
        AchievementsRadioButtons.add(rbm4);
        AchievementsRadioButtons.add(rbm5);
        for (RadioButton rb : AchievementsRadioButtons)
            rb.setDisable(true);
    }

    public void setPlayer() {
        setRadioButtons();
        setPlayerNameLabel();
        setPlayerAchievements();
        setPlayerMissions();
        setPlayerSkills();
        setToggleGroup();
    }

    private void setPlayerNameLabel() {
        playerNameLabel.setText(Model.currentPlayer.getName());
    }

    private void setToggleGroup() {
        missionsToggleGroup = new ToggleGroup();
        for (RadioButton rb : MissionsRadioButtons) {
            rb.setToggleGroup(missionsToggleGroup);
        }
        missionsToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (missionsToggleGroup.getSelectedToggle() != null) {
                    RadioButton rb = (RadioButton) missionsToggleGroup.getSelectedToggle();
                    mapSelected = Integer.parseInt(rb.getText());
                }
            }
        });
    }

    private void setPlayerAchievements() {
        Integer achievementNumber = 0;
        for (Integer val : model.currentPlayer.achievements) {
            if (val == 1)
                selectRadioButtonAchievements(achievementNumber);
            achievementNumber++;
        }
    }

    private void setPlayerMissions() {
        Integer missionNumber = 0;
        for (Integer val : model.currentPlayer.missions) {
            if (val == 1)
                selectRadioButtonMissions(missionNumber);
            missionNumber++;
        }
    }

    private void setPlayerSkills() {
        towerRangePoints.setText(String.valueOf(Model.currentPlayer.getTowerRangeLevel()));
        bulletPowerPoints.setText(String.valueOf(Model.currentPlayer.getBulletDamageLevel()));
        bulletSpeedPoints.setText(String.valueOf(Model.currentPlayer.getHitRateTimeLevel()));
        bombRangePoints.setText(String.valueOf(Model.currentPlayer.getBombRangeLevel()));
        bombPowerPoints.setText(String.valueOf(Model.currentPlayer.getBombPowerLevel()));
        healthPoints.setText(String.valueOf(Model.currentPlayer.getHealthPointsLevel()));

        pointsTextField.setText(String.valueOf(Model.currentPlayer.getPoints()));

        towerRangePointsMax.setText(String.valueOf(Preferences.getTowerRangeLevels()));
        bulletPowerMaxPoints.setText(String.valueOf(Preferences.getBulletDamageLevels()));
        bulletSpeedMaxPoints.setText(String.valueOf(Preferences.getHitRateTimeLevels()));
        bombRangeMaxPoints.setText(String.valueOf(Preferences.getBombRangeLevels()));
        bombPowerMaxPoints.setText(String.valueOf(Preferences.getBombPowerLevels()));
        healthMaxPoints.setText(String.valueOf(Preferences.getHealthPointsLevels()));

    }

    @FXML
    private void selectRadioButtonMissions(Integer missionNumber) {
        MissionsRadioButtons.get(missionNumber).setStyle("-fx-background-color: green");
    }

    @FXML
    private void selectRadioButtonAchievements(Integer achievementNumber) {
        AchievementsRadioButtons.get(achievementNumber).setStyle("-fx-background-color: green");
    }

    @FXML
    public void pressPLAYButton() throws IOException {
        if (mapSelected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Map selected");
            alert.setHeaderText(null);
            alert.setContentText("Choose Map");
            alert.showAndWait();
            return;
        }
        gameController = view.setGameView();
        Program.setGameController(gameController);
        gameController.setMapNumber(mapSelected);
        gameController.drawMap();
    }

    @FXML
    public void pressBackToMENUButton() throws IOException {
        model.saveCurrentPlayer();
        menuController = view.setMenuView();
        Program.setMenuController(menuController);
        model.currentPlayer.clear();
    }


    public void checkIncreaseButtons() {
        if (Model.currentPlayer.getTowerRangeLevel() >= Preferences.getTowerRangeLevels())
            towerRangeIncreaseButton.setDisable(true);
        if (Model.currentPlayer.getBulletDamageLevel() >= Preferences.getBulletDamageLevels())
            bulletPowerIncreaseButton.setDisable(true);
        if (Model.currentPlayer.getHitRateTimeLevel() >= Preferences.getHitRateTimeLevels())
            bulletSpeedIncreaseButton.setDisable(true);
        if (Model.currentPlayer.getBombPowerLevel() >= Preferences.getBombPowerLevels())
            bombPowerIncreaseButton.setDisable(true);
        if (Model.currentPlayer.getBombRangeLevel() >= Preferences.getBombRangeLevels())
            bombRangeIncreaseButton.setDisable(true);
        if (Model.currentPlayer.getHealthPointsLevel() >= Preferences.getHealthPointsLevels())
            healthIncreaseButton.setDisable(true);
        setPlayerSkills();
        updateCostTextFieldState();
    }

    public void increaseTowerRange(ActionEvent actionEvent) {
        int cost = Preferences.getTowerRangeIncreaseCosts(Model.currentPlayer.getTowerRangeLevel() - 1);
        if (Model.currentPlayer.getPoints() - cost < 0)
            return;
        Model.currentPlayer.setTowerRange(50);
        Model.currentPlayer.executePointsCost(cost);
        checkIncreaseButtons();
    }

    public void increaseBulletDamage(ActionEvent actionEvent) {
        int cost = Preferences.getBulletDamageIncreaseCosts(Model.currentPlayer.getBulletDamageLevel() - 1);
        if (Model.currentPlayer.getPoints() - cost < 0)
            return;
        Model.currentPlayer.setBulletDamage(10);
        Model.currentPlayer.executePointsCost(cost);
        checkIncreaseButtons();
    }

    public void increaseBulletSpeed(ActionEvent actionEvent) {
        int cost = Preferences.getHitRateTimeIncreaseCosts(Model.currentPlayer.getHitRateTimeLevel() - 1);
        if (Model.currentPlayer.getPoints() - cost < 0)
            return;
        Model.currentPlayer.setHitRateTime(-500000000);
        Model.currentPlayer.executePointsCost(cost);
        checkIncreaseButtons();
    }

    public void increaseBombRange(ActionEvent actionEvent) {
        int cost = Preferences.getBombRangeIncreaseCosts(Model.currentPlayer.getBombRangeLevel() - 1);
        if (Model.currentPlayer.getPoints() - cost < 0)
            return;
        Model.currentPlayer.setBombRange(50);
        Model.currentPlayer.executePointsCost(cost);
        checkIncreaseButtons();
    }

    public void increaseBombPower(ActionEvent actionEvent) {
        int cost = Preferences.getBombDamageIncreaseCosts(Model.currentPlayer.getBombPowerLevel() - 1);
        if (Model.currentPlayer.getPoints() - cost < 0)
            return;
        Model.currentPlayer.setBombDamage(50);
        Model.currentPlayer.executePointsCost(cost);
        checkIncreaseButtons();
    }

    public void increaseHealthPoints(ActionEvent actionEvent) {
        int cost = Preferences.getHealthPointsIncreaseCosts(Model.currentPlayer.getHealthPointsLevel() - 1);
        if (Model.currentPlayer.getPoints() - cost < 0)
            return;
        Model.currentPlayer.increaseHealthPoints(1);
        Model.currentPlayer.executePointsCost(cost);
        checkIncreaseButtons();
    }

    private void updateCostTextFieldState() {
        towerRangeIncreaseButtonMouseAction();
        bulletDamageIncreaseButtonMouseAction();
        bulletSpeedIncreaseButtonMouseAction();
        bombRangeIncreaseButtonMouseAction();
        bombPowerIncreaseButtonMouseAction();
        healthPointsIncreaseButtonMouseAction();
    }

    public void towerRangeIncreaseButtonMouseAction() {
        costTextField.setText(String.valueOf(Preferences.getTowerRangeIncreaseCosts(Model.currentPlayer.getTowerRangeLevel() - 1)));
    }

    public void bulletDamageIncreaseButtonMouseAction() {
        costTextField.setText(String.valueOf(Preferences.getBulletDamageIncreaseCosts(Model.currentPlayer.getBulletDamageLevel() - 1)));

    }

    public void bulletSpeedIncreaseButtonMouseAction() {
        costTextField.setText(String.valueOf(Preferences.getHitRateTimeIncreaseCosts(Model.currentPlayer.getHitRateTimeLevel() - 1)));

    }

    public void bombRangeIncreaseButtonMouseAction() {
        costTextField.setText(String.valueOf(Preferences.getBombRangeIncreaseCosts(Model.currentPlayer.getBombRangeLevel() - 1)));

    }

    public void bombPowerIncreaseButtonMouseAction() {
        costTextField.setText(String.valueOf(Preferences.getBombDamageIncreaseCosts(Model.currentPlayer.getBombPowerLevel() - 1)));

    }

    public void healthPointsIncreaseButtonMouseAction() {
        costTextField.setText(String.valueOf(Preferences.getHealthPointsIncreaseCosts(Model.currentPlayer.getHealthPointsLevel() - 1)));

    }
}

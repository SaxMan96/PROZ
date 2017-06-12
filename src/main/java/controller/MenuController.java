package main.java.controller;

import main.java.Program.Program;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import main.java.model.Model;
import main.java.view.View;

import java.io.IOException;
import java.util.ArrayList;

public class MenuController {

    public static Model model;
    public static View view;
    private static UpgradeController upgradeController;
    private static boolean arePlayersSet = false;

    @FXML
    private
    VBox playerChooseVBox;
    @FXML
    private
    Button newPlayerSelected;

    @FXML
    private
    AnchorPane playerChoosePane;
    @FXML
    private
    TextField newPlayerNameTextField;
    @FXML
    private
    AnchorPane creditsPane;
    @FXML
    private
    AnchorPane instructionsPane;

    public void pressPLAYButton() throws IOException {
        showPlayersToChoose();
        playerChoosePane.setVisible(!playerChoosePane.isVisible());
    }

    private void showPlayersToChoose() throws IOException {
        if (arePlayersSet)
            return;
        model.clearExistingPlayers();
        model.loadPlayersFromFile();
        VBox vbox = new VBox(14);
        vbox.setMaxWidth(2 * newPlayerSelected.getWidth());
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;
        for (Pair pair : Model.existingPlayers) {
            button = new Button((String) pair.getValue());
            button.setStyle("-fx-font: 22 arial;");
            buttons.add(button);
            vbox.getChildren().add(button);
        }
        vbox.setAlignment(Pos.CENTER);
        VBox.setMargin(vbox, Insets.EMPTY);
        playerChooseVBox.getChildren().add(vbox);
        arePlayersSet = true;
        for (Button b : buttons) {
            b.setOnAction((event) -> {
                try {
                    continueWithPlayerSelected(b.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void continueWithPlayerSelected(String playerName) throws IOException {
        model.setExistingPlayer(playerName);
        setUpgradeController();
    }

    private void setUpgradeController() throws IOException {
        upgradeController = view.setUpgradeView();
        Program.setUpgradeController(upgradeController);
        arePlayersSet = false;
        upgradeController.checkIncreaseButtons();
    }

    public void setNewPlayer() throws IOException {
        if (newPlayerNameTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Name");
            alert.setHeaderText(null);
            alert.setContentText("set player name");
            alert.showAndWait();
            return;
        }
        model.setNewPlayer(newPlayerNameTextField.getText());
        setUpgradeController();

    }

    public void pressCreditsButton() throws IOException {
        creditsPane.setVisible(!creditsPane.isVisible());
    }

    public void pressInstructionsButton() throws IOException {
        instructionsPane.setVisible(!instructionsPane.isVisible());
    }

    @FXML
    public void pressExitButton() {
        System.exit(0);
    }

}
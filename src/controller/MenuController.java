package controller;

import Program.Program;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import model.Model;
import view.View;

import java.io.IOException;
import java.util.ArrayList;

public class MenuController {

    public static Model model;
    public static View view;
    public static UpgradeController upgradeController;
    private static boolean arePlayersSet = false;

    @FXML
    VBox playerChooseVBox;
    @FXML
    Button newPlayerSelected;

    @FXML
    AnchorPane playerChoosePane;

    public void pressPLAYButton() throws IOException {
        showPlayersToChoose();
        playerChoosePane.setVisible(!playerChoosePane.isVisible());

    }

    @FXML
    TextField newPlayerNameTextField;

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


    public void showPlayersToChoose() {
        if (arePlayersSet)
            return;
        VBox vbox = new VBox(14);
        vbox.setMaxWidth(2 * newPlayerSelected.getWidth());
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;
        for (Pair pair : model.existingPlayers) {
            button = new Button((String) pair.getValue());
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

    @FXML
    AnchorPane creditsPane;

    public void pressCreditsButton() throws IOException {
        creditsPane.setVisible(!creditsPane.isVisible());
    }

    @FXML
    AnchorPane instructionsPane;

    public void pressInstructionsButton() throws IOException {
        instructionsPane.setVisible(!instructionsPane.isVisible());
    }

    @FXML
    public void pressExitButton() {
        System.exit(0);
    }


}
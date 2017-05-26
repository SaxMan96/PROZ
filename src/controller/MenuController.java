package controller;

import Program.Program;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import model.Model;
import model.Player;
import view.View;

import java.beans.EventHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController {

    public static Model model;
    public static View view;
    public static UpgradeController upgradeController;
    private static boolean arePlayersSet = false;

   /* public void set() throws IOException {
        view = Program.view;
        model = Program.model;
        //view.setMenuView(Program.menuController);
        view.setMenuView();
    }*/


/*    public void actionPerformed(ActionEvent e) {
        if ("Hypercom".equals(modelName.getSelectedItem())){
            termName.setModel(hSpecModel);
        } else if ("Deja Voo".equals(modelName.getSelectedItem())){
            termName.setModel(dSpecModel);
        } else if ("Nurit".equals(modelName.getSelectedItem())){
            termName.setModel(nSpecModel);
        } else if ("Verifone".equals(modelName.getSelectedItem())){
            termName.setModel(vSpecModel);
        } else {
            termName.setModel(slctAbove);
        }
    }*/

    @FXML
    AnchorPane playerChoosePane;
    public void pressPLAYButton() throws IOException{
       showPlayersToChoose();
       playerChoosePane.setVisible(!playerChoosePane.isVisible());

    }
    @FXML
    TextField newPlayerNameTextField;
    public void setNewPlayer() throws IOException {
        if (newPlayerNameTextField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Name");
            alert.setHeaderText(null);
            alert.setContentText("set player name");
            alert.showAndWait();
            return;
        }
        model.setNewPlayer(newPlayerNameTextField.getText());
        upgradeController = view.setUpgradeView();
        Program.setUpgradeController(upgradeController);
        arePlayersSet = false;
    }

    @FXML
    VBox playerChooseVBox;
    @FXML
    Button newPlayerSelected;
    public void showPlayersToChoose(){
        if(arePlayersSet)
            return;
        VBox vbox = new VBox(14);
        //vbox.setStyle("-fx-background-color: gray;");
        vbox.setMaxWidth(2*newPlayerSelected.getWidth());
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;
        for(Pair pair: model.existingPlayers){
            button = new Button((String) pair.getValue());

            buttons.add(button);
            vbox.getChildren().add(button);
        }
        vbox.setAlignment(Pos.CENTER);
        VBox.setMargin(vbox, Insets.EMPTY);
        playerChooseVBox.getChildren().add(vbox);
        arePlayersSet = true;
        for(Button b: buttons){ b.setOnAction((event)->{
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
        upgradeController = view.setUpgradeView();
        Program.setUpgradeController(upgradeController);
        arePlayersSet = false;
    }

    @FXML
    public void pressContinueButton(){
        System.out.println("Continue Game");
    }

    @FXML
    AnchorPane creditsPane;
    public void pressCreditsButton ()throws IOException{ creditsPane.setVisible(!creditsPane.isVisible());  }
    @FXML
    AnchorPane instructionsPane;
    public void pressInstructionsButton ()throws IOException{
        instructionsPane.setVisible(!instructionsPane.isVisible());
    }
    @FXML
    public void pressExitButton(){
        System.exit(0);
    }


}
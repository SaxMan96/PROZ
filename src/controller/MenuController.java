package controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import model.Model;
import model.Player;
import view.View;

import java.io.IOException;
import java.util.ArrayList;

public class MenuController {

    public static Model model;
    public static View view;
    UpgradeController upgradeController;

    public void set() throws IOException {
    view = Controller.view;
    model = Controller.model;
    view.setMenuView();

    }


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
    public void pressNewGameButton() throws IOException{
       /*upgradeController = new UpgradeController();
       upgradeController.set();*/
       playerChoosePane.setVisible(!playerChoosePane.isVisible());
       // setPlayersToChoose();
       // wywołanie z tego miejsca daje java.lang.reflect.InvocationTargetException
    }
    @FXML
    public void setNewPlayer(){
        model.setNewPlayer();
    }

    @FXML
    VBox playerChooseVBox;
    public void showPlayersToChoose(){
        VBox vbox = new VBox(14);
        vbox.setStyle("-fx-background-color: gray; -fx-borders-color: black");
        for(Pair pair: model.existingPlayers)
            vbox.getChildren().add(new Button((String) pair.getValue()));
        vbox.setAlignment(Pos.CENTER);
        VBox.setMargin(vbox, Insets.EMPTY);
        playerChooseVBox.getChildren().add(vbox);
    }
    @FXML
    public void newGame() throws IOException {
        upgradeController = new UpgradeController();
        upgradeController.set();
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
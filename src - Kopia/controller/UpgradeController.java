package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Model;
import program.Main;
import view.View;

import java.io.File;
import java.io.IOException;

/**
 * Created by Mateusz on 2017-03-28.
 */
public class UpgradeController {
    Model model;
    View view;
    MenuController menuController;
    GameController gameController;

    public void start(ActionEvent event) throws IOException {
        view = Main.view;
        model = Main.model;
        view.setUpgradeView(event);
    }
    /*public void initialize(ActionEvent event, Stage stage) throws IOException {
        System.out.println("New Game");
        Parent root = FXMLLoader.load(getClass().getResource(".."+File.separator+"view" + File.separator + "upgradeView.fxml"));

        Scene gameScene = new Scene(root);
        Stage gameStage =(Stage)((Node) event.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();
    }*/
    @FXML
    public void pressPLAYButton(ActionEvent event) throws IOException {
        gameController = new GameController();
        gameController.set(event);
    }
    @FXML
    public void pressBackToMENUButton(ActionEvent event) throws IOException {


       /* BorderPane mainScreen;
        try {
            mainScreen = (BorderPane) FXMLLoader.load(Main.class .getResource("../viev/menuView"));
            Main.main(mainScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        menuController = new MenuController();
        menuController.set(event);
/*        System.out.println("MENU");
        Parent root = FXMLLoader.load(getClass().getResource(".." + File.separator + "view" + File.separator + "menuView.fxml"));
        Scene gameScene = new Scene(root);
        Stage gameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();*/
    }


}

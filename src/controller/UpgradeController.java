package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Created by Mateusz on 2017-03-28.
 */
public class UpgradeController {
    @FXML
    public void pressPLAYButton(ActionEvent event) throws IOException {
        System.out.println("PLAY");
        Parent root = FXMLLoader.load(getClass().getResource(".." + File.separator + "view" + File.separator + "gameView.fxml"));
        Scene gameScene = new Scene(root);
        Stage gameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();
    }
    @FXML
    public void pressBackToMENUButton(ActionEvent event) throws IOException {
        System.out.println("MENU");
        Parent root = FXMLLoader.load(getClass().getResource(".." + File.separator + "view" + File.separator + "menuView.fxml"));
        Scene gameScene = new Scene(root);
        Stage gameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();
    }
}

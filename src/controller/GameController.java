package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Created by Mateusz on 2017-03-27.
 */
public class GameController {

    @FXML Pane PausedPane;
    public void pressMenuButton(ActionEvent event)throws IOException{
        PausedPane.setVisible(!PausedPane.isVisible());
        //  isGamePaused = content.isVisible();
    }
    @FXML
    public void pressQuitButton(ActionEvent event) throws IOException {
        System.out.println("Quit Game");
        Parent root = FXMLLoader.load(getClass().getResource(".." + File.separator + "view" + File.separator + "UpgradeView.fxml"));
        Scene gameScene = new Scene(root);
        Stage gameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();
    }
}

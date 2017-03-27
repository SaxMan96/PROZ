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

public class MenuController {
    @FXML
    public void pressNewGameButton(ActionEvent event) throws IOException{
        System.out.println("New Game");
        Parent root = FXMLLoader.load(getClass().getResource(".."+File.separator+"view" + File.separator + "gameView.fxml"));
        Scene gameScene = new Scene(root);
        Stage gameStage =(Stage)((Node) event.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();
    }
    @FXML
    public void pressContinueButton(ActionEvent event){
        System.out.println("Continue Game");
    }
    @FXML
    public void pressCreditsButton(ActionEvent event){
        System.out.println("Credits");
    }
    @FXML
    public void pressControlerButton(ActionEvent event){
        System.out.println("Control");
    }
    @FXML
    public void pressExitButton(ActionEvent event){
        System.out.println("exit");
        System.exit(0);
    }
}
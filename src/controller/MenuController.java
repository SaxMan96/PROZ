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

public class MenuController {
    @FXML
    public void pressNewGameButton(ActionEvent event) throws IOException{
        System.out.println("New Game");
        Parent root = FXMLLoader.load(getClass().getResource(".."+File.separator+"view" + File.separator + "upgradeView.fxml"));
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
    Pane Credits;
    public void pressCreditsButton (ActionEvent event)throws IOException{
        Credits.setVisible(!Credits.isVisible());
        //   = Credits.isVisible();
    }
    @FXML
    Pane Instructions;
    public void pressInstructionsButton (ActionEvent event)throws IOException{
        Instructions.setVisible(!Instructions.isVisible());
        //  isGamePaused = Credits.isVisible();
    }

    @FXML
    public void pressControlerButton(ActionEvent event){
        System.out.println("Control");
    }
    @FXML
    public void pressExitButton(ActionEvent event){
        System.exit(0);
    }
}
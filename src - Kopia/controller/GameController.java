package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;
import program.Main;
import view.View;

import java.io.File;
import java.io.IOException;

/**
 * Created by Mateusz on 2017-03-27.
 */
public class GameController {
    Model model;
    View view;
    private UpgradeController upgradeController;
/*
    private Parent parent;
    private Stage stage;
    private Scene scene;

    public GameController () {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(".." + File.separator + "view" + File.separator + "gameView.fxml"));
        fxmlLoader.setController(this);
        try {
            parent = (Parent) fxmlLoader.load();
            scene = new Scene(parent, 1000, 800);
        } catch (IOException e) {
            // manage the exception
        }
        this.stage = stage;
        stage.setScene(scene);
        stage.setMaxWidth(760);
        stage.hide();
        stage.show();
    }
*/
    @FXML Pane PausedPane;
    public void pressMenuButton()throws IOException{
        PausedPane.setVisible(!PausedPane.isVisible());
        //  isGamePaused = content.isVisible();
    }
    @FXML
    public void pressQuitButton(ActionEvent event) throws IOException {
        System.out.println("Quit Game");

        upgradeController = new UpgradeController();
        upgradeController.start(event);

        /*Parent root = FXMLLoader.load(getClass().getResource(".." + File.separator + "view" + File.separator + "UpgradeView.fxml"));
        Scene gameScene = new Scene(root);
        Stage gameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();*/
    }

    public void set(ActionEvent event) throws IOException {
        view = Main.view;
        model = Main.model;
        view.setGameView(event);
    }
}

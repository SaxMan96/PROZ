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

    public void set() throws IOException {
        view = Controller.view;
        model = Controller.model;
        view.setUpgradeView();
    }

    @FXML
    public void pressPLAYButton() throws IOException {
        gameController = new GameController();
        gameController.set();
    }
    @FXML
    public void pressBackToMENUButton() throws IOException {
        menuController = new MenuController();
        menuController.set();
    }
}

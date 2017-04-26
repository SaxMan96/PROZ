package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import model.Model;
import view.View;
import java.io.IOException;

/**
 * Created by Mateusz on 2017-03-27.
 */
public class GameController {
    Model model;
    View view;
    private UpgradeController upgradeController;

    @FXML Pane PausedPane;
    public void pressMenuButton()throws IOException{
        PausedPane.setVisible(!PausedPane.isVisible());
    }
    @FXML
    public void pressQuitButton(ActionEvent event) throws IOException {
        upgradeController = new UpgradeController();
        upgradeController.set();
    }

    public void set(ActionEvent event) throws IOException {
        view = Controller.view;
        model = Controller.model;
        view.setGameView(event);
    }
}

package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import model.Model;
import view.View;

import java.io.IOException;

public class MenuController {

    Model model;
    View view;
    UpgradeController upgradeController;

   public void set() throws IOException {
        view = Controller.view;
        model = Controller.model;
        view.setMenuView();
    }

    @FXML
    public void pressNewGameButton() throws IOException{
        upgradeController = new UpgradeController();
        upgradeController.set();
    }

    @FXML
    public void pressContinueButton(){
        System.out.println("Continue Game");
    }
    @FXML
    Pane Credits;
    public void pressCreditsButton ()throws IOException{
        Credits.setVisible(!Credits.isVisible());
    }
    @FXML
    Pane Instructions;
    public void pressInstructionsButton ()throws IOException{
        Instructions.setVisible(!Instructions.isVisible());
    }
    @FXML
    public void pressExitButton(){
        System.exit(0);
    }


}
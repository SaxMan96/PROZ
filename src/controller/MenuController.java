package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import model.Model;
import program.Main;
import view.View;

import java.io.IOException;

public class MenuController {

    Model model;
    View view;
   /* public void start() throws IOException {
        view = Main.view;
        model = Main.model;
        view.setView();
        view.setMenuView();
    }*/
    public void set(ActionEvent event) throws IOException {
        view = Main.view;
        model = Main.model;
        view.setMenuView(event);
    }
    /*Stage stage;
    public void start(Stage primaryStage) throws IOException{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/menuView.fxml"));
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }*/

    /*public void initialize(Stage primaryStage) throws IOException {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/menuView.fxml"));
        stage.setTitle("Tower Defence");
        stage.setScene(new Scene(root, 600, 400));
        stage.setMinHeight(400);
        stage.setMinWidth(600);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        stage.setMaxHeight(height);
        stage.setMaxWidth(width);

        stage.show();
    }*/

    @FXML
    public void pressNewGameButton(ActionEvent event) throws IOException{

        UpgradeController upgradeController = new UpgradeController();
        upgradeController.start(event);

/*
        System.out.println("New Game");
        Parent root = FXMLLoader.load(getClass().getResource(".."+File.separator+"view" + File.separator + "upgradeView.fxml"));
        Scene gameScene = new Scene(root);
        Stage gameStage =(Stage)((Node) event.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();
*/
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
    public void pressExitButton(ActionEvent event){
        System.exit(0);
    }


}
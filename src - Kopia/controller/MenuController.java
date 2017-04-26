package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Model;
import program.Main;
import view.View;

import java.io.IOException;

public class MenuController {

    Model model;
    View view;
    UpgradeController upgradeController;

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
        setDimensions();

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
    GridPane GridPane;
    private void setDimensions() throws IOException{
        System.out.println("setDimensions");
        //GridPane.setMaxWidth(view.getWidth());
        //GridPane.setMaxHeight(view.getHeight());

        /*FXMLLoader loader = new FXMLLoader();
        Parent rootNode = null;
        rootNode = loader.load(getClass().getResource("homeMhs_fxml.fxml"));
        Label dua = new Label("2");
        Pane pane = new Pane();
        pane.getChildren().add(dua);
        ((BorderPane) rootNode).setCenter(pane);*/
    }

    @FXML
    public void pressNewGameButton(ActionEvent event) throws IOException{

        upgradeController = new UpgradeController();
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
    public void pressContinueButton(){
        System.out.println("Continue Game");
    }
    @FXML
    Pane Credits;
    public void pressCreditsButton ()throws IOException{
        Credits.setVisible(!Credits.isVisible());
        //   = Credits.isVisible();
    }
    @FXML
    Pane Instructions;
    public void pressInstructionsButton ()throws IOException{
        Instructions.setVisible(!Instructions.isVisible());
        //  isGamePaused = Credits.isVisible();
    }
    @FXML
    public void pressExitButton(){
        System.exit(0);
    }


}
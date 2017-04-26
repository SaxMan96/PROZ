package view;

import controller.Controller;
import controller.UpgradeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Mateusz on 2017-03-31.
 */


public class View {

    private static Stage Primarystage;
    private static Scene ActualScene;
    private static double width;
    private static double height;

    public static double getWidth(){
        return width;
    }
    public static double getHeight(){
        return height;
    }

    public void init(Stage stage){
        Primarystage = stage;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        System.out.println("Width: "+width+"Height: "+height);
    }

    public void setStartView() throws IOException {
        //stage = new Stage();
        Primarystage.setTitle("Tower Defence");
        Primarystage.setMinHeight(400);
        Primarystage.setMinWidth(600);


        Parent root = FXMLLoader.load(getClass().getResource(".." + File.separator + "view" + File.separator + "startView.fxml"));
        ActualScene = new Scene(root);

        Primarystage.setScene(ActualScene);
        Primarystage.sizeToScene();
        Primarystage.show();
    }
    public void setMenuView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(".." + File.separator + "view" + File.separator + "menuView.fxml"));
        ActualScene.setRoot(root);
        Primarystage.setScene(ActualScene);

        Primarystage.setMinHeight(height);
        Primarystage.setMinWidth(width);
        Primarystage.setMaximized(true);
        Primarystage.setFullScreen(true);
        Primarystage.setResizable(false);
    }

    public void setUpgradeView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(".."+File.separator+"view" + File.separator + "upgradeView.fxml"));
        ActualScene.setRoot(root);
        Primarystage.setScene(ActualScene);
    }

    public void setGameView(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(".." + File.separator + "view" + File.separator + "gameView.fxml"));
        ActualScene.setRoot(root);
        Primarystage.setScene(ActualScene);
    }
}


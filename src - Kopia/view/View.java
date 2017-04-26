package view;

import controller.Controller;
import controller.UpgradeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import program.Main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Mateusz on 2017-03-31.
 */


public class View {

    static Controller controller;
    private static Stage stage;
    private static double width;
    private static double height;

    public static double getWidth(){
        return width;
    }
    public static double getHeight(){
        return height;
    }

    public static void init(){
        controller = Main.controller;
        stage = new Stage();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        System.out.println("Width: "+width+"Height: "+height);
    }



    public void setStartView() throws IOException {
        //stage = new Stage();
        stage.setTitle("Tower Defence setStartView");
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setResizable(false);

        Parent root = FXMLLoader.load(getClass().getResource(".." + File.separator + "view" + File.separator + "startView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
    public void setMenuView(ActionEvent event ) throws IOException {
        Stage stage;
        Parent root = FXMLLoader.load(getClass().getResource(".." + File.separator + "view" + File.separator + "menuView.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.setMaximized(true);
        stage.setResizable(false);
        stage.setTitle("Tower Defence setMenuView");
        stage.show();

    }

    public void setUpgradeView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(".."+File.separator+"view" + File.separator + "upgradeView.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.setMaximized(true);
        stage.setResizable(false);
        stage.setTitle("Tower Defence setUpgradeView");
        stage.show();
    }

    public void setGameView(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource(".." + File.separator + "view" + File.separator + "gameView.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Tower Defence setGameView");
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
    }
}


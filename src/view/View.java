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



    public static void start(){
        controller = Main.controller;
    }

    public void setView() {
        stage = new Stage();
        stage.setTitle("Tower Defence");
        stage.setMinHeight(400);
        stage.setMinWidth(600);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        stage.setMaxHeight(height);
        stage.setMaxWidth(width);

        stage.setMaximized(true);
    }
 /*   public void setMenuView( ) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(".." + File.separator + "view" + File.separator + "menuView.fxml"));
        Scene gameScene = new Scene(root);
        stage.setScene(gameScene);

        stage.setMaximized(true);
        stage.show();

    }*/




    public void setStartView() throws IOException {
        stage = new Stage();
        stage.setTitle("Tower Defence");
        stage.setMinHeight(400);
        stage.setMinWidth(600);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        stage.setMaxHeight(height);
        stage.setMaxWidth(width);

        Parent root = FXMLLoader.load(getClass().getResource(".." + File.separator + "view" + File.separator + "startView.fxml"));
        Scene gameScene = new Scene(root);
        stage.setScene(gameScene);

        stage.show();
    }
    public void setMenuView(ActionEvent event ) throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(".." + File.separator + "view" + File.separator + "menuView.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.setMaximized(true);
        stage.show();

    }

    public void setUpgradeView(ActionEvent event) throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(".."+File.separator+"view" + File.separator + "upgradeView.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.setMaximized(true);
        stage.show();
    }
}


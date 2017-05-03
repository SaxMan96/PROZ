package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Map;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Mateusz on 2017-03-31.
 */


public class View {

    public static Stage Primarystage;
    public static Scene ActualScene;
    private static double Width;
    private static double Height;
    private static Map gameMap;

    public static double getWidth(){
        return Width;
    }
    public static double getHeight(){
        return Height;
    }

    public void init(Stage stage){
        Primarystage = stage;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Width = screenSize.getWidth();
        Height = screenSize.getHeight();
        Primarystage.initStyle(StageStyle.UNDECORATED);
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

        Primarystage.setMinHeight(Height);
        Primarystage.setMinWidth(Width);
        Primarystage.setMaximized(true);
        Primarystage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        Primarystage.setFullScreen(true);
        Primarystage.setResizable(false);

    }
    public void setUpgradeView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(".."+File.separator+"view" + File.separator + "upgradeView.fxml"));
        ActualScene.setRoot(root);
        Primarystage.setScene(ActualScene);
    }
    public void setGameView() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(".." + File.separator + "view" + File.separator + "gameView.fxml"));
        ActualScene.setRoot(root);
        Primarystage.setScene(ActualScene);

    }

    public void loadMap(Map m)
    {
        gameMap = m;

    }

}

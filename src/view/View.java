package view;

import controller.GameController;
import controller.MenuController;
import controller.UpgradeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Map;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Mateusz on 2017-03-31.
 */


public class View {

    public static Stage PrimaryStage;
    public static Scene ActualScene;
    private static double Width;
    private static double Height;
    /*private static Map map;

    public static void setMap(Map map) {
        View.map = map;
    }*/

    public static double getWidth(){
        return Width;
    }
    public static double getHeight(){
        return Height;
    }

    public void init(Stage stage){
        PrimaryStage = stage;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Width = screenSize.getWidth();
        Height = screenSize.getHeight();
        PrimaryStage.initStyle(StageStyle.UNDECORATED);
    }

    public MenuController setMenuView() throws IOException {
        PrimaryStage.setTitle("Tower Defence");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(".." + File.separator + "view" + File.separator + "menuView.fxml"));
        Parent root = loader.load();
        MenuController controller = loader.getController();
        ActualScene = new Scene(root);
        PrimaryStage.setScene(ActualScene);

        PrimaryStage.setMinHeight(Height);
        PrimaryStage.setMinWidth(Width);

        PrimaryStage.setMaximized(true);
        PrimaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        PrimaryStage.setFullScreen(true);
        PrimaryStage.setResizable(false);

        PrimaryStage.setScene(ActualScene);
        PrimaryStage.sizeToScene();
        PrimaryStage.show();

        return controller;
    }
    public UpgradeController setUpgradeView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(".." + File.separator + "view" + File.separator + "upgradeView.fxml"));
        Parent root = loader.load();
        UpgradeController controller = loader.getController();
        ActualScene = new Scene(root);

        PrimaryStage.setScene(ActualScene);
        PrimaryStage.setFullScreen(true);

        return controller;
    }
    public GameController setGameView() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(".." + File.separator + "view" + File.separator + "gameView.fxml"));
        StackPane root = loader.load();
        GameController controller = loader.getController();
        ActualScene = new Scene(root);

        PrimaryStage.setScene(ActualScene);
        PrimaryStage.setFullScreen(true);

        return controller;
    }

}

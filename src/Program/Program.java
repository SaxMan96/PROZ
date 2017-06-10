package Program;

import controller.GameController;
import controller.MenuController;
import controller.UpgradeController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import preferences.Preferences;
import view.View;

import java.io.IOException;

/**
 * Created by Mateusz on 2017-05-04.
 */
public class Program extends Application {
    /*
    Timer
    QueueEvent
    */
    public static MenuController menuController;
    public static GameController gameController;
    public static UpgradeController upgradeController;
    public static Model model;
    public static View view;
    public static Preferences preferences;
    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    public static void setUpgradeController(UpgradeController controller) {
        upgradeController = controller;
        upgradeController.model = model;
        upgradeController.view = view;
        upgradeController.setPlayer();
    }

    public static void setGameController(GameController controller) {
        gameController = controller;
        gameController.model = model;
        gameController.view = view;
        gameController.primaryStage = stage;
    }

    @Override
    public void init() throws IOException {
        model = new Model();
        view = new View();
        preferences = new Preferences();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        preferences.loadFromFile();
        model.set();
        view.init(primaryStage);
        setMenuController(view.setMenuView());
        stage = primaryStage;

//        setUpgradeController(view.setUpgradeView());
//        setGameController(view.setGameView());
//        gameController.setMapNumber(1);
//        gameController.drawMap();

    }

    public static void setMenuController(MenuController controller) {
        menuController = controller;
        menuController.model = model;
        menuController.view = view;
    }
}

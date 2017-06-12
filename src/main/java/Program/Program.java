package main.java.Program;

import main.java.controller.GameController;
import main.java.controller.MenuController;
import main.java.controller.UpgradeController;
import javafx.application.Application;
import javafx.stage.Stage;
import main.java.model.Model;
import main.java.view.View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Program extends Application {
    /*
    Timer
    QueueEvent
    */
    private static MenuController menuController;
    private static GameController gameController;
    private static UpgradeController upgradeController;
    public static Model model;
    private static View view;
    private static Preferences preferences;
    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    public static void setUpgradeController(UpgradeController controller) {
        upgradeController = controller;
        UpgradeController.model = model;
        UpgradeController.view = view;
        upgradeController.setPlayer();
    }

    public static void setGameController(GameController controller) {
        gameController = controller;
        GameController.model = model;
        GameController.view = view;
        gameController.primaryStage = stage;
    }

    @Override
    public void init(){
        model = new Model();
        view = new View();
        preferences = new Preferences();

    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, UnsupportedEncodingException {
        try {
            preferences.loadFromFile();
            model.set();
            view.init(primaryStage);
            setMenuController(view.setMenuView());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = primaryStage;
    }

    public static void setMenuController(MenuController controller) {
        menuController = controller;
        MenuController.model = model;
        MenuController.view = view;
    }
}

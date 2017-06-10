package Program;

import controller.GameController;
import controller.MenuController;
import controller.UpgradeController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import preferences.Preferences;
import view.View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;


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
            PrintWriter writer;
            String filePath = "";
            URL resource = Model.class.getClassLoader().getResource("Errors/StackTrace.txt");
            if (resource != null) {
                filePath = resource.toString().substring(resource.toString().indexOf("file:") + 6);
            }
            writer = new PrintWriter(filePath, "UTF-8");
            e.printStackTrace(writer);
        }
        stage = primaryStage;
    }

    public static void setMenuController(MenuController controller) {
        menuController = controller;
        MenuController.model = model;
        MenuController.view = view;
    }
}

package main.java.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.model.Model;
import main.java.view.View;
import java.io.IOException;
import java.net.URISyntaxException;

public class Controller extends Application {
    private static MenuController menuController;
    public static Model model = new Model();
    private static View view = new View();

    @Override
    public void init() throws IOException {
        model = new Model();
        model.loadPlayersFromFile();
        view = new View();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        view.init(primaryStage);
    }

    public void startGame() throws IOException {
//        menuController = new MenuController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

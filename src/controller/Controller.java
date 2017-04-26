package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import view.View;
import java.io.IOException;

/**
 * Created by Mateusz on 2017-03-31.
 */
public class Controller extends Application
{
    public static MenuController menuController;
    public static Model model = new Model();
    public static View view = new View();

    @Override
    public void start(Stage primaryStage) throws Exception {
        model = new Model();
        view = new View();
        view.init(primaryStage);
        view.setStartView();
    }

    public void startGame() throws IOException {
        menuController = new MenuController();
        menuController.set();
    }

    public static void main(String[] args) {
        launch(args);
    }
    /*Timer
    QueueEvent
*/
}

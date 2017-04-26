package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import program.Main;
import view.View;
import controller.MenuController;

import javax.swing.*;
import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * Created by Mateusz on 2017-03-31.
 */
public class Controller extends Application
{
    public static MenuController menuController = new MenuController();
    public static Model model = new Model();
    public static View view = new View();


    @Override
    public void start(Stage primaryStage) throws Exception {
        menuController = new MenuController();
        menuController.set();
        view.init();
        view.setStartView();
    }

    // Metoda start(ActionEvent event) jest wywoływana jakoś w Main.java z racji że dziedziczy po Application
    public void start(ActionEvent event) throws IOException{
         menuController = new MenuController();
         menuController.set(event);
    }
    public static void main(String[] args) {
        launch(args);
    }
    /*Timer
    QueueEvent
*/
}



/*

public class Main extends Application {

    public static Controller controller = new Controller();
    public static Model model = new Model();
    public static View view = new View();

    @Override
    public void start( Stage stage ) throws IOException {
        controller.init();

    }
    public static void main(String[] args) {
        launch(args);
    }
}

*/

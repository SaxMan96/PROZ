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
    /*private final SoundManager sound;
    private final Timer timer;*/

    public static MenuController menuController;
    public static Model model = new Model();
    public static View view = new View();
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
        menuController = new MenuController();



    }

    public static void main(String[] args) {
        launch(args);
    }
    /*Timer
    QueueEvent
*/
}

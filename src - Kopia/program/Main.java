package program;

import controller.Controller;
import javafx.scene.layout.BorderPane;
import model.Model;
import view.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Main extends Application {

    public static Controller controller = new Controller();
    public static Model model = new Model();
    public static View view = new View();

    @Override
    public void start( Stage stage ) throws IOException {
        controller.init();
        //model.start(); do starej klasy przeciążającej Thread
        //view.init();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

package controller;

import model.Model;
import program.Main;
import view.View;

import javax.swing.*;
import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * Created by Mateusz on 2017-03-31.
 */
public class Controller
{
    /*public void initialize(Stage primaryStage) throws IOException {
        MenuController menuController = new MenuController();
        menuController.initialize(primaryStage);
    };*/
    Model model;
    View view;
    MenuController menuController;


    public void init() throws IOException {
        view = Main.view;
        model = Main.model;
        view.start();
        view.setStartView();
        //model.setMenuModel();
    }
    public void start(ActionEvent event) throws IOException{
        view = Main.view;
        model = Main.model;
        view.start();
        view.setView();
        menuController = new MenuController();
        menuController.set(event);
    }


    /*Timer
    QueueEvent
*/
}

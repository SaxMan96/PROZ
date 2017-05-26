package controller;

import Program.Program;
import com.sun.deploy.ui.ImageLoader;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

import model.Map;
import model.Model;
import model.Tile;
import view.View;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Mateusz on 2017-03-27.
 */
public class GameController {
    public static Model model;
    public static View view;
    public static UpgradeController upgradeController;
    public Map map;
    public Stage primaryStage;

    public void setMapNumber(Integer mapNumber) throws IOException {
        model.loadMap(mapNumber);
    }
    @FXML Pane PausedPane;
    public void pressMenuButton()throws IOException{
        PausedPane.setVisible(!PausedPane.isVisible());
    }
    @FXML
    public void pressQuitButton() throws IOException {
        upgradeController = view.setUpgradeView();
        Program.setUpgradeController(upgradeController);
    }
    @FXML Canvas mainCanvas;
    public void drawMap() {
        map = model.getMap();
        map.drawMap(mainCanvas);
        mainCanvas.setVisible(true);
    }

    /*private class Timer implements Runnable {
        final static long TIMEOUT = 10;

        *//**
         * Puts update model events into queue
         *//*
        @Override
        public void run()
        {
            while (true)
            {
                try
                {
                    blockingQueue.put(new TimerEvent());
                    Thread.sleep(TIMEOUT);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }*/
}

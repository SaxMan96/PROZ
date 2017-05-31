package controller;

import Program.Program;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

import javafx.util.Duration;
import jdk.nashorn.internal.ir.Block;
import model.Enemy;
import model.Map;
import model.Model;
import model.Path;
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
    public GameLoop  gameLoop;
    public Stage primaryStage;

    @FXML Pane PausedPane;
    @FXML Canvas mainCanvas;
    @FXML Canvas secondCanvas;
    @FXML TextField livesTextField;
    @FXML CheckBox xd;
    public void setMapNumber(Integer mapNumber) throws IOException {
        model.loadMap(mapNumber);
    }

    public void pressMenuButton()throws IOException{
        PausedPane.setVisible(!PausedPane.isVisible());
    }
    @FXML
    public void pressQuitButton() throws IOException {
        upgradeController = view.setUpgradeView();
        Program.setUpgradeController(upgradeController);
    }

    public void drawMap() {
        View.drawMap(model.getMap(), mainCanvas);

//        xd.setLayoutX(Map.startXPosition);
//        xd.setLayoutY(Map.startYPosition);
//
//        Path path = new Path(model.getMap());
//
//        path.generatePolyline();
//        Polyline polyline = new Polyline();
//        polyline = path.getPolyline();
//
//        PathTransition transition = new PathTransition();
//        transition.setNode(xd);
//        transition.setDuration(Duration.seconds(3));
//        transition.setPath(polyline);
//        transition.setCycleCount(PathTransition.INDEFINITE);
//        transition.play();
    }

    public void gameLoopStart() {
//        gameLoop = new GameLoop(mainCanvas, model);
//        gameLoop.run();
        gameLoop = new GameLoop(mainCanvas, model);
        gameLoop.startGame();
    }
    /*private class Timer implements Runnable {
        final static long TIMEOUT = 10;


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

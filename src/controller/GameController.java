package controller;

import Program.Program;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import model.*;
import view.View;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Cursor.*;
import static javafx.scene.Cursor.HAND;
import static javafx.scene.Cursor.MOVE;

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
    @FXML TextField scoreTextField;
    @FXML TextField cashTextField;
    @FXML TextField costTextField;

    @FXML CheckBox xd;
    @FXML AnchorPane towersShopAnchorPane;
    @FXML AnchorPane leftPane;
    @FXML AnchorPane mainAnchorPane;
    private Player currentPlayer;

    public void setMapNumber(Integer mapNumber) throws IOException {
        model.loadMap(mapNumber);
        map = model.getMap();
        currentPlayer = model.getCurrentPlayer();
    }

    public void pressMenuButton()throws IOException{
        if(gameLoop != null)
            gameLoop.interrupt();
        PausedPane.toFront();
        PausedPane.setVisible(!PausedPane.isVisible());
    }
    @FXML
    public void pressQuitButton() throws IOException {
        if(gameLoop!=null)
            gameLoop.endGame();
        upgradeController = view.setUpgradeView();
        Program.setUpgradeController(upgradeController);
    }

    public void drawMap() {
        View.drawMap(model.getMap(), mainCanvas);
        setTowersShop();

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



    private void setTowersShop() {
        //final Tower[] fireTower = new Tower[1];
        ArrayList<Tower> towerList = model.getTowerList();
        View.setTowerShop(towersShopAnchorPane, mainAnchorPane, towerList, map, mainCanvas, costTextField, livesTextField,scoreTextField,cashTextField);
    }

    public void resumeButtonClicked(ActionEvent actionEvent) {
        System.out.print("resume");
        if(gameLoop != null)
            gameLoop.nottify();
        PausedPane.setVisible(!PausedPane.isVisible());
    }

    public void restartButtonClicked(ActionEvent actionEvent) {
        PausedPane.setVisible(!PausedPane.isVisible());
        gameLoop.restartGame();
    }
}

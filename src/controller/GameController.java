package controller;

import Program.Program;
import javafx.animation.PathTransition;
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
    @FXML CheckBox xd;
    @FXML AnchorPane towersShopAnchorPane;
    @FXML AnchorPane leftPane;
    @FXML AnchorPane mainStackPane;

    public void setMapNumber(Integer mapNumber) throws IOException {
        model.loadMap(mapNumber);
        map = model.getMap();
    }

    public void pressMenuButton()throws IOException{
        PausedPane.toFront();
        PausedPane.setVisible(!PausedPane.isVisible());
    }
    @FXML
    public void pressQuitButton() throws IOException {
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
        //gameLoop.startGame();

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
        ArrayList<Tower> towerList = new ArrayList<>();

        ImageView shopImage = new ImageView(new Image("file:C:\\Users\\Mateusz\\Desktop\\PROZ\\Graphics\\tower.png"));
        towersShopAnchorPane.getChildren().add(shopImage);
        ImageView towerBasicImage = (ImageView)  towersShopAnchorPane.getChildren().get(0);
        towerBasicImage.setX(10);
        towerBasicImage.setY(10);

        ImageView iv = new ImageView(new Image("file:C:\\Users\\Mateusz\\Desktop\\PROZ\\Graphics\\tower.png"));
        mainStackPane.getChildren().add(iv);

        ImageView moveableTowerImage = (ImageView)  mainStackPane.getChildren().get(mainStackPane.getChildren().size()-1);
        moveableTowerImage.getParent().toFront();
        Bounds boundsInScene = towersShopAnchorPane.localToScene(towersShopAnchorPane.getBoundsInLocal());
        moveableTowerImage.setX(boundsInScene.getMinX()+10);
        moveableTowerImage.setY(boundsInScene.getMinY()+10);

        class Delta { double x, y; }
        final Delta dragDelta = new Delta();
        dragDelta.x = 0;
        dragDelta.y = 0;

        moveableTowerImage.setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.
            towerList.add(new Tower());
            dragDelta.x = moveableTowerImage.getLayoutX() - mouseEvent.getSceneX();
            dragDelta.y = moveableTowerImage.getLayoutY() - mouseEvent.getSceneY();
            moveableTowerImage.setCursor(MOVE);
        });
        moveableTowerImage.setOnMouseReleased(mouseEvent -> {
            int xCoor = (int) (mouseEvent.getSceneX() - mouseEvent.getSceneX()%map.getTileWidth());
            int yCoor = (int) (mouseEvent.getSceneY() - mouseEvent.getSceneY()%map.getTileWidth());
            Tile.TileType type = map.getTileTypeFromCoordinates(xCoor, yCoor);
            if(type == Tile.TileType.TOWER_PLACE){
                towerList.get(towerList.size()-1).set(xCoor,yCoor);
                View.drawTower(mainCanvas, towerList.get(towerList.size()-1));
            }
            moveableTowerImage.setLayoutX(0);
            moveableTowerImage.setLayoutY(0);
            moveableTowerImage.setCursor(HAND);
        });
        moveableTowerImage.setOnMouseDragged(mouseEvent -> {
            moveableTowerImage.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
            moveableTowerImage.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
        });
        moveableTowerImage.setOnMouseEntered(mouseEvent -> moveableTowerImage.setCursor(HAND));

    }
}

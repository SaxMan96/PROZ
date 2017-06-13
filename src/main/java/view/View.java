package main.java.view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.Program.Program;
import main.java.controller.GameController;
import main.java.controller.MenuController;
import main.java.controller.UpgradeController;
import main.java.model.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static javafx.scene.Cursor.HAND;
import static javafx.scene.Cursor.MOVE;

public class View {

    private static Stage PrimaryStage;
    private static Scene ActualScene;
    private static double Width;
    private static double Height;
    private static Model model = Program.model;
    private static Player currentPlayer = model.currentPlayer;

    public static void drawLaser(Canvas canvas, Laser l) {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(l.getSourceLayoutX(), l.getSourceLayoutY(), l.getTargetLayoutX(), l.getTargetLayoutY());
    }

    public void drawMap(Map map, Canvas canvas) {
        String tileName = null;
        for (Tile tile : map.getGrid()) {
            switch (tile.getTileType()) {
                case START:
                    tileName = "startTile.png";
                    break;
                case USABLE:
                    tileName = "usableTile.png";
                    break;
                case TOWER_PLACE:
                    tileName = "towerTile.png";
                    break;
                case FINISH:
                    tileName = "finishTile.png";
                    break;
                case PATH:
                    tileName = "pathTile.png";
                    break;
            }
            URL resource = getClass().getResource("/main/resources/Graphics/" + tileName);
            Image image = null;
            if (resource != null) {
                image = new Image(resource.toExternalForm());
            }
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(image, tile.getXPosition(), tile.getYPosition());
        }
    }

    public void drawEnemy(Canvas canvas, Enemy e) {
        String fileName = "enemy.png";

        URL resource = getClass().getResource("/main/resources/Graphics/" + fileName);
        Image graphic = null;
        if (resource != null) {
            graphic = new Image(resource.toExternalForm());
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillRoundRect(e.getLayoutX(), e.getLayoutY() - 9, Map.getTileWidth(), 7, 7, 7);
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRoundRect(e.getLayoutX(), e.getLayoutY() - 9, (double) Map.getTileWidth() * ((double) e.getCurrentHealth() / e.getMaxHealth()), 7, 7, 7);
        gc.drawImage(graphic, e.getLayoutX(), e.getLayoutY());
    }

    public void gameWin(int points, Canvas canvas, Pane pausedPane) {
        model.currentPlayer.gainPoints(points);
        String fileName = "win.png";
        URL resource = getClass().getResource("/main/resources/Graphics/" + fileName);
        ImageView graphic = null;
        if (resource != null) {
            graphic = new ImageView(resource.toExternalForm());
            graphic.relocate(44, 18);
        }
        pausedPane.getChildren().add(graphic);
    }

    public void gameLoose(Canvas canvas, Pane pausedPane) {
        String fileName = "loose.png";
        URL resource = getClass().getResource("/main/resources/Graphics/" + fileName);
        ImageView graphic = null;
        if (resource != null) {
            graphic = new ImageView(resource.toExternalForm());
            graphic.relocate(44, 18);
        }
        pausedPane.getChildren().add(graphic);
    }

    public void init(Stage stage) {
        PrimaryStage = stage;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Width = screenSize.getWidth();
        Height = screenSize.getHeight();
        PrimaryStage.initStyle(StageStyle.UNDECORATED);
    }

    public MenuController setMenuView() throws IOException {
        PrimaryStage.setTitle("Tower Defence");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/menuView.fxml"));
        Parent root = loader.load();
        MenuController controller = loader.getController();
        ActualScene = new Scene(root);
        PrimaryStage.setScene(ActualScene);
        PrimaryStage.setMinHeight(Height);
        PrimaryStage.setMinWidth(Width);
        PrimaryStage.setMaximized(true);
        PrimaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        PrimaryStage.setFullScreen(false);
        PrimaryStage.setResizable(false);

        PrimaryStage.setScene(ActualScene);
        PrimaryStage.sizeToScene();
        PrimaryStage.show();

        return controller;
    }

    public UpgradeController setUpgradeView() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/upgradeView.fxml"));
        Parent root = loader.load();
        UpgradeController controller = loader.getController();
        ActualScene = new Scene(root);

        PrimaryStage.setScene(ActualScene);
        PrimaryStage.setFullScreen(true);

        return controller;
    }

    public GameController setGameView() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/gameView.fxml"));

        AnchorPane root = loader.load();
        GameController controller = loader.getController();
        ActualScene = new Scene(root);

        PrimaryStage.setScene(ActualScene);
        PrimaryStage.setFullScreen(false);

        return controller;
    }

    private static void updateViewToPlayerStatus(TextField livesTextField, TextField scoreTextField, TextField cashTextField) {
        livesTextField.setText(String.valueOf(model.currentPlayer.getCurrentHealthPoints()));
        scoreTextField.setText(String.valueOf(model.currentPlayer.getPoints()));
        cashTextField.setText(String.valueOf(model.currentPlayer.getCoins()));
    }

    public void drawTower(Canvas canvas, Tower t) {
        URL resource = getClass().getResource("/main/resources/Graphics/tower.png");
        Image graphic = null;
        if (resource != null) {
            graphic = new Image(resource.toExternalForm());
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(graphic, t.getLayoutX(), t.getLayoutY());
    }

    public void setTowerShop(AnchorPane towersShopAnchorPane, AnchorPane mainAnchorPane,
                             ArrayList<Tower> towerList, Map map, Canvas mainCanvas,
                             TextField costTextField, TextField livesTextField,
                             TextField scoreTextField, TextField cashTextField) {

        updateViewToPlayerStatus(livesTextField, scoreTextField, cashTextField);

        URL resource = getClass().getResource("/main/resources/Graphics/tower.png");
        ImageView shopImage = null;

        if (resource != null) {

            shopImage = new ImageView(new Image(resource.toExternalForm()));
        }
        towersShopAnchorPane.getChildren().addAll(shopImage);
        ImageView towerBasicImage = (ImageView) towersShopAnchorPane.getChildren().get(towersShopAnchorPane.getChildren().size()-1);

        towerBasicImage.setX(10);
        towerBasicImage.setY(10);

        resource = getClass().getResource("/main/resources/Graphics/tower.png");
        ImageView iv = null;
        if (resource != null) {
            iv = new ImageView(String.valueOf(resource));
        }
        mainAnchorPane.getChildren().add(iv);

        ImageView movableTowerImage = (ImageView) mainAnchorPane.getChildren().get(mainAnchorPane.getChildren().size() - 1);
        movableTowerImage.getParent().toFront();
        Bounds boundsInScene = towersShopAnchorPane.localToScene(towersShopAnchorPane.getBoundsInLocal());

        movableTowerImage.setX(boundsInScene.getMinX() + 10);
        movableTowerImage.setY(boundsInScene.getMinY() + 10);

        class Delta {
            private double x, y;
        }
        final Delta dragDelta = new Delta();
        dragDelta.x = 0;
        dragDelta.y = 0;

        movableTowerImage.setOnMousePressed(mouseEvent -> {

            dragDelta.x = movableTowerImage.getLayoutX() - mouseEvent.getSceneX();
            dragDelta.y = movableTowerImage.getLayoutY() - mouseEvent.getSceneY();
            movableTowerImage.setCursor(MOVE);
        });
        movableTowerImage.setOnMouseReleased(mouseEvent -> {
            int xCoor = (int) (mouseEvent.getSceneX() - mouseEvent.getSceneX() % Map.getTileWidth());
            int yCoor = (int) (mouseEvent.getSceneY() - mouseEvent.getSceneY() % Map.getTileWidth());
            Tile.TileType type = map.getTileTypeFromCoordinates(xCoor, yCoor);
            if (type == Tile.TileType.TOWER_PLACE && currentPlayer.getCoins() >= Tower.getCost()) {
                Tower t = new Tower();
                t.set(xCoor, yCoor);
                towerList.add(t);
                drawTower(mainCanvas, towerList.get(towerList.size() - 1));
                currentPlayer.executeCoinsCost(Tower.getCost());
                updateViewToPlayerStatus(livesTextField, scoreTextField, cashTextField);
            }
            costTextField.setText("");
            movableTowerImage.setLayoutX(0);
            movableTowerImage.setLayoutY(0);
            movableTowerImage.setCursor(HAND);
        });
        movableTowerImage.setOnMouseDragged(mouseEvent -> {
            movableTowerImage.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
            movableTowerImage.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
        });
        movableTowerImage.setOnMouseEntered(mouseEvent -> {
            movableTowerImage.setCursor(HAND);
            costTextField.setText(Tower.getCost().toString());
        });
        movableTowerImage.setOnMouseExited(mouseEvent -> costTextField.setText(""));
    }



    public void setBombShop(AnchorPane bombsShopAnchorPane, AnchorPane mainAnchorPane,
                            ArrayList<Bomb> bombList, Map map, Canvas mainCanvas, TextField costTextField, TextField livesTextField,
                            TextField scoreTextField, TextField cashTextField) {

        updateViewToPlayerStatus(livesTextField, scoreTextField, cashTextField);

        URL resource = getClass().getResource("/main/resources/Graphics/bomb.png");
        ImageView shopImage = null;

        if (resource != null) {
            shopImage = new ImageView(new Image(resource.toExternalForm()));
        }
        bombsShopAnchorPane.getChildren().addAll(shopImage);
        ImageView bombBasicImage = (ImageView) bombsShopAnchorPane.getChildren().get(bombsShopAnchorPane.getChildren().size()-1);
        bombBasicImage.setX(68);
        bombBasicImage.setY(10);

        resource = getClass().getResource("/main/resources/Graphics/bomb.png");
        ImageView iv = null;
        if (resource != null) {
            iv = new ImageView(String.valueOf(resource));
        }
        mainAnchorPane.getChildren().add(iv);

        ImageView movableBombImage = (ImageView) mainAnchorPane.getChildren().get(mainAnchorPane.getChildren().size() - 1);
        movableBombImage.getParent().toFront();
        Bounds boundsInScene = bombsShopAnchorPane.localToScene(bombsShopAnchorPane.getBoundsInLocal());
        movableBombImage.setX(boundsInScene.getMinX() + 68);
        movableBombImage.setY(boundsInScene.getMinY() + 10);

        class Delta {
            private double x, y;
        }
        final Delta dragDelta = new Delta();
        dragDelta.x = 0;
        dragDelta.y = 0;

        movableBombImage.setOnMousePressed(mouseEvent -> {

            dragDelta.x = movableBombImage.getLayoutX() - mouseEvent.getSceneX();
            dragDelta.y = movableBombImage.getLayoutY() - mouseEvent.getSceneY();
            movableBombImage.setCursor(MOVE);
        });
        movableBombImage.setOnMouseReleased(mouseEvent -> {
            int xCoor = (int) (mouseEvent.getSceneX());
            int yCoor = (int) (mouseEvent.getSceneY());
            if ( currentPlayer.getCoins() >= Bomb.getCost()) {
                Bomb bomb = new Bomb();
                bomb.set(xCoor, yCoor);
                bomb.setExplodeNanoTime(System.nanoTime());
                bomb.setActive(true);
                bombList.add(bomb);
                drawBombs(mainCanvas, bombList.get(bombList.size() - 1));
                currentPlayer.executeCoinsCost(Bomb.getCost());
                updateViewToPlayerStatus(livesTextField, scoreTextField, cashTextField);
            }
            costTextField.setText("");
            movableBombImage.setLayoutX(0);
            movableBombImage.setLayoutY(0);
            movableBombImage.setCursor(HAND);
        });
        movableBombImage.setOnMouseDragged(mouseEvent -> {
            movableBombImage.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
            movableBombImage.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
        });
        movableBombImage.setOnMouseEntered(mouseEvent -> {
            movableBombImage.setCursor(HAND);
            costTextField.setText(Bomb.getCost().toString());
        });
        movableBombImage.setOnMouseExited(mouseEvent -> costTextField.setText(""));
    }

    public void drawBombs(Canvas canvas, Bomb bomb) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillOval(bomb.getLayoutX()-bomb.getRange(), bomb.getLayoutY()-bomb.getRange(), 2*bomb.getRange(),2*bomb.getRange());
    }
}

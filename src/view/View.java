package view;

import controller.GameController;
import controller.MenuController;
import controller.UpgradeController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import javafx.scene.canvas.Canvas;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.Cursor.HAND;
import static javafx.scene.Cursor.MOVE;
import static model.Model.currentPlayer;

/**
 * Created by Mateusz on 2017-03-31.
 */


public class View {

    public static Stage PrimaryStage;
    public static Scene ActualScene;
    private static double Width;
    private static double Height;
    /*private static Map map;

    public static void setMap(Map map) {
        View.map = map;
    }*/

    public static double getWidth(){
        return Width;
    }
    public static double getHeight(){
        return Height;
    }

    public void init(Stage stage){
        PrimaryStage = stage;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Width = screenSize.getWidth();
        Height = screenSize.getHeight();
        PrimaryStage.initStyle(StageStyle.UNDECORATED);
    }

    public MenuController setMenuView() throws IOException {
        PrimaryStage.setTitle("Tower Defence");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(".." + File.separator + "view" + File.separator + "menuView.fxml"));
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(".." + File.separator + "view" + File.separator + "upgradeView.fxml"));
        Parent root = loader.load();
        UpgradeController controller = loader.getController();
        ActualScene = new Scene(root);

        PrimaryStage.setScene(ActualScene);
        PrimaryStage.setFullScreen(true);

        return controller;
    }
    public GameController setGameView() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(".." + File.separator + "view" + File.separator + "gameView.fxml"));
        AnchorPane root = loader.load();
        GameController controller = loader.getController();
        ActualScene = new Scene(root);

        PrimaryStage.setScene(ActualScene);
        PrimaryStage.setFullScreen(false);

        return controller;
    }

    public static void drawMap(Map map, Canvas canvas) {
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
            //Image image = new Image(getClass().getResource(tileName).toExternalForm());
            Image image = new Image("file:Graphics/" + tileName);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(image, tile.getXPosition(), tile.getYPosition());
            /*gc.setTextAlign(TextAlignment.CENTER);
            gc.setTextBaseline(VPos.CENTER);
            gc.fillText(i.toString(),tile.getXPosition()+24,tile.getYPosition()+24);
            System.out.println(i);
            i++;*/
        }
    }

    public static void drawEnemy(Canvas canvas, Enemy e){
        String fileName = "enemy.png";
        ImageView graphic = new ImageView("file:Graphics/" + fileName);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillRect(e.getLayoutX(), e.getLayoutY()- 9, Map.getTileWidth(), 7);
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(e.getLayoutX(), e.getLayoutY()- 9,(double)Map.getTileWidth()*((double)e.getCurrentHealth()/e.getMaxHealth()),7);
        gc.drawImage(graphic.getImage(), e.getLayoutX(),e.getLayoutY());
    }

    public static void drawTower(Canvas canvas, Tower t){
        String fileName = "tower.png";
        ImageView graphic = new ImageView(new Image("file:Graphics/"+fileName ));
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(graphic.getImage(), t.getLayoutX(),t.getLayoutY());
    }

    public static void gameWin() {
    }

    public static void setTowerShop(AnchorPane towersShopAnchorPane, AnchorPane mainAnchorPane,
                                    ArrayList<Tower> towerList, Map map, Canvas mainCanvas,
                                    TextField costTextField, TextField livesTextField,
                                    TextField scoreTextField, TextField cashTextField) {

        updateViewToPlayerStatus(livesTextField, scoreTextField, cashTextField);

        ImageView shopImage = new ImageView(new Image("file:C:\\Users\\Mateusz\\Desktop\\PROZ\\Graphics\\tower.png"));
        towersShopAnchorPane.getChildren().add(shopImage);
        ImageView towerBasicImage = (ImageView)  towersShopAnchorPane.getChildren().get(0);
        towerBasicImage.setX(10);
        towerBasicImage.setY(10);

        ImageView iv = new ImageView(new Image("file:C:\\Users\\Mateusz\\Desktop\\PROZ\\Graphics\\tower.png"));
        mainAnchorPane.getChildren().add(iv);

        ImageView movableTowerImage = (ImageView)  mainAnchorPane.getChildren().get(mainAnchorPane.getChildren().size()-1);
        movableTowerImage.getParent().toFront();
        Bounds boundsInScene = towersShopAnchorPane.localToScene(towersShopAnchorPane.getBoundsInLocal());
        movableTowerImage.setX(boundsInScene.getMinX()+10);
        movableTowerImage.setY(boundsInScene.getMinY()+10);

        class Delta { double x, y; }
        final Delta dragDelta = new Delta();
        dragDelta.x = 0;
        dragDelta.y = 0;

        movableTowerImage.setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.

            //costTextField.setText(Tower.getCost().toString());

            dragDelta.x = movableTowerImage.getLayoutX() - mouseEvent.getSceneX();
            dragDelta.y = movableTowerImage.getLayoutY() - mouseEvent.getSceneY();
            movableTowerImage.setCursor(MOVE);
        });
        movableTowerImage.setOnMouseReleased(mouseEvent -> {
            int xCoor = (int) (mouseEvent.getSceneX() - mouseEvent.getSceneX()%map.getTileWidth());
            int yCoor = (int) (mouseEvent.getSceneY() - mouseEvent.getSceneY()%map.getTileWidth());
            Tile.TileType type = map.getTileTypeFromCoordinates(xCoor, yCoor);
            if(type == Tile.TileType.TOWER_PLACE && currentPlayer.getCoins()>=Tower.getCost()){
                Tower t = new Tower();
                t.set(xCoor,yCoor);
                towerList.add(t);
                View.drawTower(mainCanvas, towerList.get(towerList.size()-1));
                currentPlayer.executeCost(Tower.getCost());
                currentPlayer.gainPoints(Tower.getCost());

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
        movableTowerImage.setOnMouseExited(mouseEvent->{
            costTextField.setText("");
        });
    }

    private static void updateViewToPlayerStatus(TextField livesTextField, TextField scoreTextField, TextField cashTextField) {
        livesTextField.setText(String.valueOf(currentPlayer.getCurrentHealthPoints()));
        scoreTextField.setText(String.valueOf(currentPlayer.getPoints()));
        cashTextField.setText(String.valueOf(currentPlayer.getCoins()));
    }

    public static void drawLaser(Canvas canvas, Laser l) {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(l.getSourceLayoutX(),l.getSourceLayoutY(),l.getTargetLayoutX(),l.getTargetLayoutY());
    }
}

package view;

import controller.GameController;
import controller.MenuController;
import controller.UpgradeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Enemy;
import model.Map;
import model.Tile;
import javafx.scene.canvas.Canvas;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
        PrimaryStage.setFullScreen(true);
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
        StackPane root = loader.load();
        GameController controller = loader.getController();
        ActualScene = new Scene(root);

        PrimaryStage.setScene(ActualScene);
        PrimaryStage.setFullScreen(true);

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
                case NOT_USABLE:
                    tileName = "notUsableTile.png";
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
            canvas.setVisible(true);
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
        gc.drawImage(graphic.getImage(), 400,400);
    }

    public static void gameWin() {
    }
}

package Program;

import controller.GameController;
import controller.MenuController;
import controller.UpgradeController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import view.View;
import preferences.Preferences;

import java.io.File;
import java.io.IOException;

/**
 * Created by Mateusz on 2017-05-04.
 */
public class Program extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    /*
    Timer
    QueueEvent
    */
    public static MenuController menuController;
    public static GameController gameController;
    public static UpgradeController upgradeController;
    public static Model model;
    public static View view;
    public static Preferences preferences;
    public static Stage stage;

    @Override
    public void init() throws IOException {
        model = new Model();
        view = new View();
        preferences = new Preferences();

       /* menuController = MenuController.getInstance();
        upgradeController = UpgradeController.getInstance();
        gameController = GameController.getInstance();
        model = Model.getInstance();
        view = View.getInstance();*/
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        preferences.loadFromFile();
        model.set();
        view.init(primaryStage);
        setMenuController(view.setMenuView());
        stage = primaryStage;
        setUpgradeController(view.setUpgradeView());
        setGameController(view.setGameView());
        gameController.setMapNumber(2);
        gameController.drawMap();
        gameController.gameLoopStart();


//        AnimationTimer gameLoop = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//
//                // add random enemies
//                spawnEnemies( true);
//
//                // check if target is still valid
//                towers.forEach( tower -> tower.checkTarget());
//
//                // tower movement: find target
//                for( Tower tower: towers) {
//                    tower.findTarget( enemies);
//                }
//
//                // movement
//                towers.forEach(sprite -> sprite.move());
//                enemies.forEach(sprite -> sprite.move());
//
//                // check collisions
//                checkCollisions();
//
//                // update sprites in scene
//                towers.forEach(sprite -> sprite.updateUI());
//                enemies.forEach(sprite -> sprite.updateUI());
//
//                // check if sprite can be removed
//                enemies.forEach(sprite -> sprite.checkRemovability());
//
//                // remove removables from list, layer, etc
//                removeSprites( enemies);
//
//                // update score, health, etc
//                updateScore();
//            }
//
//        };
//        gameLoop.start();

        // create an animation (update & render loop)
//        new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                // update clocks
//                blueClock.update(now);
//                greenClock.update(now);
//                redClock.update(now);
//
//                // clear screen
//                gc.clearRect(0, 0, primaryStage.getWidth(),
//                        primaryStage.getHeight());
//
//                // draw blue clock
//                blueClock.draw(gc);
//                // save the origin or the current state
//                // of the Graphics Context.
//                gc.save();
//
//                // shift x coord position the width of a clock plus 20 pixels
//                gc.translate(blueClock.maxDiameter + 20, 0);
//                greenClock.draw(gc);
//
//                // shift x coord position past the first clock
//                gc.translate(blueClock.maxDiameter + 20, 0);
//                redClock.draw(gc);
//
//                // reset Graphics Context to last saved point.
//                // Translate x, y to (0,0)
//                gc.restore();
//
//            }
//        }.start();
//
//        // add the single node onto the scene graph
//        root.getChildren().add(canvas);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
    }
    public static void setMenuController(MenuController controller){
        menuController = controller;
        menuController.model = model;
        menuController.view = view;
    }
    public static void setUpgradeController(UpgradeController controller){
        upgradeController = controller;
        upgradeController.model = model;
        upgradeController.view = view;
        upgradeController.setPlayer();
    }
    public static void setGameController(GameController controller){
        gameController = controller;
        gameController.model = model;
        gameController.view = view;
        gameController.primaryStage = stage;
    }
}

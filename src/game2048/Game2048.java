package game2048;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * @author bruno.borges@oracle.com
 */
public class Game2048 extends Application {

    private GameManager gameManager;
    private Bounds gameBounds;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        gameManager = new GameManager();
        gameBounds = gameManager.getLayoutBounds();

        StackPane root = new StackPane(gameManager);
        root.setPrefSize(gameBounds.getWidth(), gameBounds.getHeight());
        ChangeListener<Number> resize = (ov, v, v1) -> {
            gameManager.setLayoutX((root.getWidth() - gameBounds.getWidth()) / 2d);
            gameManager.setLayoutY((root.getHeight() - gameBounds.getHeight()) / 2d);
        };
        root.widthProperty().addListener(resize);
        root.heightProperty().addListener(resize);

        Scene scene = new Scene(root, 600, 720);
        Font Roboto = Font.loadFont(getClass().getResourceAsStream("/resources/font/RobotoLight.ttf"), 20);
        scene.getStylesheets().add("game2048/game.css");

        addKeyHandler(scene);
        addSwipeHandlers(scene);
        
        if (isARMDevice()) {
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");
        }

        if (Platform.isSupported(ConditionalFeature.INPUT_TOUCH)) {
            scene.setCursor(Cursor.NONE);
        }

        primaryStage.setTitle("2048HOLO");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("resources/icons/2048.png")); //icona programma
        //primaryStage.initStyle(StageStyle.UNDECORATED);  //prova per il borderless
        primaryStage.setMinWidth(gameBounds.getWidth());
        primaryStage.setMinHeight(gameBounds.getHeight());
        primaryStage.show();
    }
    
    private boolean isARMDevice() {
        return System.getProperty("os.arch").toUpperCase().contains("ARM");
    }
    
    private void addKeyHandler(Scene scene) {
        scene.setOnKeyPressed(ke -> {
            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.S)) {
                gameManager.saveSession();
                return;
            }
            if (keyCode.equals(KeyCode.R)) {
                gameManager.restoreSession();
                return;
            }
            if (keyCode.isArrowKey() == false) {
                return;
            }
            Direction direction = Direction.valueFor(keyCode);
            gameManager.move(direction);
        });
    }

    private void addSwipeHandlers(Scene scene) {
        scene.setOnSwipeUp(e -> gameManager.move(Direction.UP));
        scene.setOnSwipeRight(e -> gameManager.move(Direction.RIGHT));
        scene.setOnSwipeLeft(e -> gameManager.move(Direction.LEFT));
        scene.setOnSwipeDown(e -> gameManager.move(Direction.DOWN));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

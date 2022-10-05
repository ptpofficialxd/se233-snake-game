package se233.chapter5;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se233.chapter5.controller.GameLoop;
import se233.chapter5.model.Food;
import se233.chapter5.model.Snake;
import se233.chapter5.view.Platform;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Platform platform = new Platform();
        Snake snake = new Snake(new Point2D(0,0));
        Food food = new Food();
        GameLoop gameLoop = new GameLoop(platform,snake,food);
        Scene scene = new Scene(platform,platform.WIDTH * platform.TITLE_SIZE ,platform.HEIGHT * platform.TITLE_SIZE);
        scene.setOnKeyPressed(keyEvent -> {
//            System.out.println(keyEvent.getCode());
            platform.setKey(keyEvent.getCode());
        } );
        scene.setOnKeyReleased(keyEvent -> {
            platform.setKey(null);
        } );
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        (new Thread(gameLoop)).start();
    }
}
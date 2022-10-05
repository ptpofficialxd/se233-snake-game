package se233.chapter5.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import se233.chapter5.model.Food;
import se233.chapter5.model.Snake;
import java.util.ArrayList;

public class Platform extends Pane {
    public static final int WIDTH = 30 ;
    public static  final int HEIGHT = 30 ;
    public static final int TITLE_SIZE = 10 ;
    private Canvas canvas;
    private KeyCode key;

    private Score score ;
    public Platform() {
        this.setHeight(TITLE_SIZE*HEIGHT);
        this.setWidth(TITLE_SIZE*WIDTH);
        canvas = new Canvas(TITLE_SIZE * WIDTH , TITLE_SIZE * HEIGHT);
        this.getChildren().add(canvas);

        this.score = new Score(10 , 0);
        this.getChildren().add(score);
    }

    public void render(Snake snake , Food food) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,WIDTH*TITLE_SIZE, HEIGHT*TITLE_SIZE);
        gc.setFill(Color.BLUE);

        snake.getBody().forEach( p -> {
            gc.fillRect(p.getX() * TITLE_SIZE , p.getY() * TITLE_SIZE, TITLE_SIZE,TITLE_SIZE);
        });


        if(food.isSpecialFood){
            gc.setFill(Color.GREEN);
            gc.fillRect(food.getPosition().getX() * TITLE_SIZE,food.getPosition().getY() * TITLE_SIZE, TITLE_SIZE , TITLE_SIZE);

        }else{
            gc.setFill(Color.RED);
            gc.fillRect(food.getPosition().getX() * TITLE_SIZE,food.getPosition().getY() * TITLE_SIZE, TITLE_SIZE , TITLE_SIZE);
        }

    }

    public KeyCode getKey() {
        return key;
    }

    public void setKey(KeyCode key) {
        this.key = key;
    }

    public Score getScore() {
        return this.score;
    }
}
package se233.chapter5.controller;

import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import se233.chapter5.model.Direction;
import se233.chapter5.model.Food;
import se233.chapter5.model.Snake;
import se233.chapter5.view.Platform;
import se233.chapter5.view.Score;

public class GameLoop implements Runnable{
    private Platform platform ;
    private Snake snake ;
    private Food food ;
    private float interval = 1000.0f/10;
    private boolean running ;

    public GameLoop(Platform platform , Snake snake , Food food){
        this.snake = snake ;
        this.platform = platform ;
        this.food = food ;
        this.running = true ;
    }

    private void update(){
        KeyCode cur_key = platform.getKey();
        Direction cur_direction = snake.getCurrentDirection();

        if(cur_key==KeyCode.UP && cur_direction != Direction.DOWN){
            snake.setCurrentDirection(Direction.UP);
        }

        else if(cur_key == KeyCode.DOWN && cur_direction != Direction.UP){
            snake.setCurrentDirection(Direction.DOWN);
        }

        else if(cur_key == KeyCode.LEFT && cur_direction != Direction.RIGHT){
            snake.setCurrentDirection(Direction.LEFT);
        }

        else if(cur_key == KeyCode.RIGHT && cur_direction != Direction.LEFT){
            snake.setCurrentDirection(Direction.RIGHT);
        }
        snake.update();
    }
    private void checkCollision() {
        if(snake.isCollidingWith(food)){
            for(int i = 0 ; i < food.getScore() ; i++){
                snake.grow();
            }
            snake.setSnakeScore(snake.getSnakeScore() + food.getScore());
            food.respawn();
        }
        if(snake.isDead()){
            running = false ;

            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Game over");
                    alert.setHeaderText(null);
                    alert.setContentText("You're dead. your score is " + snake.getSnakeScore());
                    alert.showAndWait();
                    System.exit(0);

                }
            });
        }
    }

    private void redraw() {
        platform.render(snake,food);
    }

    @Override
    public void run(){
        while (running){
            update();
            checkCollision();
            updateScore();
            redraw();
            try{
                Thread.sleep((long)interval);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

    private void updateScore(){
        javafx.application.Platform.runLater( () -> {
            Score score = platform.getScore();
            score.setPoint(this.snake.getSnakeScore());
        });
    }

    public Platform getPlatform() {
        return platform;
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public float getInterval() {
        return interval;
    }

    public boolean isRunning() {
        return running;
    }
}
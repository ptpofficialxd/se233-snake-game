package se233.chapter5.model;

import se233.chapter5.view.Platform;
import javafx.geometry.Point2D;
import java.util.Random;

public class Food {
    private Point2D position ;
    private Random rn ;
    private int score = 1 ;
    public boolean isSpecialFood = false ;

    public Food(Point2D position){
        this.position = position ;
        this.rn = new Random();
    }
    public Food(){
        this.rn = new Random();
        respawn();
    }

    public void respawn() {
        Point2D prev_position = this.position;

        do{
            if(rn.nextInt(6) == 1){
                this.isSpecialFood = true ;
                this.score = 5 ;
            }else{
                this.score  = 1 ;
                this.isSpecialFood = false ;
            }
            this.position = new Point2D(rn.nextInt(Platform.WIDTH), rn.nextInt(Platform.HEIGHT));
        }while (prev_position == this.position);
    }

    public Point2D getPosition() {
        return this.position ;
    }

    public int getScore() {
        return this.score;
    }
}
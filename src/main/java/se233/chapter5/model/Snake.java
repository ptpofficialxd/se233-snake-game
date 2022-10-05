package se233.chapter5.model;

import javafx.geometry.Point2D;
import se233.chapter5.view.Platform;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Direction direction ;
    private Point2D head ;
    private Point2D prevTail;
    private ArrayList<Point2D> body ;
    private int snakeScore = 0;
    public Snake(Point2D position){
        this.direction = Direction.DOWN ;
        this.body = new ArrayList<>() ;
        this.head = position ;
        this.body.add(this.head);
    }

    public void update() {
        this.head = head.add(direction.current);
        this.prevTail = body.remove(body.size() - 1);
        this.body.add(0,head);
    }

    public boolean isCollidingWith(Food food){
        return head.equals(food.getPosition());
    }

    public void grow(){
        body.add(prevTail);
    }

    public int getLength(){
        return body.size();
    }

    public boolean isDead(){
        boolean isOutOfBound = head.getX() < 0 || head.getY() < 0 || head.getX() > Platform.WIDTH || head.getY() > Platform.HEIGHT ;
        boolean isHitBody = body.lastIndexOf(head) > 0 ;
        return isHitBody || isOutOfBound ;
    }

    public void setCurrentDirection(Direction direction) {
        this.direction = direction;
    }
    public Direction getCurrentDirection(){
        return  this.direction ;
    }

    public Point2D getPrevTail() {
        return prevTail;
    }

    public List<Point2D> getBody() {
        return body;
    }
    public Point2D getHead() {
        return this.head ;
    }

    public int getSnakeScore() {
        return snakeScore;
    }

    public void setSnakeScore(int snakeScore) {
        this.snakeScore = snakeScore;
    }
}
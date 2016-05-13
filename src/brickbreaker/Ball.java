/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Ball {
    
    private final Circle shape;
    
    private DoubleProperty centerX = new SimpleDoubleProperty();
    private DoubleProperty centerY = new SimpleDoubleProperty();
    private double speedX = Math.sqrt(2) / 2;
    private double speedY = Math.sqrt(2) / 2;
    private boolean movingRight = true;
    private boolean movingDown = true;
    private double speed = Constants.BALL_START_SPEED;
    
    private Timeline ballAnimation;
    private final Timeline waitPeriod;
    private boolean hasUpdated;
    
    public Ball(double startX, double startY) {
        
        centerX.set(startX);
        centerY.set(startY);
        
        waitPeriod = new Timeline(
                new KeyFrame(new Duration(2000.0), i -> {
                    ballAnimation = new Timeline(
                            new KeyFrame(new Duration(20.0), t -> {
                                centerX.set(centerX.get() + speedX * speed * (movingRight ? 1 : -1));
                                centerY.set(centerY.get() + speedY * speed * (movingDown ? 1 : -1));
                                hasUpdated = true;
                            })
                    );
                    ballAnimation.setCycleCount(Timeline.INDEFINITE);
                    ballAnimation.playFromStart();
                }));
        waitPeriod.playFromStart();
        
        shape = new Circle(centerX.get(), centerY.get(), 5, Constants.BALL_COLOR);
        
        shape.centerXProperty().bind(centerX);
        shape.centerYProperty().bind(centerY);
    }
    
    public void collideWithBrick(Brick brick) {
        double x1 = brick.getCenterX();
        double x2 = centerX.get();
        double y1 = brick.getCenterY();
        double y2 = centerY.get();
        //System.out.println(x1 + ", " + x2 + ", " + y1 + ", " + y2);
        double ballAngle = Math.toDegrees(Math.atan(Math.abs((y2 - y1) / (x2 - x1))));
        if (ballAngle > Constants.BRICK_DIAGONAL_ANGLE) {
            changeYDirection();
        } else {
            changeXDirection();
        }
        //System.out.println(ballAngle);
        /*
        if ((ballAngle > Constants.BRICK_DIAGONAL_ANGLE && ballAngle < 180 - Constants.BRICK_DIAGONAL_ANGLE) || 
                (ballAngle > -180 + Constants.BRICK_DIAGONAL_ANGLE && ballAngle < -1 * Constants.BRICK_DIAGONAL_ANGLE)) {
            changeYDirection();
            //System.out.println("change Y");
        } else {
            changeXDirection();
            //System.out.println("change X");
        }
        //System.out.println(Constants.BRICK_DIAGONAL_ANGLE);
        //System.out.println("pausing");
        */
    }
    public void collideWithPaddle(Paddle paddle) {
        double x1 = paddle.getCenterX();
        double x2 = centerX.get();
        double y1 = paddle.getCenterY();
        double y2 = centerY.get();
        //System.out.println(x1 + ", " + x2 + ", " + y1 + ", " + y2);
        //System.out.println(Math.atan((y2 - y1) / (x2 - x1)));
        //System.out.println(Math.toDegrees(Math.atan((y1 - y2) / (x2 - x1))));
        //double ballAngle = ((Math.toDegrees(Math.atan(Math.abs((y2 - y1) / (x2 - x1)))) - Constants.PADDLE_DIAGONAL_ANGLE) / (180 - 2 * Constants.PADDLE_DIAGONAL_ANGLE)) * (180 - 2 * Constants.BALL_MIN_BOUNCE_ANGLE);
        //double ballAngle = (x2 - (x1 - 0.5 * Constants.PADDLE_WIDTH)) / 100 * (180 - 2 * Constants.BALL_MIN_BOUNCE_ANGLE);
        double ballAngle = (Math.toDegrees(Math.atan(Math.abs((y1 - y2) / (x2 - x1)))));
        //double ballDifference = 2 * (x2 - x1) / Constants.PADDLE_WIDTH;
        //System.out.println(ballAngle);
        ballAngle /= 90;
        ballAngle *= 90 - Constants.BALL_MIN_BOUNCE_ANGLE;
        ballAngle += Constants.BALL_MIN_BOUNCE_ANGLE;
        movingRight = x2 >= x1;
        //System.out.println(ballAngle);
        speedX = Math.cos(Math.toRadians(ballAngle));
        speedY = Math.sin(Math.toRadians(ballAngle));
        //System.out.println(speedX  + ", " + speedY);
    }
    
    public boolean hasUpdated() {
        return hasUpdated;
    }
    public void changeUpdate() {
        hasUpdated = !hasUpdated;
    }
    public void increaseSpeed() {
        speed += Constants.BALL_INCREASE_SPEED_INCREMENT;
    }
    public void changeXDirection() {
        movingRight = !movingRight;
    }
    public void changeYDirection() {
        movingDown = !movingDown;
    }
    public boolean isMovingRight() {
        return movingRight;
    }
    public boolean isMovingDown() {
        return movingDown;
    }
    public Circle getShape() {
        return shape;
    }
}

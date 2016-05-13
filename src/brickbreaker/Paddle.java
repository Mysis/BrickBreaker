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
import javafx.scene.input.KeyCode;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Paddle {
    
    Rectangle shape;
    Timeline movement;
    
    DoubleProperty xTranslate = new SimpleDoubleProperty();
    
    private boolean left = false;
    private boolean right = false;
    
    public Paddle() {
        xTranslate.set(0);
        shape = new Rectangle(Constants.PADDLE_STARTX, Constants.PADDLE_STARTY, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT);
        shape.setFill(Constants.PADDLE_COLOR);
        
        movement = new Timeline(
                new KeyFrame(new Duration(20.0), t -> {
                    int displacement = 0;
                    displacement += left ? -Constants.PADDLE_SPEED : 0;
                    displacement += right ? Constants.PADDLE_SPEED : 0;
                    xTranslate.set(xTranslate.get() + displacement);
                    if (xTranslate.get() > (Constants.SCREEN_WIDTH - Constants.BOUNDARY_WIDTH - Constants.PADDLE_WIDTH) / 2) {
                        xTranslate.set((Constants.SCREEN_WIDTH - Constants.BOUNDARY_WIDTH - Constants.PADDLE_WIDTH) / 2);
                    } else if (xTranslate.get() < (Constants.SCREEN_WIDTH - Constants.BOUNDARY_WIDTH - Constants.PADDLE_WIDTH) / -2) {
                        xTranslate.set((Constants.SCREEN_WIDTH - Constants.BOUNDARY_WIDTH - Constants.PADDLE_WIDTH) / -2);
                    }
                }));
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.playFromStart();
        
        shape.setFocusTraversable(true);
        shape.setOnKeyPressed(key -> {
            left = key.getCode() == KeyCode.LEFT;
            right = key.getCode() == KeyCode.RIGHT;
        });
        shape.setOnKeyReleased(key -> {
            if (key.getCode() == KeyCode.LEFT) {
                left = false;
            } else if (key.getCode() == KeyCode.RIGHT) {
                right = false;
            }
        });
        
        shape.translateXProperty().bind(xTranslate);
    }
    
    public boolean checkCollision(Node objToCheck) {
        return shape.intersects(objToCheck.getBoundsInLocal());
    }
    public Rectangle getShape() {
        return shape;
    }
    public double getCenterX() {
        return shape.getX() + xTranslate.get() + Constants.PADDLE_WIDTH / 2;
    }
    public double getCenterY() {
        return shape.getY() + Constants.PADDLE_HEIGHT / 2;
    }
}

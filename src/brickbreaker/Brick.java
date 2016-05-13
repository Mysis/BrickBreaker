/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import javafx.scene.shape.Rectangle;

public class Brick {
    
    private final Rectangle shape;
    
    private final double centerX;
    private final double centerY;
    
    public Brick(double x, double y) {
        centerX = x + Constants.BRICKS_WIDTH / 2;
        centerY = y + Constants.BRICKS_HEIGHT / 2;
        shape = new Rectangle(x, y, Constants.BRICKS_WIDTH, Constants.BRICKS_HEIGHT);
        shape.setFill(Constants.BRICKS_COLOR);
    }
    
    public Rectangle getShape() {
        return shape;
    }
    public double getCenterX() {
        return centerX;
    }
    public double getCenterY() {
        return centerY;
    }
}

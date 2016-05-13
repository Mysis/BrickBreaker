/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Boundary {
    
    private final Rectangle leftWall;
    private final Rectangle topWall;
    private final Rectangle rightWall;
    private final Rectangle lowerBoundary;
    
    public Boundary(Color fill) {
        leftWall = new Rectangle(0, 0, Constants.BOUNDARY_WIDTH, Constants.SCREEN_WIDTH);
        topWall = new Rectangle(0, 0, Constants.SCREEN_HEIGHT, Constants.BOUNDARY_WIDTH);
        rightWall = new Rectangle(Constants.SCREEN_WIDTH - Constants.BOUNDARY_WIDTH, 0, Constants.BOUNDARY_WIDTH, Constants.SCREEN_HEIGHT);
        lowerBoundary = new Rectangle(0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH, 30);
        
        leftWall.setFill(fill);
        topWall.setFill(fill);
        rightWall.setFill(fill);
    }
    
    public List<Rectangle> getShapeList() {
        ArrayList<Rectangle> returnList = new ArrayList();
        returnList.add(leftWall);
        returnList.add(topWall);
        returnList.add(rightWall);
        returnList.add(lowerBoundary);
        return returnList;
    }
    public Rectangle getLeftRectangle() {
        return leftWall;
    }
    public Rectangle getTopRectangle() {
        return topWall;
    }
    public Rectangle getRightRectangle() {
        return rightWall;
    }
    public Rectangle getLowerBoundary() {
        return lowerBoundary;
    }
}

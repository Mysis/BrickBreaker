/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import javafx.scene.paint.Color;

public final class Constants {
    
    public static final double SCREEN_WIDTH = 500;
    public static final double SCREEN_HEIGHT = 500;
    public static final Color SCREEN_COLOR = Color.BLACK;
    
    public static final double BOUNDARY_WIDTH = 10;
    public static final Color BOUNDARY_COLOR = Color.BROWN;
    
    public static final double BRICKS_WIDTH = 50;
    public static final double BRICKS_HEIGHT = 20;
    public static final Color BRICKS_COLOR = Color.WHITE;
    public static final int BRICKS_PER_ROW = (int)((SCREEN_WIDTH - (2 * BOUNDARY_WIDTH)) / BRICKS_WIDTH);
    public static final int BRICKS_PER_COLUMN = 5;
    public static final double BRICKS_VERTICAL_SPACING = 3;
    public static final double BRICKS_HORIZONTAL_SPACING = 3;
    public static final double BRICK_DIAGONAL_ANGLE = Math.toDegrees(Math.atan(BRICKS_HEIGHT / BRICKS_WIDTH));
    
    public static final double PADDLE_WIDTH = 75;
    public static final double PADDLE_HEIGHT = 20;
    public static final double PADDLE_SPEED = 8;
    public static final Color PADDLE_COLOR = Color.GREEN;
    public static final double PADDLE_STARTX = (SCREEN_WIDTH - PADDLE_WIDTH) / 2;
    public static final double PADDLE_STARTY = SCREEN_HEIGHT - PADDLE_HEIGHT;
    public static final double PADDLE_DIAGONAL_ANGLE = Math.toDegrees(Math.atan(PADDLE_HEIGHT / PADDLE_WIDTH));
    
    public static final int BALL_SIZE = 5;
    public static final Color BALL_COLOR = Color.RED;
    public static final double BALL_STARTX = (SCREEN_WIDTH - BALL_SIZE) / 2;
    public static final double BALL_STARTY = (PADDLE_STARTY + BOUNDARY_WIDTH + BRICKS_VERTICAL_SPACING + (BRICKS_HEIGHT + BRICKS_VERTICAL_SPACING) * BRICKS_PER_COLUMN) / 2;
    public static final double BALL_START_SPEED = 5;
    public static final double BALL_INCREASE_SPEED_INCREMENT = 0.2;
    public static final double BALL_MIN_BOUNCE_ANGLE = 30;
}

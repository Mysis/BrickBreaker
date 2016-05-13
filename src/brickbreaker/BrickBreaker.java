/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class BrickBreaker extends Application {

    Scene scene;

    private Ball mainBall;
    private Boundary mainBoundary;
    private List<List<Brick>> brickList = new ArrayList();
    private Paddle mainPaddle;
    private Group mainGroup;

    Timeline mainTimeline;

    private IntegerProperty score = new SimpleIntegerProperty(0);
    Text txtScore;
    int lives = Constants.STARTING_LIVES;
    List<Circle> livesIconList = new ArrayList();

    @Override
    public void start(Stage primaryStage) {

        mainGroup = new Group();
        mainBoundary = new Boundary(Color.BROWN);
        for (Rectangle boundary : mainBoundary.getShapeList()) {
            mainGroup.getChildren().add(boundary);
        }

        for (int i = 0; i < Constants.BRICKS_PER_ROW; i++) {
            brickList.add(new ArrayList());
            for (int j = 0; j < Constants.BRICKS_PER_COLUMN; j++) {
                double xOffset = Constants.BOUNDARY_WIDTH + Constants.BRICKS_HORIZONTAL_SPACING + i * (Constants.BRICKS_WIDTH + Constants.BRICKS_HORIZONTAL_SPACING);
                double yOffset = Constants.BOUNDARY_WIDTH + Constants.BRICKS_VERTICAL_SPACING + j * (Constants.BRICKS_HEIGHT + Constants.BRICKS_VERTICAL_SPACING);
                Brick brickToAdd = new Brick(xOffset, yOffset);
                brickList.get(i).add(brickToAdd);
                mainGroup.getChildren().add(brickToAdd.getShape());
            }
        }

        mainPaddle = new Paddle();
        mainGroup.getChildren().add(mainPaddle.getShape());

        mainBall = new Ball(Constants.BALL_STARTX, Constants.BALL_STARTY);
        mainGroup.getChildren().add(mainBall.getShape());

        mainGroup.setFocusTraversable(true);
        mainGroup.requestFocus();

        mainTimeline = new Timeline(new KeyFrame(new Duration(10.0), t -> {
            if (mainBall.getShape().intersects(mainPaddle.getShape().getBoundsInParent()) && mainBall.isMovingDown()) {
                mainBall.collideWithPaddle(mainPaddle);
                mainBall.changeYDirection();
                mainBall.increaseSpeed();
            }
            if (mainBall.getShape().intersects(mainBoundary.getLeftRectangle().getBoundsInLocal()) && !mainBall.isMovingRight()) {
                mainBall.changeXDirection();
            }
            if (mainBall.getShape().intersects(mainBoundary.getRightRectangle().getBoundsInLocal()) && mainBall.isMovingRight()) {
                mainBall.changeXDirection();
            }
            if (mainBall.getShape().intersects(mainBoundary.getTopRectangle().getBoundsInLocal()) && !mainBall.isMovingDown()) {
                mainBall.changeYDirection();
            }
            if (mainBall.getShape().intersects(mainBoundary.getLowerBoundary().getBoundsInLocal())) {
                decreaseLife();
            }
            
            if (mainBall.hasUpdated()) {
                iterateThroughBrickCollisions();
                mainBall.changeUpdate();
            }
        }));
        mainTimeline.setCycleCount(Timeline.INDEFINITE);
        mainTimeline.playFromStart();
        
        txtScore = new Text(10, -20, "Score");
        txtScore.textProperty().bind(Bindings.format("Score: %1$d", score));
        txtScore.setFill(Color.ORANGE);
        txtScore.setStyle("-fx-font: 24 arial;");
        mainGroup.getChildren().add(txtScore);
        mainGroup.setLayoutY(Constants.SCORE_HEIGHT);
        
        for (int i = 0; i < lives; i++) {
            Circle newLife = new Circle(Constants.SCREEN_WIDTH - 30 * i - 30, -25, Constants.BALL_SIZE, Constants.BALL_COLOR);
            mainGroup.getChildren().add(newLife);
            livesIconList.add(newLife);
        }

        scene = new Scene(mainGroup, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT + Constants.SCORE_HEIGHT);
        scene.setFill(Constants.SCREEN_COLOR);

        primaryStage.setScene(scene);
        primaryStage.setTitle("BrickBreaker");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    
    public void iterateThroughBrickCollisions() { 
        for (int i = 0; i < brickList.size(); i++) {
            for (int j = 0; j < brickList.get(i).size(); j++) {
                Brick brick = brickList.get(i).get(j);
                if (mainBall.getShape().intersects(brick.getShape().getBoundsInLocal())) {
                    mainBall.collideWithBrick(brick);
                    brickList.get(i).remove(brick);
                    mainGroup.getChildren().remove(brick.getShape());
                    score.set(score.get() + 1);
                    return;
                }
            }
        }
    }
    
    public void decreaseLife() {
        lives -= 1;
        if (lives <= -1) {
            System.out.println("Game Over");
            System.exit(0);
        }
        mainGroup.getChildren().remove(livesIconList.get(lives));
        livesIconList.remove(lives);
        mainBall = new Ball(Constants.BALL_STARTX, Constants.BALL_STARTY);
        mainGroup.getChildren().add(mainBall.getShape());
    }

    public static void main(String[] args) {
        launch(args);
    }

}

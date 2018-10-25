package ViewController;

import Model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;
/**
 * @author Paweł Młyniec
 * @version 1.0
 * creates playing area, calculates incoming data from controller and draws objects
 */
public class Game {
    Stage stage;
    Canvas canvas;
    GraphicsContext graphicsContext;
    Timeline timeline;
    Scene gameScene;
    public Ball ball;
    public Settings settings;
    public Paddle playerOnePaddle;
    public Paddle playerTwoPaddle;
   private  Window window;
  public  long gamingTime;
   public long startTime;
    public  boolean isPaused;


   public Game(){
       ball = new Ball();
       settings = new Settings();
   }

    public void init(Window oldWindow){
        window = oldWindow;
        playerOnePaddle = new Paddle(100,20,0,300);//dadać window
        playerTwoPaddle = new Paddle(100,20,980,300);//dadać widok

        canvas = new Canvas(window.getWindoWidth(),window.getWindowHight());
        graphicsContext =  canvas.getGraphicsContext2D();
        timeline = new Timeline(new KeyFrame(Duration.millis(5), e->animation(graphicsContext)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        gameScene = new Scene(new StackPane(canvas));
        gamingTime = 0;

    }




    public  void animation(GraphicsContext graphicsContext) {


        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, Window.getWindoWidth(), Window.getWindowHight());
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(Font.font(30));

        if (playerOnePaddle.goUp)
            playerOnePaddle.setyPosition(playerOnePaddle.getyPosition()-2);
        if (playerOnePaddle.goDown)
            playerOnePaddle.setyPosition(playerOnePaddle.getyPosition()+2);
        if (playerTwoPaddle.goUp && settings.isTwoPlayers())
            playerTwoPaddle.setyPosition(playerTwoPaddle.getyPosition()-2);
        if (playerTwoPaddle.goDown && settings.isTwoPlayers())
            playerTwoPaddle.setyPosition(playerTwoPaddle.getyPosition()+2);


        graphicsContext.fillText(String.valueOf(settings.getScore1()) , window.getWindoWidth() / 5, window.getWindowHight()/10);
        graphicsContext.fillText(String.valueOf(settings.getScore2()) , (window.getWindoWidth() / 2) + (window.getWindoWidth() / 5), window.getWindowHight()/10);

        graphicsContext.fillRect(playerOnePaddle.getxPosition(),playerOnePaddle.getyPosition(),playerOnePaddle.getWidth(),playerOnePaddle.getPaddleHight());
        graphicsContext.fillRect(playerTwoPaddle.getxPosition(),playerTwoPaddle.getyPosition(),playerTwoPaddle.getWidth(),playerTwoPaddle.getPaddleHight());

        if(!settings.isStart()){
            graphicsContext.setTextAlign(TextAlignment.CENTER);
            if(settings.getScore1()>=settings.getEndingScore())
                graphicsContext.fillText("Player One Won!!!", window.getWindoWidth() / 2, window.getWindowHight()/3);
            else if(settings.getScore2()>=settings.getEndingScore())
                graphicsContext.fillText("Player Two Won!!!", window.getWindoWidth() / 2, window.getWindowHight()/3);


            graphicsContext.fillText("Press space to start or esc to return", window.getWindoWidth() / 2, window.getWindowHight()/2);
            ball.setBallXPos(window.getWindoWidth()/ 2);
            ball.setBallYPos(window.getWindowHight()/2);
            ball.setBallXSpeed( new Random().nextBoolean() ? 1:-1);
            ball.setBallYSpeed( new Random().nextBoolean() ? 1:-1);

        } else {
            ball.setBallXPos(ball.getXPos()+ball.getXSpeed());
            ball.setBallYPos(ball.getYPos()+ball.getYSpeed());

            if(!settings.isTwoPlayers())
                if(ball.getXPos() < window.getWindoWidth() - window.getWindoWidth()/8)  {
                playerTwoPaddle.setyPosition(ball.getYPos()-playerTwoPaddle.getPaddleHight()/2);
             }  else {
             playerTwoPaddle.setyPosition(ball.getYPos()>playerTwoPaddle.getyPosition()+playerTwoPaddle.getPaddleHight()/2 ? playerTwoPaddle.getyPosition()+5:playerTwoPaddle.getyPosition()-5 );
             }


            graphicsContext.fillOval(ball.getXPos(), ball.getYPos(), ball.getRatio(),ball.getRatio());

        }
        if(ball.getYPos()+ball.getRatio() > window.getWindowHight()|| ball.getYPos()< 0)
            ball.setBallYSpeed(ball.getYSpeed()*-1);
        if(ball.getXPos()<= playerOnePaddle.getxPosition()-playerOnePaddle.getWidth()) {
            settings.setScore2(settings.getScore2()+1);
            gamingTime+= (System.nanoTime()-startTime)/100000000;
            isPaused = true;
            settings.setStart(false);
        }
        if(ball.getXPos()>= playerTwoPaddle.getxPosition()+playerTwoPaddle.getWidth()) {
            settings.setScore1(settings.getScore1()+1);
            gamingTime+= (System.nanoTime()-startTime)/100000000;
            isPaused = true;
            settings.setStart(false);
        }
        if( ((ball.getXPos() + ball.getRatio()) > playerTwoPaddle.getxPosition()) && ball.getYPos() >= playerTwoPaddle.getyPosition() && ball.getYPos() <= playerTwoPaddle.getyPosition() + playerTwoPaddle.getPaddleHight()  ||
                ((ball.getXPos() < playerOnePaddle.getxPosition() + playerOnePaddle.getWidth()) && ball.getYPos() >= playerOnePaddle.getyPosition() && ball.getYPos() <= playerOnePaddle.getyPosition() + playerOnePaddle.getPaddleHight())) {
            ball.setBallYSpeed((ball.getYSpeed()+1*Math.signum(ball.getYSpeed())));
            ball.setBallXSpeed(-1*(ball.getXSpeed()+1*Math.signum(ball.getXSpeed())));

        }
    }
}

package Model;
/**
 * @author Paweł Młyniec
 * @version 1.0
 * Contains data about ball
 */
public class Ball {
    private double ballXPos ;
    private double ballYPos ;
    private float ballYSpeed = 1;
    private float ballXSpeed = 1;
    private static final int ballRatio = 20;

    public Ball(){}

    public Ball(double BallXpos, double BallYPos){
        ballXPos = BallXpos;
        ballYPos = BallYPos;
    }



    public double getXPos() {
        return ballXPos;
    }

    public double getYPos() {
        return ballYPos;
    }

    public  int getRatio() {
        return ballRatio;
    }

    public float getXSpeed() {
        return ballXSpeed;
    }

    public float getYSpeed() {
        return ballYSpeed;
    }

    public void setBallXPos(double ballXPos) {
        this.ballXPos = ballXPos;
    }

    public void setBallXSpeed(float ballXSpeed) {
        this.ballXSpeed = ballXSpeed;
    }

    public void setBallYPos(double ballYPos) {
        this.ballYPos = ballYPos;
    }

    public void setBallYSpeed(float ballYSpeed) {
        this.ballYSpeed = ballYSpeed;
    }
}


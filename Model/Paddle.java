package Model;
/**
 * @author Paweł Młyniec
 * @version 1.0
 * Contains data to create paddle
 */
public class Paddle {
    private double paddleHight ;
    private double paddleWidth ;
    private double yPosition;
    private double xPosition;
    public boolean goUp = false;
    public boolean goDown = false;

    public Paddle(double PaddleHight, double PaddleWidth, double XPosition, double Yposition){
        paddleHight = PaddleHight;
        paddleWidth = PaddleWidth;
        yPosition = Yposition;
        xPosition = XPosition;
    }

    public double getPaddleHight() {
        return paddleHight;
    }

    public double getWidth() {
        return paddleWidth;
    }

    public double getxPosition() {
        return xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public void setPaddleHight(double paddleHight) {
        this.paddleHight = paddleHight;
    }

    public void setPaddleWidth(double paddleWidth) {
        this.paddleWidth = paddleWidth;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }
}

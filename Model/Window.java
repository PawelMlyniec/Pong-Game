package Model;
/**
 * @author Paweł Młyniec
 * @version 1.0
 * contains data to build window
 */
public class Window {
    private static double windoWidth = 1000;
    private static double windowHight = 600;

    public static double getWindowHight() {
        return windowHight;
    }

    public static double getWindoWidth() {
        return windoWidth;
    }

    public void setWindowHight(double windowHight) {
        this.windowHight = windowHight;
    }

    public void setWindoWidth(double windoWidth) {
        this.windoWidth = windoWidth;
    }
}

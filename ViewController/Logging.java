package ViewController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
/**
 * @author Paweł Młyniec
 * @version 1.0
 * Simple view
 */
public class Logging {
    Scene scene;

    public Logging ()throws Exception{

            Parent root = FXMLLoader.load(getClass().getResource("LoggingView.fxml"));
            scene = new Scene(root, 700, 400);

    }



    public Scene getScene() {
        return scene;
    }
}

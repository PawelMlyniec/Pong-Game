package Main;

import ViewController.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Paweł Młyniec
 * @version 1.0
 * Main class creates controller,
 */
public class Main extends Application {
    Controller controller;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) throws Exception {
        controller = new Controller();
        controller.logging(stage);
    }
}





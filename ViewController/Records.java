package ViewController;
import Database.ReturnedRecord;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.scene.layout.VBox;

import java.util.List;

/**
 * @author Paweł Młyniec
 * @version 1.0
 * View to display records from database
 */
public class Records {
    List<ReturnedRecord> list;
    double width;
    double hight;
    static Scene scene;
    public Button button;
    ListView listView = new ListView();

    public Records(double width, double hight, List list) {
        this.list = list;
        this.hight = hight;
        this.width = width;

    }

    public void init() {
        int i = 0;

        while (i < 15) {
            listView.getItems().add(list.get(i).toString());
            i++;
        }
        button = new Button("Return");
        VBox vBox = new VBox(listView, button);
        scene = new Scene(vBox, width, hight);

    }

    public static Scene getScene() {
        return scene;
    }
}



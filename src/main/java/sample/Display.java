package sample;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Display {
    int stations;
    int distance = 60;
    Group root;
    Stage window;
    Scene scene;
    List<Station> stationList = new ArrayList<Station>();


    public Display(Group window){
        this.stations = stations;
//        this.window = window;
        root = new Group();
        window.getChildren().add(root);
//        scene = new Scene(root, 1000, 600);
//        window.setScene(scene);
//        window.setTitle("Simulation");
//        window.show();
    }

    public void addStations(String id){
        stationList.add(new Station(id));
    }

    public void displayRectangles(){
        int width = 160;
        int y = 60;
        int j = 0;
        for(int i = 1; i <= stationList.size(); i++){
            if(i % 3 == 1){
                y += (60 + 100);
                j = 0;
            }
            stationList.get(i-1).getRectangle().relocate(200 + (distance + width) * j, y);
            stationList.get(i-1).display();
            root.getChildren().add(stationList.get(i-1).display());
            j++;
        }

//            Rectangle rect = new Rectangle(200 + (distance + width) * j , y, width, 100);
//            rect.setStroke(color);
//            rect.setFill(Color.WHITE);
    }

    public void changeColor(int i, boolean running){
        if(running){
            stationList.get(i).getRectangle().setStroke(Color.GREEN);
        }
        else {
            stationList.get(i).getRectangle().setStroke(Color.RED);
        }
    }

}

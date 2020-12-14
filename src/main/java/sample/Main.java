package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;


public class Main extends Application {
    private File productFile;   //a termék json fájlja
    private File lineFile;  //a gyártósor json fájlja
//    private FileChooser fileChooser=new FileChooser();
    private int neededProducts=0; //hány darab terméket akarunk gyártani
    private int simulationSpeed=1; //milyen gyors legyen a szimuláció

    Rectangle rectangle;
    Button start;
    ImageView imv;
    ComboBox comboBox;
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        File_Chooser file_chooser = new File_Chooser();
        File_Chooser file_chooser2 = new File_Chooser();

        window = primaryStage;
        Group root = new Group();
//        Pane root = new Pane();
        Scene scene = new Scene(root, 200, 200);

        productFile = new File("p1.json");
        lineFile = new File("test_OK.json");

        Button start = new Button("Start");
        start.setLayoutX(50);
        start.setLayoutY(50);
        start.minWidth(80);

        Button gyartosorConf = new Button("Gyartosor konfiguracio");
        gyartosorConf.setLayoutX(50);
        gyartosorConf.setLayoutY(80);
        gyartosorConf.minWidth(130);

        Button termekConf = new Button("Termek konfiguracio");
        termekConf.setLayoutX(50);
        termekConf.setLayoutY(110);
        termekConf.minWidth(130);

        start.setOnAction(e -> {
            Group root2 = new Group();
            Scene scene_simulation = new Scene(root2, 1000, 600);
            window.setScene(scene_simulation);
            window.setTitle("Simulation");
            window.show();


            Display display = new Display(root2);
            Simulation simulation = new Simulation();
            try {
//                simulation.simulate(productFile, lineFile, 2, 1, primaryStage);
                simulation.simulate(file_chooser.getFile(), file_chooser2.getFile(), 2, 1, display);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
//            Display display = new Display(primaryStage);
//            for (int i = 0; i < 4; i++){
//                display.addStations("i");
//            }
//            display.displayRectangles();
        });

        gyartosorConf.setOnAction(e -> {
            file_chooser.chooser();
        });

        termekConf.setOnAction(e -> {
            file_chooser2.chooser();
        });

        root.getChildren().addAll(start, gyartosorConf, termekConf);
        window.setTitle("Production Simulation");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch( args);
    }

}

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    private File productFile;   //a termék json fájlja
    private File lineFile;  //a gyártósor json fájlja
    private FileChooser fileChooser=new FileChooser();
    private int neededProducts=0; //hány darab terméket akarunk gyártani
    private int simulationSpeed=1; //milyen gyors legyen a szimuláció


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

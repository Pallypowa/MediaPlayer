package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Station {

    String id;
    Color color = Color.RED;
    Rectangle rect;

    public Station(String id){
        this.id = id;
        rect = new Rectangle(0, 0, 160, 100);
    }

    public Rectangle display(){
        rect.setStroke(color);
        rect.setFill(Color.WHITE);
        return rect;
    }

    public Rectangle getRectangle(){
        return rect;
    }
}

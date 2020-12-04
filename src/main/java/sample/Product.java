package sample;

import java.util.ArrayList;

public class Product {
    String name;
    String id;
    ArrayList<String> neededRobots; //tárolja a szükséges gépek ID-ját

    public Product(String name,String id, ArrayList<String> neededRobots) {
        this.name=name;
        this.id = id;
        this.neededRobots = neededRobots;
    }
}

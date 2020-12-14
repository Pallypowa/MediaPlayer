package sample;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulation {

    //json parser a termékekhez
    public static Product parseProduct(File fn) throws Exception{
        FileReader file=new FileReader(fn);
        JSONParser parser=new JSONParser();
        JSONObject obj= (JSONObject) parser.parse(file);
        String name=(String) obj.get("name");
        String id=(String)obj.get("id");
        ArrayList<String> needs=(ArrayList<String>) obj.get("needs");
        return new Product(name,id, needs);
    }

    //json parser a gyártósorhoz
    public static ProductionLine parseProdLine(File fn,int speed) throws Exception{
        FileReader file=new FileReader(fn);
        JSONParser parser=new JSONParser();
        JSONArray arr= (JSONArray) parser.parse(file);
        System.out.println(arr);
        ArrayList<Robot> robots=new ArrayList<>();
        for (Object o : arr) {
            JSONObject obj = (JSONObject) o;
            Robot r = new Robot((String) obj.get("id"), Long.parseLong((String) obj.get("time")) / speed);
            robots.add(r);
        }
        return new ProductionLine(robots);
    }

    //Ezt hívd meg, ha el akarod indítani a szimulációt
    public static void simulate(File productFile, File lineFile,int NUM, int speed, Display display)throws Exception{
        //NUM- hány darab terméket készítsünk
        Product product=parseProduct(productFile);
        ProductionLine line=parseProdLine(lineFile,speed);
        if(line.verifyLine(product)){
            System.out.println("OK");
            line.setUsed(product);
            ExecutorService pool= Executors.newFixedThreadPool(line.usedRobots.size());
            int done=0;

            //a gépeken fordított sorrendben haladunk végig, így biztos nem lesz false negatív indításunk
            int limit=line.usedRobots.size()-1;

            //megjelenítés
//            Display display = new Display(stage);
//            display = new Display(stage);
            for (Robot r : line.usedRobots){
                display.addStations(r.getId());
            }
            display.displayRectangles();

            while(done<NUM){
                for (int i =limit ; i >-1 ; --i) {
                    Robot r=line.usedRobots.get(i);
                    //ha az előző gép végzett, akkor kap anyagot, amivel dolgozhat
                    boolean hasMaterial=i==0||line.usedRobots.get(i-1).hasOutput();
                    //ha van anyag, nem fut és nem végzett a kellő mennyiségű termékkel
                    if(hasMaterial&&!r.isRunning()&&r.getCounter()<=NUM){
                        pool.execute(r);
                        System.out.println(r.getId()+" started execution");
                    }
                    display.changeColor(i, r.isRunning());

                }
                done=line.usedRobots.get(limit).getCounter();
                System.out.println(done);
                TimeUnit.MILLISECONDS.sleep(500);
            }
            pool.shutdown();

        }else{
            System.out.println("The production line does not contain all machines to produce "+ product.name);
        }
        line.finished();
        System.out.println("Done");
    }
}

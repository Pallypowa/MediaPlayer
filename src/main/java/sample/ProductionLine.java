package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductionLine {
    private ArrayList<Robot> allRobots;
    public List<Robot> usedRobots=new ArrayList<>();

    public ProductionLine(ArrayList<Robot> robots) {
        this.allRobots = robots;
    }

    //ellenőrzi, hogy minden szükséges gép rendelkezésünkre áll a termék elkészítéséhez
    public boolean verifyLine(Product p){
        for (String r:p.neededRobots) {
            if(!indexes().contains(r)){
                return false;
            }
        }
        return true;
    }

    //a használandó robotokat egy új listába rendezi
    public void setUsed(Product p){
        for (Robot r: allRobots) {
            if(p.neededRobots.contains(r.getId())){
                r.setNeeded(true);
                usedRobots.add(r);
            }
        }
    }

    public List<String> indexes(){
        List<String> idx=new ArrayList<>();
        for (Robot r: allRobots) {
            idx.add(r.getId());
        }
        return idx;
    }

    public void finished() throws IOException {
        for (Robot r:usedRobots) r.finished();
        usedRobots.clear();
    }
}

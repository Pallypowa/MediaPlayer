package sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Robot implements Runnable {
    private String id;
    private long runtime;
    private int counter = 0;
    private boolean needed=false;
    private boolean running=false;
    private boolean output=false;
    private BufferedWriter fw=new BufferedWriter(new FileWriter(id+"_log.txt"));


    public Robot(String id, long runtime) throws IOException {
        this.id = id;
        this.runtime=runtime;
    }
    @Override
    public void run() {

        try {
            fw.write("Starting");
            fw.newLine();
            this.running=true;
            TimeUnit.SECONDS.sleep(runtime);
            counter++;
            fw.write("Finished, Number of finished products: "+counter);
            this.running=false;
            this.output=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public int getCounter() {
        return counter;
    }

    public void setNeeded(boolean needed) {
        this.needed = needed;
    }

    public boolean hasOutput() {
        boolean ret=output;
        output=false;
        return ret;
    }

    public boolean isRunning() {
        return running;
    }

    public void finished() throws IOException {
        needed=false;
        running=false;
        output=false;
        fw.close();
    }
}

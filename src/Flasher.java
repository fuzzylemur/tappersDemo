import processing.core.PApplet;
import java.util.Random;

public class Flasher {

    long time;
    LED[] myLEDs;

    Flasher(LED[] LEDlist){
        time = 0;
        myLEDs = LEDlist;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

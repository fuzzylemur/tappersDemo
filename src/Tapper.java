import processing.core.PApplet;
import java.util.Random;


class Tapper {

    int FLASH_DELAY_MAX = 300;
    int FLASH_DELAY_MIN = 50;
    int FLASH_CYCLE_MIN = 5;
    int FLASH_CYCLE_MAX = 50;
    int NUM_SOUNDS = 16;

    LedGrid myGrid;
    LED[] myLEDs;
    Sound mySound;
    Random rand = new Random();

    int delay;
    int cycle;
    int startFrame;
    boolean flash = true;

    Tapper(PApplet sketch, float x, float y, int frame, float size) {

        myGrid = new LedGrid(sketch, x, y, size);
        myLEDs = myGrid.getLEDlist();

        startFrame = frame;
        int randVal = rand.nextInt(FLASH_DELAY_MAX - FLASH_DELAY_MIN);
        delay = randVal + FLASH_DELAY_MIN;
        randVal = rand.nextInt(FLASH_CYCLE_MAX - FLASH_CYCLE_MIN);
        cycle = randVal + FLASH_CYCLE_MIN;

        mySound = new Sound(randVal % NUM_SOUNDS);
        mySound.play();

    }

    void update(int frame) {

        if (frame-startFrame == delay) {
            if (flash){
                for (LED led : myLEDs) {
                    led.setAlpha(0);
                }
                flash = false;
            }
            else {
                for (LED led : myLEDs) {
                    led.setAlpha(255);
                }
                flash = true;
                mySound.play();
            }
            startFrame = frame;
        }
        else if (flash && (frame-startFrame)%cycle==0) {
            for (LED led : myLEDs) {
                if (maybe())
                    led.setAlpha(255);
                else
                    led.setAlpha(0);
            }
        }
    }

    boolean maybe() {
        return rand.nextInt(2)==0;
    }

    void render() {
        myGrid.render();
    }
}

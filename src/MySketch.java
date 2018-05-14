import processing.core.PApplet;
import processing.core.PImage;

public class MySketch extends PApplet{

    int MAX_TAPPERS = 500;
    int WIDTH = 1200;
    int HEIGHT = 750;
    float SCALE_FACTOR = 10f;
    float DEFAULT_SIZE = 2f;
    float MIN_SIZE = 0.8f;

    Tapper[] tappers = new Tapper[MAX_TAPPERS];
    LedGrid[] gridArray = new LedGrid[100];
    int nextIndex = 0;
    int frame = 0;
    int frameDelay = 0;

    PImage BGimg;


    public void settings(){
        size(WIDTH, HEIGHT);
        BGimg = loadImage("img/img1.png");
    }

    public void draw(){

        image(BGimg, 0, 0);

        for (int j=0; j<nextIndex; j++) {
            tappers[j].update(frame);
            tappers[j].render();
        }

        frame ++;
        delay(frameDelay);
    }

    public void mousePressed(){
        tappers[nextIndex] = new Tapper(this, mouseX, mouseY, frame, DEFAULT_SIZE);
        nextIndex ++;
    }

    public void mousePressedPersp(){
        float size = MIN_SIZE + (abs((WIDTH/2)-mouseX)/((WIDTH/2)/SCALE_FACTOR));
        tappers[nextIndex] = new Tapper(this, mouseX, mouseY, frame, size);
        nextIndex ++;
    }

    public void keyPressed() {

        boolean allOn = true;
        if (key == CODED) {
            if (keyCode == RIGHT)
                allOn = true;
            if (keyCode == LEFT)
                allOn = false;

            if (keyCode == UP)
                tappers[nextIndex-1].myGrid.sizeUp();
            if (keyCode == DOWN)
                tappers[nextIndex-1].myGrid.sizeDown();

            if (keyCode == SHIFT)
                frameDelay += 10;
            if (keyCode == ALT)
                if (frameDelay != 0)
                frameDelay -= 10;

            if (keyCode == RIGHT || keyCode == LEFT) {
                for (Tapper tapper : tappers) {
                    if (tapper != null) {
                        for (LED led : tapper.myLEDs) {
                            if (led != null)
                                if (allOn)
                                    led.setAlpha(255);
                                else
                                    led.setAlpha(0);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        String[] processingArgs = {"MySketch"};
        MySketch mySketch = new MySketch();
        PApplet.runSketch(processingArgs, mySketch);
    }
}
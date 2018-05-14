import processing.core.PApplet;
import java.util.Random;

class LedGrid {

    int GRID_X = 4;
    int GRID_Y = 4;
    float SPACING = 4f;
    float DIAM = 0.8f;
    float SIZE_INC = 0.1f;
    int randFactor = 4;

    PApplet sketch;
    Random rand = new Random();
    LED[][] matrix;
    LED[] LEDlist;

    float ori_x;
    float ori_y;
    float size;
    int numLeds = 0;


    LedGrid(PApplet sketch, float ori_x, float ori_y, float size) {

        this.sketch = sketch;
        this.ori_x = ori_x;
        this.ori_y = ori_y;
        this.size = size;

        createGrid();
        createLEDlist();

    }

    void createGrid() {
        matrix = new LED[GRID_X][GRID_Y];
        for (int i=0; i<GRID_X; i++) {
            for (int j = 0; j < GRID_Y; j++) {
                int ind = rand.nextInt(randFactor);
                if (ind==0) {
                    matrix[i][j] = new LED();
                    numLeds++;
                }
            }
        }
    }

    void createLEDlist() {
        LEDlist = new LED[numLeds];
        int k=0;
        for (int i=0; i<GRID_X; i++) {
            for (int j = 0; j < GRID_Y; j++) {
                if (matrix[i][j] != null) {
                    LEDlist[k] = matrix[i][j];
                    k++;
                }
            }
        }
    }

    void renderSingleLed(int x, int y) {
        int[] RGBA = matrix[x][y].getColor();
        if (RGBA[3] > 0) {
            sketch.fill(RGBA[0], RGBA[1], RGBA[2], RGBA[3] / 8);
            sketch.ellipse(ori_x + size * (x * SPACING), ori_y + size * (y * SPACING), size * DIAM * 5, size * DIAM * 5);
            sketch.fill(RGBA[0], RGBA[1], RGBA[2], RGBA[3] / 3);
            sketch.ellipse(ori_x + size * (x * SPACING), ori_y + size * (y * SPACING), size * DIAM * 2, size * DIAM * 2);
            sketch.fill(RGBA[0], RGBA[1], RGBA[2], RGBA[3]);
            sketch.ellipse(ori_x + size * (x * SPACING), ori_y + size * (y * SPACING), size * DIAM, size * DIAM);
        }
    }

    void render(){
        sketch.noStroke();
        for (int i=0; i<GRID_X; i++) {
            for (int j=0; j<GRID_Y; j++) {
                if (matrix[i][j] != null) {
                    renderSingleLed(i, j);
                }
            }
        }
    }

    LED[] getLEDlist() {
        return LEDlist;
    }

    void sizeUp() {
        size += SIZE_INC;
    }

    void sizeDown() {
        if (size > SIZE_INC)
            size -= SIZE_INC;
    }
}
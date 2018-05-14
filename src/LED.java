import java.util.Random;

class LED {

    int[] COL_1 = {250,0,0,255};
    int[] COL_2 = {20,20,255,255};
    int[] COL_3 = {150,0,255,255};
    int[] COL_4 = {255,100,100,255};
    int[] COL_5 = {150,150,200,255};
    int[] COL_6 = {0,50,200,255};

    int[][] COLORS = {COL_1, COL_2, COL_3, COL_4, COL_5, COL_6};
    int NUM_COLORS = 6;

    int[] RGBA;

    Random rand = new Random();

    LED() {
        int index = rand.nextInt(NUM_COLORS);
        RGBA = COLORS[index];
    }

    LED(int R, int G, int B, int A) {
        RGBA[0] = R;
        RGBA[1] = G;
        RGBA[2] = B;
        RGBA[3] = A;
    }

    int[] getColor() {
        return RGBA;
    }

    void setAlpha(int value) {
        RGBA[3] = value;
    }
}

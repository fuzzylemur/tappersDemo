import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

class Sound {

    String[] FILES = {"sounds/Bottle.aiff", "sounds/Morse.aiff", "sounds/Pop.aiff", "sounds/Tink.aiff",
            "sounds/tap1.wav", "sounds/tap2.wav", "sounds/tap3.wav", "sounds/tap4.wav", "sounds/tap5.wav",
            "sounds/tap6.wav", "sounds/tap7.wav", "sounds/tap8.wav", "sounds/tap9.wav", "sounds/tap10.wav",
            "sounds/tap11.wav", "sounds/tap12.wav",
            };

    Clip clip;

    Sound(int index) {

        try {
            URL url = this.getClass().getClassLoader().getResource(FILES[index]);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    void play() {
        if (clip.isRunning())
            clip.stop();   // Stop the player if it is still running
        clip.setFramePosition(0); // rewind to the beginning
        clip.start();     // Start playing
    }
}
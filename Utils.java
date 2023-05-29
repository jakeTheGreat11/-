import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;

public class Utils {
    public static void sleep (int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void playMusic(String music){
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File(music));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            clip.start();
        } catch (Exception e) {
            System.out.println("error on sound at 'playSound");
        }
    }
    public static boolean checkCollision (Rectangle rect1, Rectangle rect2) {
        boolean collision = false;
        if (rect1.intersects(rect2)) {
            collision = true;
        }
        return collision;
    }

}

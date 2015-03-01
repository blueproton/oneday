/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oneday;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.scene.media.AudioClip;

/**
 *
 * @author tim
 */
public class SoundManager {
    
    private AudioClip sound;
    ExecutorService soundPool = Executors.newFixedThreadPool(2);
    
    public void loadSound(URL url) {
        this.sound = new AudioClip(url.toExternalForm());
    }
    
    public void playSound() {
        Runnable soundPlay = new Runnable() {

            @Override
            public void run() {
                sound.play();
            }
            
        };
        soundPool.execute(soundPlay);
    }
    
    public void shutdown() {
        soundPool.shutdown();
    }
    
}

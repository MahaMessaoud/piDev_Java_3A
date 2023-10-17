/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Ahmed Ben Abid
 */
public class soundService {

    public soundService() {
    }
    public void btn2(){
    //Sound
        String path = getClass().getResource("../Sound/btn2.mp3").getPath().replace("%20", " ");
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
//End Sound
    }
}

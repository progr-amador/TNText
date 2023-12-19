package com.progr.amador.TNText;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    float volume;

    public Sound() {

        soundURL[0] = getClass().getResource("/sounds/snd_se_menu_00000017.wav");
        soundURL[1] = getClass().getResource("/sounds/When_rushjet1_and_danooct1_get_bored_mp3cut.net2.wav");

    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(0.5f);
        } catch (Exception e) {
        }
    }

    public void play() { clip.start(); }
    public void loop() {clip.loop(Clip.LOOP_CONTINUOUSLY);}

    public void stop() {
        clip.stop();
    }
}

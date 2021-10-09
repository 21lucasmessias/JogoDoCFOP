package utils;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author cesar
 */
public class reprodutorMidia {

    String midia;

    public reprodutorMidia(String midia) {
        this.midia = midia;
    }

    public void tocar() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(midia).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            System.out.println("Error with playing sound.");
        }
    }
}

package com.progr.amador.TNText.Model;

import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Sound;
import com.progr.amador.TNText.State.MenuState;

import java.io.IOException;


public class Options {

    Sound sound = new Sound();

    private final int width;
    private final int height;
    private int current = 0;

    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public int getCurrent() {
        return current;
    }
    public void setCurrent(int current) {
        this.current = current;
    }


    public Options(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void toggleUpgrade() throws IOException {
        if(current == 0) Application.getInstance().togglePlusBomb();
        else if (current == 1) Application.getInstance().togglePlusPower();
        else if (current == 2) {
            Application.getInstance().setState(new MenuState(new Menu(15, 15)));
            Application.getInstance().getState().run();
        }
    }

    public void OptionChoosingMusic() {
        sound.setFile(0);
        sound.play();
        //sound.stop();

    }

}

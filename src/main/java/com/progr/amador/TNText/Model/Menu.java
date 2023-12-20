package com.progr.amador.TNText.Model;

import com.progr.amador.TNText.Sound;
import com.progr.amador.TNText.State.GameState;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.State.OptionsState;


import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class Menu {

    Sound sound = new Sound();
    private final int width;
    private final int height;
    private int current = 0;

    public Menu(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
    }

    public int getCurrent() {
        return current;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }


    public void setCurrent(int current) {
        this.current = current;
    }

    public void nextState() throws IOException {
        if(current == 0) {
            Application.getInstance().setState(new GameState(new Arena(15, 15)));
            Application.getInstance().getState().run();
        }
        else if (current == 1) {
            Application.getInstance().setState(new OptionsState(new Options(15, 15)));
            Application.getInstance().getState().run();
        }
        else if (current == 2) { getTerminal().getScreen().close(); }
    }

    public void MenuChoosingMusic() {
        sound.setFile(0);
        if( Application.getInstance().checkSound()) sound.play();
    }

}

package com.progr.amador.TNText.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Model.Elements.Element;
import com.progr.amador.TNText.Model.Elements.Text;
import com.progr.amador.TNText.State.GameState;
import com.progr.amador.TNText.State.MenuState;
import com.progr.amador.TNText.State.OptionsState;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class Options {

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

}

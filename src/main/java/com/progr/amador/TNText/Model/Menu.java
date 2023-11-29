package com.progr.amador.TNText.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.progr.amador.TNText.State.GameState;
import com.progr.amador.TNText.Application;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class Menu {
    private int width, height, current = 0;

    private Application app;

    public Menu(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
        graphics.putString(1, 0, "Welcome");
        graphics.putString(1, 1, "Game");
        graphics.putString(1, 2, "Options");
        graphics.putString(1, 3, "Exit");
    }

    public void nextState() throws IOException {
        if(current == 0) app.setState(new GameState());
        //else if (current == 1) application.setState(new OptionsState(terminal));
        else if (current == 2) { getTerminal().getScreen().close(); }
    }


    public boolean processKey(KeyStroke key, Screen screen) throws IOException {
        switch (key.getKeyType()) {
            case ArrowUp -> current = (current - 1) % 3;

            case ArrowDown -> current = (current + 1) % 3;

            case Enter -> { nextState(); return false;}

            case EOF -> {
                return true;
            }
        }
        return false;
    }
}

package com.progr.amador.TNText.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.progr.amador.TNText.Model.Elements.Element;
import com.progr.amador.TNText.State.GameState;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.State.OptionsState;


import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class Menu {
    private final int width;
    private final int height;
    private int current = 0;
    private final int entries = 3;

    private Application app;

    public Menu(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        new Element(1, 1).draw(graphics, "#9C929A", "Welcome");

        if(current == 0) new Element(1, 2).draw(graphics, "#6B93C5", "Game");
        else new Element(1, 2).draw(graphics, "#9C929A", "Game");

        if(current == 1) new Element(1, 3).draw(graphics, "#6B93C5", "Options");
        else new Element(1, 3).draw(graphics, "#9C929A", "Options");

        if(current == 2) new Element(1, 4).draw(graphics, "#6B93C5", "Exit");
        else new Element(1, 4).draw(graphics, "#9C929A", "Exit");

    }

    public void nextState() throws IOException {
        if(current == 0) app.setState(new GameState());
        else if (current == 1) app.setState(new OptionsState());
        else if (current == 2) { getTerminal().getScreen().close(); }
    }


    public boolean processKey(KeyStroke key, Screen screen) throws IOException {
        switch (key.getKeyType()) {
            case ArrowUp -> current = (current - 1 + entries) % entries;

            case ArrowDown -> current = (current + 1) % entries;

            case Enter -> { nextState(); return false;}

            case EOF -> {
                return true;
            }
        }
        return false;
    }
}

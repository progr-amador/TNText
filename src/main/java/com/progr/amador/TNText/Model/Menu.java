package com.progr.amador.TNText.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.progr.amador.TNText.Model.Elements.Element;
import com.progr.amador.TNText.Model.Elements.Text;
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

    public Menu(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        new Text(1, 1).draw(graphics, "Welcome", false);

        new Text(1, 2).draw(graphics, "Game", current == 0);

        new Text(1, 3).draw(graphics, "Options", current == 1);

        new Text(1, 4).draw(graphics, "Exit", current == 2);

    }

    public void nextState() throws IOException {
        if(current == 0) Application.getInstance().setState(new GameState());
        else if (current == 1) Application.getInstance().setState(new OptionsState());
        else if (current == 2) { getTerminal().getScreen().close(); }
    }


    public boolean processKey(KeyStroke key) throws IOException {
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

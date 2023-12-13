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

    public Menu(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        new Text(0, 1).draw(graphics, "    TNTEXT   ", false);

        new Text(0, 2).draw(graphics, "     GAME    ", current == 0);

        new Text(0, 3).draw(graphics, "   SETTINGS   ", current == 1);

        new Text(0, 4).draw(graphics, "     EXIT    " , current == 2);

        new Text(0, 6).draw(graphics, " \u0081 CONTROLS \u0082", true);


        new Element(1, 9).draw(graphics, "#6B93C5", "W   goUP   \u008A");
        new Element(1, 10).draw(graphics, "#6B93C5", "S   goDO   \u008B");
        new Element(1, 11).draw(graphics, "#6B93C5", "A   goLF   \u0088");
        new Element(1, 12).draw(graphics, "#6B93C5", "D   goRG   \u0089");
        new Element(1, 13).draw(graphics, "#6B93C5", "\u0087   bomb   \u008C");


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


    /*public boolean processKey(KeyStroke key) throws IOException {
        int entries = 3;
        switch (key.getKeyType()) {
            case ArrowUp -> current = (current - 1 + entries) % entries;

            case ArrowDown -> current = (current + 1) % entries;

            case Enter -> { nextState(); return false;}

            case EOF -> {
                return true;
            }
        }
        return false;
    } */
}

package com.progr.amador.TNText.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Model.Elements.Element;
import com.progr.amador.TNText.State.GameState;
import com.progr.amador.TNText.State.MenuState;
import com.progr.amador.TNText.State.OptionsState;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class Options {
    private final int width;
    private final int height;
    private int current = 0;
    private final int entries = 6;

    private Application app;

    public Options(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        if(current == 0) new Element(1, 1).draw(graphics, "#6B93C5", "Power 1");
        else new Element(1, 1).draw(graphics, "#9C929A", "Power 1");

        if(current == 1) new Element(1, 2).draw(graphics, "#6B93C5", "Power 2");
        else new Element(1, 2).draw(graphics, "#9C929A", "Power 2");

        if(current == 2) new Element(1, 3).draw(graphics, "#6B93C5", "Power 3");
        else new Element(1, 3).draw(graphics, "#9C929A", "Power 3");

        if(current == 3) new Element(1, 4).draw(graphics, "#6B93C5", "Power 4");
        else new Element(1, 4).draw(graphics, "#9C929A", "Power 4");

        if(current == 4) new Element(1, 5).draw(graphics, "#6B93C5", "Power 5");
        else new Element(1, 5).draw(graphics, "#9C929A", "Power 5");

        if(current == 5) new Element(1, 6).draw(graphics, "#6B93C5", "Menu");
        else new Element(1, 6).draw(graphics, "#9C929A", "Menu");

    }

    public void toggleUpgrade() throws IOException {
        if(current == 0) ;
        else if (current == 1) ;
        else if (current == 2) ;
        else if (current == 3) ;
        else if (current == 4) ;
        else if (current == 5) app.setState(new MenuState());
    }


    public boolean processKey(KeyStroke key, Screen screen) throws IOException {
        switch (key.getKeyType()) {
            case ArrowUp -> current = (current - 1 + entries) % entries;

            case ArrowDown -> current = (current + 1) % entries;

            case Enter -> { toggleUpgrade(); return false;}

            case EOF -> {
                return true;
            }
        }
        return false;
    }
}

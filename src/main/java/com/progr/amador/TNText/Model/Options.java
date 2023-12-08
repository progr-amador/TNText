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

    public Options(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        new Text(1, 1).draw(graphics, "Power 1", current == 0);
        new Text(1, 2).draw(graphics, "Power 2", current == 1);
        new Text(1, 3).draw(graphics, "Power 3", current == 2);
        new Text(1, 4).draw(graphics, "Power 4", current == 3);
        new Text(1, 5).draw(graphics, "Power 5", current == 4);
        new Text(1, 6).draw(graphics, "Menu", current == 5);

    }

    public void toggleUpgrade() throws IOException {
        /*if(current == 0) ;
        else if (current == 1) ;
        else if (current == 2) ;
        else if (current == 3) ;
        else if (current == 4) ;
        else */if (current == 5) {
            Application.getInstance().setState(new MenuState(new Menu(15, 15)));
            Application.getInstance().getState().run();
        }
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    /*public boolean processKey(KeyStroke key) throws IOException {
        int entries = 6;
        switch (key.getKeyType()) {
            case ArrowUp -> current = (current - 1 + entries) % entries;

            case ArrowDown -> current = (current + 1) % entries;

            case Enter -> { toggleUpgrade(); return false;}

            case EOF -> {
                return true;
            }
        }
        return false;
    }*/
}

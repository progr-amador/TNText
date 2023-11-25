package com.progr.amador.TNText.GUI;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class LanternaGUI {
    private Screen screen;

    public LanternaGUI(Screen screen) {this.screen = screen;}

    public Screen createScreen(Terminal terminal) throws IOException {
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
        return screen;
    }

    public Terminal createTerminal(int width)

    public void clear() {
        screen.clear();
    }
    public void refresh() throws IOException {
        screen.refresh();
    }
    public void close() throws IOException {
        screen.close();
    }


}

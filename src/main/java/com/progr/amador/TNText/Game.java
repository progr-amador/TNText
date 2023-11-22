package com.progr.amador.TNText;


import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.TerminalSize;

import java.io.IOException;

public class Game {
    Screen screen = null;
    Arena arena = new Arena(61, 21);

    public Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() throws IOException {
        while(true){
            try {
                draw(); // Call the private draw method within the Game class
            } catch (IOException e) {
                e.printStackTrace();
            }

            boolean over = false;
            KeyStroke key = screen.readInput();
            over = arena.processKey(key, screen);
            if (over) break;
        }
    }
}
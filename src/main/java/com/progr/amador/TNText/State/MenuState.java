package com.progr.amador.TNText.State;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Model.Menu;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class MenuState extends State{

    private Menu menu = new Menu(15, 15);

    public MenuState() throws IOException {
        run();
    }

    public void draw() throws IOException {
        getTerminal().getScreen().clear();
        menu.draw(getTerminal().getScreen().newTextGraphics());
        getTerminal().getScreen().refresh();
    }

    public void run() throws IOException {
        while(true){
            draw(); // Call the private draw method within the menu class

            boolean over;
            KeyStroke key = getTerminal().getScreen().readInput();
            over = menu.processKey(key);
            if (over) break;
        }
    }

}

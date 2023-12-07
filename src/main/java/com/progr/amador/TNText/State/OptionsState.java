package com.progr.amador.TNText.State;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Model.Menu;
import com.progr.amador.TNText.Model.Options;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class OptionsState extends State{

    private Options options = new Options(15, 15);

    public OptionsState() throws IOException {
        run();
    }

    public void draw() throws IOException {
        getTerminal().getScreen().clear();
        options.draw(getTerminal().getScreen().newTextGraphics());
        getTerminal().getScreen().refresh();
    }

    public void run() throws IOException {
        while(true){
            draw(); // Call the private draw method within the Game class

            boolean over;
            KeyStroke key = getTerminal().getScreen().readInput();
            over = options.processKey(key);
            if (over) break;
        }
    }
}

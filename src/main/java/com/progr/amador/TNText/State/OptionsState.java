package com.progr.amador.TNText.State;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Controller.Elements.OptionsController;
import com.progr.amador.TNText.Model.Menu;
import com.progr.amador.TNText.Model.Options;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class OptionsState extends State<Options>{

    public OptionsState(Options options) throws IOException {
        super(options);
        while(true){
            draw(); // Call the private draw method within the Game class

            boolean over;
            KeyStroke key = getTerminal().getScreen().readInput();
            over = getController().processKey(key);
            if (over) break;
        }
    }

    @Override
    protected OptionsController getController() {
        return new OptionsController(getModel());
    }

    public void draw() throws IOException {
        getTerminal().getScreen().clear();
        getModel().draw(getTerminal().getScreen().newTextGraphics());
        getTerminal().getScreen().refresh();
    }


}

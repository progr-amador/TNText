package com.progr.amador.TNText.State;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Controller.Elements.OptionsController;
import com.progr.amador.TNText.Model.Options;
import com.progr.amador.TNText.Viewer.OptionsViewer;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class OptionsState extends State<Options>{

    private final OptionsController optionsController = new OptionsController(getModel());
    private final OptionsViewer optionsViewer = new OptionsViewer(getModel());

    public OptionsState(Options options){
        super(options);
    }

    public void run() throws IOException {
        while(true){
            draw(); // Call the private draw method within the Game class

            KeyStroke key = getTerminal().getScreen().readInput();
            optionsController.processKey(key);
        }
    }

    @Override
    protected Controller<Options> getController() {
        return optionsController;
    }

    public void draw() throws IOException {
        getTerminal().getScreen().clear();
        optionsViewer.draw(getTerminal().getScreen().newTextGraphics());
        getTerminal().getScreen().refresh();
    }


}

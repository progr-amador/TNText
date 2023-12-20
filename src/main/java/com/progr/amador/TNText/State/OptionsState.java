package com.progr.amador.TNText.State;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Controller.OptionsController;
import com.progr.amador.TNText.Model.Options;
import com.progr.amador.TNText.Viewer.OptionsViewer;
import com.progr.amador.TNText.Viewer.Viewer;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class OptionsState extends State<Options>{
    private OptionsController optionsController;
    private OptionsViewer optionsViewer;

    public OptionsState(Options options){
        super(options);
    }

    @Override
    public Controller<Options> getController() {
        if (optionsController == null) optionsController = new OptionsController(getModel());
        return optionsController;
    }

    @Override
    public Viewer<Options> getViewer() {
        if (optionsViewer == null) optionsViewer = new OptionsViewer(getModel());
        return optionsViewer;
    }

    public void draw() throws IOException {
        getTerminal().getScreen().clear();
        getViewer().draw();
        getTerminal().getScreen().refresh();
    }

    public void run() throws IOException {
        while(true){
            draw(); // Call the private draw method within the Game class
            KeyStroke key = getTerminal().getScreen().readInput();
            getController().processKey(key);
        }
    }


}

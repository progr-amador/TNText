package com.progr.amador.TNText.Controller.Elements;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Model.Options;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class OptionsController extends Controller<Options> {


    public OptionsController(Options options) {
        super(options);
    }


    public void processKey(KeyStroke key) throws IOException {
        int entries = 4;
        switch (key.getKeyType()) {
            case ArrowUp -> {getModel().setCurrent((getModel().getCurrent() - 1 + entries) % entries); getModel().OptionChoosingMusic();}

            case ArrowDown -> {getModel().setCurrent((getModel().getCurrent() + 1) % entries); getModel().OptionChoosingMusic(); }

            case Enter -> getModel().toggleUpgrade();

            case Escape -> getTerminal().getScreen().close();

            case EOF -> System.exit(0);
        }
    }
}
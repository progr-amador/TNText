package com.progr.amador.TNText.Controller.Elements;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Model.Options;

import java.io.IOException;

public class OptionsController extends Controller<Options> {


    public OptionsController(Options options) {
        super(options);
    }


    public boolean processKey(KeyStroke key) throws IOException {
        int entries = 6;
        switch (key.getKeyType()) {
            case ArrowUp -> this.getModel().setCurrent((this.getModel().getCurrent() - 1 + entries) % entries);

            case ArrowDown -> this.getModel().setCurrent((this.getModel().getCurrent() + 1) % entries);

            case Enter -> this.getModel().toggleUpgrade();

            case EOF -> {
                return true;
            }
        }
        return false;
    }
}
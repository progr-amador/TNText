package com.progr.amador.TNText.Controller.Elements;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Model.Menu;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;


public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu) {
        super(menu);
    }

    public void processKey(KeyStroke key) throws IOException {
        int entries = 3;
        switch (key.getKeyType()) {
            case Character -> { if (key.getCharacter() == 'q' || key.getCharacter() == 'Q') getTerminal().getScreen().close(); }

            case ArrowUp -> this.getModel().setCurrent((this.getModel().getCurrent() - 1 + entries) % entries);

            case ArrowDown -> this.getModel().setCurrent((this.getModel().getCurrent() + 1) % entries);

            case Enter -> this.getModel().nextState();

            case EOF -> System.exit(0);
        }
    }


}

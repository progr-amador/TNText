package com.progr.amador.TNText;

import com.progr.amador.TNText.Model.Menu;
import com.progr.amador.TNText.State.*;

import java.io.IOException;

public class Application {
    private static Application instance;
    private State state;
    private final static TerminalGUI terminal = new TerminalGUI();

    public static Application getInstance() {
        if (instance == null) instance = new Application();
        return instance;
    }

    public static void main(String[] args) throws IOException {
        getInstance();
        instance.state = new MenuState(new Menu(15, 15));
        instance.state.run();
    }

    public void setState(State state) {
        instance.state = state;
    }

    public State getState() {
        return instance.state;
    }

    public static TerminalGUI getTerminal() {
        return terminal;
    }


}


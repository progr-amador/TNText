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
        instance.state = new MenuState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public static TerminalGUI getTerminal() {
        return terminal;
    }


}


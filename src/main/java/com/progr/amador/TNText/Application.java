package com.progr.amador.TNText;

import com.progr.amador.TNText.Model.Menu;
import com.progr.amador.TNText.State.*;

import java.io.IOException;

public class Application {
    private static State state;
    private final static TerminalGUI terminal = new TerminalGUI();

    public Application() throws IOException {
        state = new MenuState();
    }

    public static void main(String[] args) throws IOException {
        new Application();
    }

    public void setState(State state) {
        Application.state = state;
    }

    public static TerminalGUI getTerminal() {
        return terminal;
    }


}


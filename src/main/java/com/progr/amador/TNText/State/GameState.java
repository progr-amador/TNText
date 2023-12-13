package com.progr.amador.TNText.State;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Controller.Elements.ArenaController;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.Elements.Explosion;
import com.progr.amador.TNText.TerminalGUI;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static com.progr.amador.TNText.Application.getTerminal;

public class GameState extends State<Arena> {
    private final ArenaController arenaController = new ArenaController(getModel());

    public GameState(Arena arena)  {
        super(arena);
    }

    @Override
    protected Controller<Arena> getController() {
        return arenaController;
    }

    public void draw() throws IOException {
        getTerminal().getScreen().clear();
        getModel().draw(getTerminal().getScreen().newTextGraphics());
        getTerminal().getScreen().refresh();
    }

    public void run() throws IOException {
        while (true) {
            if(getModel().getVictor() == -1) {
                getModel().whoWon();
                draw(); // Call the private draw method within the Game class
            }

            // Check if input is available before reading
            KeyStroke key = getTerminal().getScreen().pollInput();
            if (key != null) arenaController.processKey(getModel().getPlayer1(), getModel().getPlayer2(), key, getTerminal().getScreen(), getModel().getVictor());

            // Insert a small delay if no input is available to prevent tight looping
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
package com.progr.amador.TNText.State;


import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Controller.Elements.ArenaController;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.TerminalGUI;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class GameState extends State<Arena> {
    ArenaController arenaController = new ArenaController(new Arena(15,15));
    private boolean isRunning = true;

    public GameState(Arena arena) throws IOException {
        super(arena);
        run();
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(getModel());
    }

    public void draw() throws IOException {
        getTerminal().getScreen().clear();
        try {
            arenaController.getPlayerController().getModel().draw(getTerminal().getScreen().newTextGraphics());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        getTerminal().getScreen().refresh();
    }

    public void run() throws IOException {
        while (true) {
            draw(); // Call the private draw method within the Game class
            // Check if input is available before reading

            KeyStroke key = getTerminal().getScreen().pollInput();
            if (key != null) {
                boolean over = arenaController.getPlayerController().processKey(arenaController.getModel().getPlayer1(), arenaController.getModel().getPlayer2(), key, getTerminal().getScreen(), isRunning);
                if (over) break;
            }


            if(arenaController.getPlayerController().getModel().whoExploded() != null) isRunning = false;

            // Insert a small delay if no input is available to prevent tight looping
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
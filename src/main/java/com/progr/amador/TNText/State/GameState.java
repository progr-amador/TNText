package com.progr.amador.TNText.State;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Controller.ArenaController;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.ArenaBuilder;
import com.progr.amador.TNText.Viewer.ArenaViewer;
import com.progr.amador.TNText.Viewer.Viewer;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class GameState extends State<Arena> {
    private ArenaController arenaController;
    private ArenaViewer arenaViewer;
    private final ArenaBuilder arenaBuilder = new ArenaBuilder(getModel());



    public GameState(Arena arena)  {
        super(arena);
    }

    @Override
    public Controller<Arena> getController() {
        if (arenaController == null) arenaController = new ArenaController(getModel());
        return arenaController;
    }

    @Override
    public Viewer<Arena> getViewer() {
        if (arenaViewer == null) arenaViewer = new ArenaViewer(getModel());
        return arenaViewer;
    }

    public ArenaBuilder getArenaBuilder() {
        return arenaBuilder;
    }

    public void draw() throws IOException {
        getTerminal().getScreen().clear();
        getViewer().draw();
        getTerminal().getScreen().refresh();
    }

    public void run() throws IOException {
        arenaBuilder.run();
        if(Application.getInstance().checkSound()) getModel().playMusic();
        while (true) {
            if(getModel().getVictor() == -1) {
                getModel().whoWon();
                draw(); // Call the private draw method within the Game class
            }

            // Check if input is available before reading
            KeyStroke key = getTerminal().getScreen().pollInput();
            if (key != null) getController().processKey(key, getModel().getVictor(), arenaBuilder);

            // Insert a small delay if no input is available to prevent tight looping
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
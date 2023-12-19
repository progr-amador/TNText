package com.progr.amador.TNText.State;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Controller.Elements.ArenaController;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.ArenaBuilder;
import com.progr.amador.TNText.Viewer.ArenaViewer;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class GameState extends State<Arena> {
    private final ArenaController arenaController = new ArenaController(getModel());
    private final ArenaViewer arenaViewer = new ArenaViewer(getModel());
    private final ArenaBuilder arenaBuilder = new ArenaBuilder(getModel());

    public GameState(Arena arena)  {
        super(arena);
    }

    public void draw() throws IOException {
        getTerminal().getScreen().clear();
        arenaViewer.draw();
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
            if (key != null) arenaController.processKey(key, getModel().getVictor(), arenaBuilder);

            // Insert a small delay if no input is available to prevent tight looping
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
package com.progr.amador.TNText.State;


import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Controller.Elements.ArenaController;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.TerminalGUI;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class GameState extends State {
    ArenaController arenaController = new ArenaController(new Arena(15,15));

    public GameState() throws IOException {
        run();
    }

    public void draw() throws IOException {
        getTerminal().getScreen().clear();
        arenaController.getPlayerController().getModel().draw(getTerminal().getScreen().newTextGraphics());
        getTerminal().getScreen().refresh();
    }

    public void run() throws IOException {
        while(true){
            try {
                draw(); // Call the private draw method within the Game class
            } catch (IOException e) {
                e.printStackTrace();
            }

            boolean over;
            KeyStroke key = getTerminal().getScreen().readInput();
            over = arenaController.getPlayerController().processKey(arenaController.getModel().getPlayer1(), arenaController.getModel().getPlayer2(), key, getTerminal().getScreen()) ;
            if (over) break;
        }
    }
}
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
        Thread drawThread = new Thread(() -> {
            try {
                draw();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread runThread = new Thread(() -> {
            try {
                run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        drawThread.start();
        runThread.start();
    }

    public void draw() throws IOException {
        while (true) {
            getTerminal().getScreen().clear();
            arenaController.getPlayerController().getModel().draw(getTerminal().getScreen().newTextGraphics());
            getTerminal().getScreen().refresh();

            try {
                Thread.sleep(100); // Adjust sleep time as needed for your game's responsiveness
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() throws IOException {
        while(true){
            draw(); // Call the private draw method within the Game class
            boolean over;
            KeyStroke key = getTerminal().getScreen().readInput();
            over = arenaController.getPlayerController().processKey(arenaController.getModel().getPlayer1(), arenaController.getModel().getPlayer2(), key, getTerminal().getScreen()) ;
            if (over) break;
        }
    }
}

/*
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
            draw(); // Call the private draw method within the Game class
            boolean over;
            KeyStroke key = getTerminal().getScreen().readInput();
            over = arenaController.getPlayerController().processKey(arenaController.getModel().getPlayer1(), arenaController.getModel().getPlayer2(), key, getTerminal().getScreen()) ;
            if (over) break;
        }
    }
 */
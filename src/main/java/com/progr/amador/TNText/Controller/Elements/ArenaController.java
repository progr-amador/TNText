package com.progr.amador.TNText.Controller.Elements;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.Elements.Player;
import com.progr.amador.TNText.Model.Menu;
import com.progr.amador.TNText.State.MenuState;

import java.io.IOException;

public class ArenaController extends GameController {
    private final Player1Controller player1Controller;
    private final Player2Controller player2Controller;


    public ArenaController(Arena arena) {
        super(arena);
        this.player1Controller = new Player1Controller(arena);
        this.player2Controller = new Player2Controller(arena);
    }

    public void processKey(Player player1, Player player2, KeyStroke key, Screen screen, boolean isRunning) throws IOException {
        switch (key.getKeyType()) {
            case Character -> {
                if ((key.getCharacter() == 'w' || key.getCharacter() == 'W' ||
                    key.getCharacter() == 'a' || key.getCharacter() == 'A' ||
                    key.getCharacter() == 's' || key.getCharacter() == 'S' ||
                    key.getCharacter() == 'd' || key.getCharacter() == 'D' ||
                    key.getCharacter() == ' ') && isRunning) player1Controller.processKey(player1, key);

                else if (key.getCharacter() == 'q' || key.getCharacter() == 'Q') screen.close();
                else if (key.getCharacter() == 'm' || key.getCharacter() == 'M') {
                    Application.getInstance().setState(new MenuState(new Menu(15, 15)));
                    Application.getInstance().getState().run();
                }
            }

            case ArrowLeft, ArrowRight, ArrowUp, ArrowDown, Enter -> {if(isRunning) player2Controller.processKey(player2, key); }

            case EOF -> System.exit(0);

        }
    }
}

package com.progr.amador.TNText.Controller.Elements;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.Elements.Player;
import com.progr.amador.TNText.Model.Menu;
import com.progr.amador.TNText.State.MenuState;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class ArenaController extends GameController {
    private final Player1Controller player1Controller;
    private final Player2Controller player2Controller;

    public ArenaController(Arena arena) {
        super(arena);
        this.player1Controller = new Player1Controller(arena);
        this.player2Controller = new Player2Controller(arena);
    }

    public void processKey(KeyStroke key, int victor) throws IOException {
        switch (key.getKeyType()) {
            case Character -> {
                if ((key.getCharacter() == 'w' || key.getCharacter() == 'W' ||
                    key.getCharacter() == 'a' || key.getCharacter() == 'A' ||
                    key.getCharacter() == 's' || key.getCharacter() == 'S' ||
                    key.getCharacter() == 'd' || key.getCharacter() == 'D' ||
                    key.getCharacter() == ' ') && victor == -1) player1Controller.processKey(key);

                else if (key.getCharacter() == 'm' || key.getCharacter() == 'M') {
                    Application.getInstance().setState(new MenuState(new Menu(15, 15)));
                    Application.getInstance().getState().run();
                }
            }

            case Escape -> getTerminal().getScreen().close();

            case ArrowLeft, ArrowRight, ArrowUp, ArrowDown, Enter -> {if(victor == -1) player2Controller.processKey(key); }

            case EOF -> System.exit(0);

        }
    }
}

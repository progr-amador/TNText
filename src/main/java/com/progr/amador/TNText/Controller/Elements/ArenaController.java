package com.progr.amador.TNText.Controller.Elements;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.Menu;
import com.progr.amador.TNText.State.MenuState;

import java.io.IOException;

public class ArenaController extends GameController {
    private PlayerController playerController;

    public ArenaController(Arena arena) {
        super(arena);
        this.playerController = new PlayerController(arena);
    }

    public PlayerController getPlayerController() {
        return playerController;
    }
}

package com.progr.amador.TNText.Controller.Elements;

import com.progr.amador.TNText.Arena;
import com.progr.amador.TNText.Player;

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

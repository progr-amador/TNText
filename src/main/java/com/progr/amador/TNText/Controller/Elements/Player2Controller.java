package com.progr.amador.TNText.Controller.Elements;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Controller.GameController;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.ArenaBuilder;
import com.progr.amador.TNText.Model.Elements.Bomb;
import com.progr.amador.TNText.Model.Elements.Player;


public class Player2Controller extends GameController {
    public Player2Controller(Arena arena) { super(arena);}

    public void processKey(KeyStroke key, ArenaBuilder arenaBuilder) {
        Player player2 = getModel().getPlayer2();
        switch (key.getKeyType()) {
            case ArrowLeft -> movePlayer(player2, player2.getPosition().getLeft());

            case ArrowRight -> movePlayer(player2, player2.getPosition().getRight());

            case ArrowUp -> movePlayer(player2, player2.getPosition().getUp());

            case ArrowDown -> movePlayer(player2, player2.getPosition().getDown());

            case Enter -> arenaBuilder.addBomb(new Bomb(player2));

            case EOF -> System.exit(0);

        }
    }
}

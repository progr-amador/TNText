package com.progr.amador.TNText.Controller.Elements;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.Elements.Bomb;
import com.progr.amador.TNText.Model.Elements.Player;


public class Player2Controller extends GameController{
    public Player2Controller(Arena arena) { super(arena);}

    // assumindo que o element passa a ter um can element move, maybe no game controller?
    /*
    public void _movePlayer(Player player, Position position) {
        if (canElementMove(position) == null) player.setPosition(position);
    }*/


    public void processKey(KeyStroke key) {
        Player player2 = getModel().getPlayer2();
        switch (key.getKeyType()) {
            case ArrowLeft -> movePlayer(player2, player2.getPosition().getLeft());

            case ArrowRight -> movePlayer(player2, player2.getPosition().getRight());

            case ArrowUp -> movePlayer(player2, player2.getPosition().getUp());

            case ArrowDown -> movePlayer(player2, player2.getPosition().getDown());

            case Enter -> getModel().addBomb(new Bomb(player2));

            case EOF -> System.exit(0);

        }
    }
}

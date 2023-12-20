package com.progr.amador.TNText.Controller.Elements;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Controller.GameController;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.ArenaBuilder;
import com.progr.amador.TNText.Model.Elements.Player;
import com.progr.amador.TNText.Model.Elements.Bomb;

public class Player1Controller extends GameController {

    public Player1Controller(Arena arena) {super (arena);}

    public void processKey(KeyStroke key, ArenaBuilder arenaBuilder) {
        Player player1 = getModel().getPlayer1();
        switch (key.getKeyType()) {
            case Character -> {
                if      (key.getCharacter() == 'w' || key.getCharacter() == 'W') movePlayer(player1, player1.getPosition().getUp());
                else if (key.getCharacter() == 'a' || key.getCharacter() == 'A') movePlayer(player1, player1.getPosition().getLeft());
                else if (key.getCharacter() == 's' || key.getCharacter() == 'S') movePlayer(player1, player1.getPosition().getDown());
                else if (key.getCharacter() == 'd' || key.getCharacter() == 'D') movePlayer(player1, player1.getPosition().getRight());
                else if (key.getCharacter() == ' ') arenaBuilder.addBomb(new Bomb(player1));
            }
            case EOF -> System.exit(0);

        }
    }

}

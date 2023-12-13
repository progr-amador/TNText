package com.progr.amador.TNText.Controller.Elements;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.Elements.Brick;
import com.progr.amador.TNText.Model.Elements.Player;
import com.progr.amador.TNText.Model.Elements.Wood;
import com.progr.amador.TNText.Model.Elements.Bomb;
import com.progr.amador.TNText.Model.Position;

import java.io.IOException;

public class Player1Controller extends GameController{

    public Player1Controller(Arena arena) {super (arena);}

    public void movePlayer(Player player, Position position) {
        for (Brick brick : this.getModel().getBricks()) {
            if (brick.getPosition().equals(position)) return;
        }
        for (Wood wood : this.getModel().getWoods()) {
            if (wood.getPosition().equals(position)) return;
        }
        player.setPosition(position);
    }

    // assumindo que o element passa a ter um can element move, maybe no game controller?
    /*
    public void _movePlayer(Player player, Position position) {
        if (canElementMove(position) == null) player.setPosition(position);
    }*/


    public void processKey(Player player1, KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case Character -> {
                if      (key.getCharacter() == 'w' || key.getCharacter() == 'W') movePlayer(player1, player1.getPosition().getUp());
                else if (key.getCharacter() == 'a' || key.getCharacter() == 'A') movePlayer(player1, player1.getPosition().getLeft());
                else if (key.getCharacter() == 's' || key.getCharacter() == 'S') movePlayer(player1, player1.getPosition().getDown());
                else if (key.getCharacter() == 'd' || key.getCharacter() == 'D') movePlayer(player1, player1.getPosition().getRight());
                else if (key.getCharacter() == ' ') getModel().addBomb(new Bomb(player1));
            }
            case EOF -> System.exit(0);

        }
    }

}

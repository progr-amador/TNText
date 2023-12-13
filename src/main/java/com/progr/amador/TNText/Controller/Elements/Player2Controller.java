package com.progr.amador.TNText.Controller.Elements;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.Elements.Bomb;
import com.progr.amador.TNText.Model.Elements.Brick;
import com.progr.amador.TNText.Model.Elements.Player;
import com.progr.amador.TNText.Model.Elements.Wood;
import com.progr.amador.TNText.Model.Menu;
import com.progr.amador.TNText.Model.Position;
import com.progr.amador.TNText.State.MenuState;

import java.io.IOException;

public class Player2Controller extends GameController{
    public Player2Controller(Arena arena) { super(arena);}

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


    public void processKey(Player player2, KeyStroke key) throws IOException {
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

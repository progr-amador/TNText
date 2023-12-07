package com.progr.amador.TNText.Controller.Elements;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.Elements.Brick;
import com.progr.amador.TNText.Model.Elements.Player;
import com.progr.amador.TNText.Model.Elements.Wood;
import com.progr.amador.TNText.Model.Elements.Bomb;
import com.progr.amador.TNText.Model.Position;

import java.io.IOException;

public class PlayerController extends GameController{

    private Arena arena; // se alguém encontrar uma maneira melhor (acho que qualquer coisa é melhor) de
                         // extrair o arena que é utilizado no construtor para poder usar no processkey
                         // ou talvez uma maneira de mudar o addBomb de sitio para não ter de ser um método da arena

    public PlayerController(Arena arena) { super(arena); this.arena = arena; /* PARTNER IN CRIME */ }

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


    public boolean processKey(Player player1, Player player2, KeyStroke key, Screen screen) throws IOException {
        switch (key.getKeyType()) {
            case Character -> {
                if      (key.getCharacter() == 'w' || key.getCharacter() == 'W') movePlayer(player1, player1.getPosition().getUp());
                else if (key.getCharacter() == 'a' || key.getCharacter() == 'A') movePlayer(player1, player1.getPosition().getLeft());
                else if (key.getCharacter() == 's' || key.getCharacter() == 'S') movePlayer(player1, player1.getPosition().getDown());
                else if (key.getCharacter() == 'd' || key.getCharacter() == 'D') movePlayer(player1, player1.getPosition().getRight());
                else if (key.getCharacter() == ' ') arena.addBomb(new Bomb(player1.getPosition().getX(), player1.getPosition().getY(), player1.getPower()));

                else if (key.getCharacter() == 'q' || key.getCharacter() == 'Q') screen.close();
                // m should go back to menu.
            }

            case ArrowLeft -> movePlayer(player2, player2.getPosition().getLeft());

            case ArrowRight -> movePlayer(player2, player2.getPosition().getRight());

            case ArrowUp -> movePlayer(player2, player2.getPosition().getUp());

            case ArrowDown -> movePlayer(player2, player2.getPosition().getDown());

            case Enter -> arena.addBomb(new Bomb(player2.getPosition().getX(), player2.getPosition().getY(), player2.getPower()));

            case EOF -> { return true; }

        }
        return false;
    }

}

package com.progr.amador.TNText;

import com.progr.amador.TNText.Controller.Elements.Player1Controller;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.Elements.Player;
import com.progr.amador.TNText.Model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerControllertest {
    Arena testzone;
    Player1Controller player1Controller;
    Position specific_brick_place;
    Player one;


    @BeforeEach
    public void helper() {
        testzone = new Arena(15, 15);
        player1Controller = new Player1Controller(testzone);

    }

    @Test
    public void movePlayer1() {
        one = new Player(1, 1);
        specific_brick_place = new Position(0, 1);
        player1Controller.movePlayer(one, specific_brick_place);
        Assertions.assertEquals(new Position(1, 1), one.getPosition());
    }

    @Test
    public void movePlayer2() {
        one = new Player(1, 1);
        specific_brick_place = new Position(1, 2);
        player1Controller.movePlayer(one, specific_brick_place);
        Assertions.assertEquals(new Position(1, 2), one.getPosition());
    }

    @Test
    public void movePlayer3() {
        one = new Player(13, 13);
        specific_brick_place = new Position(14, 13);
        player1Controller.movePlayer(one, specific_brick_place);
        Assertions.assertEquals(new Position(13, 13), one.getPosition());
    }

    @Test
    public void movePlayer4() {
        one = new Player(13, 13);
        specific_brick_place = new Position(13, 12);
        player1Controller.movePlayer(one, specific_brick_place);
        Assertions.assertEquals(new Position(13, 12), one.getPosition());
    }

}

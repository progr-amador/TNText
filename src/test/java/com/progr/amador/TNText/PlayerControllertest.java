package com.progr.amador.TNText;

import com.progr.amador.TNText.Controller.Elements.PlayerController;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.Elements.Player;
import com.progr.amador.TNText.Model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PlayerControllertest {
    Arena testzone;
    PlayerController playerController;
    Position specific_brick_place;
    Player one;
    Player two;


    @BeforeEach
    public void helper() {
        testzone = new Arena(15,15);
        playerController = new PlayerController(testzone);
        specific_brick_place = new Position(0,1);
        one = new Player(1,1);
        two = new Player(13,13);
    }

    @Test
    public void movePlayer() {
        playerController.movePlayer(one,specific_brick_place);
        Assertions.assertEquals(new Position(1,1),one.getPosition());
    }




}

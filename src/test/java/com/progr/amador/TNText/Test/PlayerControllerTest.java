package com.progr.amador.TNText.Test;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.progr.amador.TNText.Controller.Elements.Player1Controller;
import com.progr.amador.TNText.Controller.Elements.Player2Controller;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.ArenaBuilder;
import com.progr.amador.TNText.Model.Elements.Explosion;
import com.progr.amador.TNText.Model.Elements.Powerup.PlusBomb;
import com.progr.amador.TNText.Model.Elements.Powerup.PlusPower;
import com.progr.amador.TNText.Model.Elements.Wood;
import com.progr.amador.TNText.Model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static com.googlecode.lanterna.input.KeyType.*;

public class PlayerControllerTest {
    Arena testzone;
    ArenaBuilder builder;
    Player1Controller player1Controller;
    Player2Controller player2Controller;
    Position specific_place;



    @BeforeEach
    public void helper() {
        testzone = new Arena(15, 15);
        builder = new ArenaBuilder(testzone);
        builder.run();
        player1Controller = new Player1Controller(testzone);
        player2Controller = new Player2Controller(testzone);
    }


    // Against bricks -----------------------------------------------------------------------------------------------

    @Test
    public void movePlayerAgainstBrick1() {
        specific_place = new Position(0, 1);
        player1Controller.movePlayer(testzone.getPlayer1(), specific_place);
        Assertions.assertNotEquals(specific_place, testzone.getPlayer1().getPosition());
    }

    @Test
    public void movePlayerAgainstBrick2() {
        specific_place = new Position(1, 2);
        player1Controller.movePlayer(testzone.getPlayer1(), specific_place);
        Assertions.assertEquals(new Position(1, 2), testzone.getPlayer1().getPosition());
    }

    @Test
    public void movePlayerAgainstBrick3() {
        specific_place = new Position(14, 13);
        player2Controller.movePlayer(testzone.getPlayer2(), specific_place);
        Assertions.assertEquals(new Position(13, 13), testzone.getPlayer2().getPosition());
    }

    @Test
    public void movePlayerAgainstBrick4() {

        specific_place = new Position(13, 12);
        player2Controller.movePlayer(testzone.getPlayer2(), specific_place);
        Assertions.assertEquals(new Position(13, 12), testzone.getPlayer2().getPosition());
    }

    @Test
    public void movePlayerAgainstBrick5() {
        specific_place = new Position(13, 14);
        player2Controller.movePlayer(testzone.getPlayer2(), specific_place);
        player2Controller.movePlayer(testzone.getPlayer2(), specific_place);
        Assertions.assertEquals(new Position(13, 13), testzone.getPlayer2().getPosition());
    }


    // Against woods -----------------------------------------------------------------------------------------------

    @Test
    public void movePlayerAgainstWood1(){

        specific_place = new Position(2, 1);
        testzone.getWoods().add(new Wood(specific_place.getX(), specific_place.getY()));

        player1Controller.movePlayer(testzone.getPlayer1(), specific_place);
        Assertions.assertEquals(new Position(1, 1), testzone.getPlayer1().getPosition());
    }

    @Test
    public void movePlayerAgainstWood2(){

        specific_place = new Position(12, 13);
        testzone.getWoods().add(new Wood(specific_place.getX(), specific_place.getY()));

        player2Controller.movePlayer(testzone.getPlayer2(), specific_place);
        Assertions.assertEquals(new Position(13, 13), testzone.getPlayer2().getPosition());
    }

    // Against powerups -----------------------------------------------------------------------------------------------

    @Test
    public void movePlayerAgainstPowerup1(){
        specific_place = new Position(2, 1);

        testzone.getPowerups().add(new PlusBomb(specific_place.getX(),specific_place.getY()));
        player1Controller.movePlayer(testzone.getPlayer1(), specific_place);
        Assertions.assertEquals(2, testzone.getPlayer1().getBag());
    }

    @Test
    public void movePlayerAgainstPowerup2(){
        specific_place = new Position(12, 13);

        testzone.getPowerups().add(new PlusPower(specific_place.getX(),specific_place.getY()));
        player2Controller.movePlayer(testzone.getPlayer2(), specific_place);
        Assertions.assertEquals(3, testzone.getPlayer2().getPower());
    }

    @Test
    public void movePlayerAgainstPowerup3(){

        Position original = new Position(1, 1);
        specific_place = new Position(2, 1);

        testzone.getPowerups().add(new PlusBomb(specific_place.getX(),specific_place.getY()));
        player1Controller.movePlayer(testzone.getPlayer1(), specific_place);
        player1Controller.movePlayer(testzone.getPlayer1(), original);
        player1Controller.movePlayer(testzone.getPlayer1(), specific_place);
        Assertions.assertEquals(2, testzone.getPlayer1().getBag());
    }

    // Against explosions -----------------------------------------------------------------------------------------------

    @Test
    public void movePlayerAgainstExplosion1(){

        specific_place = new Position(2, 1);

        testzone.getExplosions().add(new Explosion(specific_place.getX(),specific_place.getY()));
        player1Controller.movePlayer(testzone.getPlayer1(), specific_place);
        testzone.whoWon();
        Assertions.assertNotEquals(testzone.getVictor(), -1);
    }

    @Test
    public void movePlayerAgainstExplosion2(){

        specific_place = new Position(13, 12);

        testzone.getExplosions().add(new Explosion(specific_place.getX(),specific_place.getY()));
        player2Controller.movePlayer(testzone.getPlayer2(), specific_place);
        testzone.whoWon();
        Assertions.assertNotEquals(testzone.getVictor(), -1);

    }

    // ProcessKey -----------------------------------------------------------

    @Test
    public void processKeyPlayer2(){
        specific_place = new Position(13, 12);
        KeyStroke key = new KeyStroke(ArrowUp);

        player2Controller.processKey(key, builder);

        Assertions.assertEquals(specific_place, testzone.getPlayer2().getPosition());

        specific_place = new Position(13, 13);
        key = new KeyStroke(ArrowDown);

        player2Controller.processKey(key, builder);

        Assertions.assertEquals(specific_place, testzone.getPlayer2().getPosition());

        specific_place = new Position(12, 13);
        key = new KeyStroke(ArrowLeft);

        player2Controller.processKey(key, builder);

        Assertions.assertEquals(specific_place, testzone.getPlayer2().getPosition());

        specific_place = new Position(13, 13);
        key = new KeyStroke(ArrowRight);

        player2Controller.processKey(key, builder);

        Assertions.assertEquals(specific_place, testzone.getPlayer2().getPosition());
    }

    @Test
    public void processKeyPlayer2Bomb(){
        KeyStroke key = new KeyStroke(Enter);

        player2Controller.processKey(key, builder);

        Assertions.assertEquals(1, testzone.getBombs().size());
    }

}

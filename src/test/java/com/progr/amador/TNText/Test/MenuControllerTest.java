package com.progr.amador.TNText.Test;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Controller.MenuController;
import com.progr.amador.TNText.Model.Menu;
import com.progr.amador.TNText.State.GameState;
import com.progr.amador.TNText.State.MenuState;
import com.progr.amador.TNText.State.OptionsState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.*;

public class MenuControllerTest {

    MenuController menuController;
    MenuState menuState;
    Menu menu;
    KeyStroke keyup;
    KeyStroke keydown;
    KeyStroke enter;


    @BeforeEach
    public void helper() throws IOException {
        menu = new Menu(15, 15);
        menuController = new MenuController(menu);
        keyup = new KeyStroke(ArrowUp);
        keydown = new KeyStroke(ArrowDown);
        enter = new KeyStroke(Enter);
    }

    // KeyUp --------------------------------------------------------------------------------------------------

    @Test
    public void ArrowUpPressing1() throws IOException {
        menuController.processKey(keyup);
        Assertions.assertEquals(2,menu.getCurrent());
    }

    @Test
    public void ArrowUpPressing2() throws IOException {
        menuController.processKey(keyup);
        menuController.processKey(keyup);
        menuController.processKey(keyup);
        Assertions.assertEquals(0,menu.getCurrent());
    }

    // KeyDown ------------------------------------------------------------------------------------------------

    @Test
    public void ArrowDownPressing1() throws IOException {
        menuController.processKey(keydown);
        Assertions.assertEquals(1,menu.getCurrent());
    }


    @Test
    public void ArrowDownPressing2() throws IOException {
        menuController.processKey(keydown);
        menuController.processKey(keydown);
        menuController.processKey(keydown);
        Assertions.assertEquals(0,menu.getCurrent());
    }


}

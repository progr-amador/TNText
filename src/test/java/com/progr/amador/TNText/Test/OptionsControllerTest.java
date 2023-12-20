package com.progr.amador.TNText.Test;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Controller.OptionsController;
import com.progr.amador.TNText.Model.Options;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.ArrowDown;
import static com.googlecode.lanterna.input.KeyType.ArrowUp;

public class OptionsControllerTest {
    
    OptionsController optionsController;
    Options options;
    
    KeyStroke keyup;
    KeyStroke keydown;

    @BeforeEach
    public void helper() throws IOException {
        options = new Options(15, 15);
        optionsController = new OptionsController(options);
        keyup = new KeyStroke(ArrowUp);
        keydown = new KeyStroke(ArrowDown);
    }


    // KeyUp ------------------------------------------------------------------------------------------------

    @Test
    public void ArrowUpPressing1() throws IOException {
        optionsController.processKey(keyup);
        Assertions.assertEquals(3,options.getCurrent());
    }

    @Test
    public void ArrowUpPressing2() throws IOException {
        optionsController.processKey(keyup);
        optionsController.processKey(keyup);
        optionsController.processKey(keyup);
        optionsController.processKey(keyup);
        Assertions.assertEquals(0,options.getCurrent());
    }

    // KeyDown ------------------------------------------------------------------------------------------------

    @Test
    public void ArrowDownPressing1() throws IOException {
        optionsController.processKey(keydown);
        Assertions.assertEquals(1,options.getCurrent());
    }


    @Test
    public void ArrowDownPressing2() throws IOException {
        optionsController.processKey(keydown);
        optionsController.processKey(keydown);
        optionsController.processKey(keydown);
        optionsController.processKey(keydown);
        Assertions.assertEquals(0,options.getCurrent());
    }

    
    
}

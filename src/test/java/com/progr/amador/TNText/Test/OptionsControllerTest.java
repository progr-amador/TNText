package com.progr.amador.TNText.Test;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Controller.OptionsController;
import com.progr.amador.TNText.Model.Options;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.*;

public class OptionsControllerTest {
    
    OptionsController optionsController;
    Options options;
    
    KeyStroke keyup;
    KeyStroke keydown;
    KeyStroke enter;

    @BeforeEach
    public void helper() {
        options = new Options(15, 15);
        optionsController = new OptionsController(options);
        keyup = new KeyStroke(ArrowUp);
        keydown = new KeyStroke(ArrowDown);
        enter = new KeyStroke(Enter);
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

    //ON / OFF -----------------------------------------------------------------------------------------------

    @Test
    public void PlusBombONOFF() throws IOException {
        optionsController.processKey(enter);
        Assertions.assertFalse(Application.getInstance().checkPlusBomb());
        optionsController.processKey(enter);
        Assertions.assertTrue(Application.getInstance().checkPlusBomb());
    }

    @Test
    public void PlusPowerONOFF() throws IOException {
        optionsController.processKey(keydown);
        optionsController.processKey(enter);
        Assertions.assertFalse(Application.getInstance().checkPlusPower());
        optionsController.processKey(enter);
        Assertions.assertTrue(Application.getInstance().checkPlusPower());
    }

    @Test
    public void SoundONOFF() throws IOException {
        optionsController.processKey(keyup);
        optionsController.processKey(keyup);
        optionsController.processKey(enter);
        Assertions.assertFalse(Application.getInstance().checkSound());
        optionsController.processKey(enter);
        Assertions.assertTrue(Application.getInstance().checkSound());
    }

    
    
}

package com.progr.amador.TNText.State;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Controller.Elements.MenuController;
import com.progr.amador.TNText.Model.Menu;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class MenuState extends State<Menu>{

    public MenuState(Menu menu) throws IOException {
        super(menu);
        while(true){
            draw(); // Call the private draw method within the menu class

            boolean over;
            KeyStroke key = getTerminal().getScreen().readInput();
            over = this.getController().processKey(key);
            if (over) break;
        }
    }

    public void draw() throws IOException {
        getTerminal().getScreen().clear();
        getModel().draw(getTerminal().getScreen().newTextGraphics());
        getTerminal().getScreen().refresh();
    }


    public MenuController getController() {
        return new MenuController(getModel());
    }



}

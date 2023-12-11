package com.progr.amador.TNText.State;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Controller.Elements.MenuController;
import com.progr.amador.TNText.Model.Menu;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class MenuState extends State<Menu>{

    public MenuState(Menu menu) {
        super(menu);
    }

    public void run() throws IOException {
        while(true){
            draw(); // Call the private draw method within the menu class

            KeyStroke key = getTerminal().getScreen().readInput();
            this.getController().processKey(key);
        }
    }

    public void draw() throws IOException {
        getTerminal().getScreen().clear();
        getModel().draw(getTerminal().getScreen().newTextGraphics());
        getTerminal().getScreen().refresh();
    }


    public Controller<Menu> getController() {
        return new MenuController(getModel());
    }



}

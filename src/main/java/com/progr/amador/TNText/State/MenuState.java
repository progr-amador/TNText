package com.progr.amador.TNText.State;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Controller.Elements.MenuController;
import com.progr.amador.TNText.Model.Menu;
import com.progr.amador.TNText.Viewer.MenuViewer;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class MenuState extends State<Menu>{
    private final MenuController menuController = new MenuController(getModel());
    private final MenuViewer menuViewer = new MenuViewer(getModel());

    public MenuState(Menu menu) {
        super(menu);
    }

    @Override
    protected Controller<Menu> getController() {
        return menuController;
    }

    public void draw() throws IOException {
        getTerminal().getScreen().clear();
        menuViewer.draw();
        getTerminal().getScreen().refresh();
    }

    public void run() throws IOException {
        while(true){
            draw(); // Call the private draw method within the menu class
            KeyStroke key = getTerminal().getScreen().readInput();
            menuController.processKey(key);
        }
    }


}

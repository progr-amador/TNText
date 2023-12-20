package com.progr.amador.TNText.State;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Controller.MenuController;
import com.progr.amador.TNText.Model.Menu;
import com.progr.amador.TNText.Viewer.MenuViewer;
import com.progr.amador.TNText.Viewer.Viewer;

import java.io.IOException;

import static com.progr.amador.TNText.Application.getTerminal;

public class MenuState extends State<Menu>{
    private MenuController menuController;
    private MenuViewer menuViewer;

    public MenuState(Menu menu) {
        super(menu);
    }

    @Override
    public Controller<Menu> getController() {
        if (menuController == null) menuController = new MenuController(getModel());
        return menuController;
    }

    @Override
    public Viewer<Menu> getViewer() {
        if (menuViewer == null) menuViewer = new MenuViewer(getModel());
        return menuViewer;
    }

    public void draw() throws IOException {
        getTerminal().getScreen().clear();
        getViewer().draw();
        getTerminal().getScreen().refresh();
    }

    public void run() throws IOException {
        while(true){
            draw(); // Call the private draw method within the menu class
            KeyStroke key = getTerminal().getScreen().readInput();
            getController().processKey(key);
        }
    }


}

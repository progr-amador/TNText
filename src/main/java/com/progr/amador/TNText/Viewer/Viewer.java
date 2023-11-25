package com.progr.amador.TNText.Viewer;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import com.googlecode.lanterna.screen.Screen;
import com.progr.amador.TNText.GUI.LanternaGUI;

public abstract class Viewer<T> {
    private T model;

    public Viewer(T model) {this.model = model;}
    public T getModel() {return model;}
    public void setModel(T model) {this.model = model;}

    public void draw(LanternaGUI gui) throws IOException {
        gui.clear();
        gui.refresh();
    }


}

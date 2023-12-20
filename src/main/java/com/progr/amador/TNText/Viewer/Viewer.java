package com.progr.amador.TNText.Viewer;

import com.progr.amador.TNText.TerminalGUI;

import java.io.IOException;

public abstract class Viewer<T>{
    private final T model;
    public Viewer(T model) {this.model = model;}
    public T getModel() { return model;}

    public void draw() throws IOException {}

}

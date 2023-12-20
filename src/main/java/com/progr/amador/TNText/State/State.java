package com.progr.amador.TNText.State;

import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Viewer.Viewer;

import java.io.IOException;

public abstract class State<T>{

    private final T model;
    private Controller<T> controller;
    private Viewer<T> viewer;

    public State(T model) {
        this.model = model;
    }

    public T getModel() { return model; }

    public abstract Controller<T> getController();

    public abstract Viewer<T> getViewer();


    public abstract void draw() throws IOException;

    public abstract void run() throws IOException;
}

package com.progr.amador.TNText.State;

import com.progr.amador.TNText.Controller.Controller;

import java.io.IOException;

public abstract class State<T>{

    private final T model;
    private final Controller<T> controller;

    public State(T model) {
        this.model = model;
        this.controller = getController();
    }

    protected abstract Controller<T> getController();

    public T getModel() { return model; }

    public abstract void draw() throws IOException;

    public abstract void run() throws IOException;
}

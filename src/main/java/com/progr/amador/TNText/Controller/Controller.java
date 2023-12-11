package com.progr.amador.TNText.Controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void processKey(KeyStroke key) throws IOException;

}

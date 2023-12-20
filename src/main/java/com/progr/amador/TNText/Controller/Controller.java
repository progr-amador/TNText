package com.progr.amador.TNText.Controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.progr.amador.TNText.Model.ArenaBuilder;

import java.io.IOException;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void processKey(KeyStroke key) throws IOException {}

    public void processKey(KeyStroke key, int victor, ArenaBuilder arenaBuilder) throws IOException {}

}

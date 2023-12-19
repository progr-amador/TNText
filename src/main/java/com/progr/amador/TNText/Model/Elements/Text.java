package com.progr.amador.TNText.Model.Elements;

import com.progr.amador.TNText.Viewer.Elements.TextViewer;

public class Text extends Element{

    private final TextViewer textViewer = new TextViewer(this);

    public Text(int x, int y) {
        super(x, y);
    }

    public void draw(String symbol, boolean selected) {
        textViewer.draw(symbol, selected);
    }

    @Override
    public void draw(String color, String symbol) {
        textViewer.draw(color, symbol);
    }
}

package com.progr.amador.TNText.Viewer.Elements;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.progr.amador.TNText.Model.Elements.Element;

public class TextViewer extends ElementViewer{

    public TextViewer(Element model) {
        super(model);
    }

    public void draw(String symbol, boolean selected) {
        String color_selected = "#6B93C5";
        String color_unselected = "#9C929A";
        if (selected) super.draw(color_selected, symbol);
        else super.draw(color_unselected, symbol);
    }

    public void draw(String color, String symbol) {
        super.draw(color, symbol);
    }

}

package com.progr.amador.TNText.Viewer.Elements;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.progr.amador.TNText.Model.Elements.Element;

public class TextViewer extends ElementViewer{

    public TextViewer(Element model) {
        super(model);
    }

    public void draw(TextGraphics graphics, String symbol, boolean selected) {
        String color_selected = "#6B93C5";
        String color_unselected = "#9C929A";
        if (selected) super.draw(graphics, color_selected, symbol);
        else super.draw(graphics, color_unselected, symbol);
    }

}

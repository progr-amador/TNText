package com.progr.amador.TNText.Model.Elements;

import com.googlecode.lanterna.graphics.TextGraphics;
public class Text extends Element{

    public Text(int x, int y) {
        super(x, y);
    }

    public void draw(String symbol, boolean selected) {
        String color_selected = "#6B93C5";
        String color_unselected = "#9C929A";
        if (selected) super.draw(color_selected, symbol);
        else super.draw(color_unselected, symbol);
    }

    @Override
    public void draw(String color, String symbol) {
        super.draw(color, symbol);
    }
}

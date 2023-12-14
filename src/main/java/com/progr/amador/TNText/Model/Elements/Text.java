package com.progr.amador.TNText.Model.Elements;

import com.googlecode.lanterna.graphics.TextGraphics;
public class Text extends Element{

    public Text(int x, int y) {
        super(x, y);
    }

    public void draw(TextGraphics graphics, String symbol, boolean selected) {
        String color_selected = "#6B93C5";
        String color_unselected = "#9C929A";
        if (selected) super.draw(graphics, color_selected, symbol);
        else super.draw(graphics, color_unselected, symbol);
    }

    @Override
    public void draw(TextGraphics graphics, String color, String symbol) {
        super.draw(graphics, color, symbol);
    }
}

package com.progr.amador.TNText.Model.Elements.Powerup;

import com.googlecode.lanterna.graphics.TextGraphics;

public class PlusBomb extends Powerup{
    public PlusBomb(int x, int y) {
        super(x, y);
    }

    public void draw(TextGraphics graphics, String color) {
        super.draw(graphics, color, "\u0084");
    }
}

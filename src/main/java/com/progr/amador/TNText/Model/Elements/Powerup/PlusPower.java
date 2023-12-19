package com.progr.amador.TNText.Model.Elements.Powerup;

import com.googlecode.lanterna.graphics.TextGraphics;

public class PlusPower extends Powerup{
    public PlusPower(int x, int y) {
        super(x, y);
    }

    public void draw() {
        super.draw("#9C929A", "\u0086");
    }
}

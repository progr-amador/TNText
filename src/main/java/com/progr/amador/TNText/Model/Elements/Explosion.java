package com.progr.amador.TNText.Model.Elements;

import com.googlecode.lanterna.graphics.TextGraphics;

public class Explosion extends Element{

    public Explosion(int x, int y) {
        super(x, y);
    }

    public void draw() {
        super.draw("#FFA500", "\u0085");
    }
}

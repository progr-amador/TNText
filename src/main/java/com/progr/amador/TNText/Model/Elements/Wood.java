package com.progr.amador.TNText.Model.Elements;

import com.googlecode.lanterna.graphics.TextGraphics;

public class Wood extends Element {
    private int lifes;
    public Wood(int x, int y) {super(x, y);this.lifes=1;}

    public void draw() {
        super.draw("#9C929A", "\u0090");
    }
}

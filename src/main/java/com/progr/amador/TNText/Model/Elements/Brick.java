package com.progr.amador.TNText.Model.Elements;

import com.googlecode.lanterna.graphics.TextGraphics;

public class Brick extends Element {
    public Brick(int x, int y) {super(x,y);}

    public void draw(TextGraphics graphics) {
        super.draw(graphics, "#6B93C5", "\u0080");
    }
}

package com.progr.amador.TNText;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Player extends Element{
    private int lifes;

    public Player(int x, int y) {super(x,y); this.lifes=1;}

    public void takeLife(){this.lifes--;}
    public void earnLife(){this.lifes++;}
    public int getLifes() {return lifes;}

    public void draw(TextGraphics graphics, String color, String symbol) {
        super.draw(graphics, color, symbol);
        graphics.enableModifiers(SGR.BOLD);
    }

}

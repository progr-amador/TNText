package com.progr.amador.TNText.Model.Elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.progr.amador.TNText.Model.Elements.Element;

public class Player extends Element {
    private int lifes;
    private int power = 2;

    public Player(int x, int y) {super(x,y); this.lifes=1;}

    public void takeLife(){this.lifes--;}
    public void earnLife(){this.lifes++;}
    public int getLifes() {return lifes;}

    public int getPower() { return power; }
    public void incrementPower() { power++; }

}

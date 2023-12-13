package com.progr.amador.TNText.Model.Elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.progr.amador.TNText.Model.Elements.Element;

public class Player extends Element {

    private boolean alive = true;
    private int lifes;
    private int power = 2;
    private int bag = 1;

    public Player(int x, int y) {super(x,y); this.lifes=1;}

    public void takeLife(){this.lifes--;}
    public void earnLife(){this.lifes++;}
    public int getLifes() {return lifes;}

    public int getPower() { return power; }
    public void incrementPower() { power++; }

    public boolean getStatus() {return alive;}

    public void kill() {alive = false;}

    public int getBag() {return bag;}
    public void plusBag() {bag++;}
    public void lessBag() {bag--;}

}

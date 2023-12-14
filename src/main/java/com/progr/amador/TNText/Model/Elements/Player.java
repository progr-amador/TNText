package com.progr.amador.TNText.Model.Elements;

public class Player extends Element {

    private int lifes;
    private int power = 2;
    private int bag = 1;

    public Player(int x, int y) {super(x,y); this.lifes=1;}

    public void takeLife(){this.lifes--;}
    public void earnLife(){this.lifes++;}
    public int getLifes() {return lifes;}

    public int getPower() { return power; }
    public void plusPower() { power++; }

    public int getBag() {return bag;}
    public void plusBag() {bag++;}
    public void lessBag() {bag--;}

}

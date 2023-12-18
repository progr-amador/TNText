package com.progr.amador.TNText.Model.Elements;

import java.util.concurrent.atomic.AtomicInteger;

public class Player extends Element {

    private Integer power = 2;
    private AtomicInteger bag = new AtomicInteger(1);

    public Player(int x, int y) {super(x,y);}


    public int getPower() { return power; }
    public String getPowerString() { return power.toString(); }
    public void plusPower() { power++; }

    public int getBag() {return bag.get();}
    public String getBagString() {return bag.toString();}
    public void plusBag() {bag.incrementAndGet();}
    public void lessBag() {bag.decrementAndGet();}

}

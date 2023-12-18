package com.progr.amador.TNText.Model.Elements;

import java.util.concurrent.atomic.AtomicInteger;

public class Player extends Element {

    private int power = 2;
    private AtomicInteger bag = new AtomicInteger(1);

    public Player(int x, int y) {super(x,y);}


    public int getPower() { return power; }
    public void plusPower() { power++; }

    public int getBag() {return bag.get();}
    public void plusBag() {bag.incrementAndGet();}
    public void lessBag() {bag.decrementAndGet();}

}

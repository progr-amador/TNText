package com.progr.amador.TNText.Model.Elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Bomb extends Element{
    private int radius;
    private boolean hasExploded = false;
    private Player player;

    public Bomb(Player owner) {
        super(owner.getPosition().getX(), owner.getPosition().getY());
        this.radius = owner.getPower();
        this.player = owner;
    }

    public Player getPlayer() {return player; }

    public int getRadius() {
        return radius;
    }

    public boolean hasExploded() { return hasExploded; }
    public void explode() { hasExploded = true; }
}

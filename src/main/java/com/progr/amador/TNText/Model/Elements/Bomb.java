package com.progr.amador.TNText.Model.Elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Bomb extends Element{
    private int radius, timems = 3000;
    private boolean hasExploded = false;
    private Player player;

    public Bomb(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    public Bomb(Player owner) {
        super(owner.getPosition().getX(), owner.getPosition().getY());
        this.radius = owner.getPower();
        this.player = owner;
    }

    public Player getPlayer() {return player; }

    public int getTimeMs() { return timems; }

    public int getRadius() {
        return radius;
    }

    public boolean getHasExploded() { return hasExploded; }
    public void setHasExploded() { hasExploded = true; }

    @Override
    public void draw(TextGraphics graphics, String color, String symbol) {
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(new TerminalPosition(getX(), getY()), symbol, SGR.BLINK);
    }
}

package com.progr.amador.TNText.Model.Elements;

public class Bomb extends Element{
    private int radius, time = 3;
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

    public int getTime() { return time; }

    public int getRadius() {
        return radius;
    }

    public boolean getHasExploded() { return hasExploded; }
    public void setHasExploded() { hasExploded = true; }


}

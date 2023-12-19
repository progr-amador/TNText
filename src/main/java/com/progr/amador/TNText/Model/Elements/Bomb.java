package com.progr.amador.TNText.Model.Elements;

import com.progr.amador.TNText.Viewer.Elements.BombViewer;

public class Bomb extends Element{
    private final int radius;
    private boolean hasExploded = false;
    private final Player player;

    private final BombViewer bombViewer = new BombViewer(this);

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

    public void draw() {
        bombViewer.draw();
    }
}

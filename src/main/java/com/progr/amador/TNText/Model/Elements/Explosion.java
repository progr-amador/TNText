package com.progr.amador.TNText.Model.Elements;

import com.progr.amador.TNText.Viewer.Elements.ExplosionViewer;

public class Explosion extends Element{

    private final ExplosionViewer explosionViewer = new ExplosionViewer(this);

    public Explosion(int x, int y) {
        super(x, y);
    }

    public void draw() {
        explosionViewer.draw();
    }
}

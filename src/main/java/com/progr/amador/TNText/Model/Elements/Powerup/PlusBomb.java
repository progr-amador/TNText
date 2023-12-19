package com.progr.amador.TNText.Model.Elements.Powerup;

import com.progr.amador.TNText.Viewer.Elements.PlusBombViewer;

public class PlusBomb extends Powerup{

    private PlusBombViewer plusBombViewer = new PlusBombViewer(this);

    public PlusBomb(int x, int y) {
        super(x, y);
    }

    public void draw() {
        plusBombViewer.draw();
    }
}

package com.progr.amador.TNText.Model.Elements.Powerup;

import com.progr.amador.TNText.Viewer.Elements.PlusPowerViewer;

public class PlusPower extends Powerup{

    private PlusPowerViewer plusPowerViewer = new PlusPowerViewer(this);

    public PlusPower(int x, int y) {
        super(x, y);
    }

    public void draw() {
        plusPowerViewer.draw();
    }
}

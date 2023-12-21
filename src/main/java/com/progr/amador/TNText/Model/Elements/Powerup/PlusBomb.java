package com.progr.amador.TNText.Model.Elements.Powerup;

import com.progr.amador.TNText.Model.Elements.Player;
import com.progr.amador.TNText.Viewer.Elements.PlusBombViewer;

public class PlusBomb extends Powerup{

    private final PlusBombViewer plusBombViewer = new PlusBombViewer(this);

    public PlusBomb(int x, int y) {
        super(x, y);
    }

    public void draw() {
        plusBombViewer.draw();
    }

    @Override
    public void execute(Player player) {
        player.plusBag();
    }
}

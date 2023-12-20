package com.progr.amador.TNText.Model.Elements.Powerup;

import com.progr.amador.TNText.Model.Elements.Element;
import com.progr.amador.TNText.Model.Elements.Player;

public abstract class Powerup extends Element {
    public Powerup(int x, int y) {
        super(x, y);
    }

    public abstract void draw();

    public abstract void execute(Player player);
}

package com.progr.amador.TNText.Viewer.Elements;

import com.progr.amador.TNText.Model.Elements.Element;

public class ExplosionViewer extends ElementViewer{
    public ExplosionViewer(Element model) {
        super(model);
    }

    public void draw() {
        super.draw("#FFA500", "\u0085");
    }
}

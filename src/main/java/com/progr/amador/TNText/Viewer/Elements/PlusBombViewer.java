package com.progr.amador.TNText.Viewer.Elements;

import com.progr.amador.TNText.Model.Elements.Element;

public class PlusBombViewer extends ElementViewer{
    public PlusBombViewer(Element model) {
        super(model);
    }

    public void draw() {
        super.draw("#9C929A", "\u0091");
    }

}
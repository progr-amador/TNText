package com.progr.amador.TNText.Viewer.Elements;

import com.progr.amador.TNText.Model.Elements.Element;

public class BombViewer extends ElementViewer{

    public BombViewer(Element model) {
        super(model);
    }

    public void draw() {
        super.draw("#000000", "\u008D");
    }
}

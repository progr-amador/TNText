package com.progr.amador.TNText.Viewer.Elements;

import com.progr.amador.TNText.Model.Elements.Element;

public class BrickViewer extends ElementViewer{
    public BrickViewer(Element model) {
        super(model);
    }

    public void draw() {
        super.draw("#6B93C5", "\u0080");
    };
}

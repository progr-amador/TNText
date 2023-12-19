package com.progr.amador.TNText.Model.Elements;

import com.progr.amador.TNText.Viewer.Elements.WoodViewer;

public class Wood extends Element {

    private final WoodViewer woodViewer = new WoodViewer(this);
    public Wood(int x, int y) {super(x, y);}

    public void draw() {
        woodViewer.draw();
    }
}

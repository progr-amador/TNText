package com.progr.amador.TNText.Model.Elements;

import com.progr.amador.TNText.Viewer.Elements.BrickViewer;

public class Brick extends Element {

    private final BrickViewer brickViewer = new BrickViewer(this);
    public Brick(int x, int y) {super(x,y);}

    public void draw() {
        brickViewer.draw();
    }
}

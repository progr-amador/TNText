package com.progr.amador.TNText.Model.Elements;

import com.progr.amador.TNText.Model.Position;
import com.progr.amador.TNText.Viewer.Elements.ElementViewer;

public class  Element {
    private Position position;

    private final ElementViewer elementViewer = new ElementViewer(this);

    public Element(int x, int y) {this.position = new Position(x, y);}

    public Position getPosition() {return this.position;}
    public int getX() {return getPosition().getX();}
    public int getY() {return getPosition().getY();}

    public void setPosition(Position position) {this.position = position;}

    public void draw(String color, String symbol) {
        elementViewer.draw(color, symbol);
    }


}

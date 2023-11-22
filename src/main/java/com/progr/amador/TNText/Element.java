package com.progr.amador.TNText;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class  Element {
    private Position position;

    public Element(int x, int y) {this.position = new Position(x, y);}

    public Position getPosition() {return this.position;}
    public int getX() {return getPosition().getX();}

    public int getY() {return getPosition().getY();}
    public void setPosition(Position position) {this.position = position;}

    public void draw(TextGraphics graphics, String color, String symbol) {
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), symbol);
    }
}

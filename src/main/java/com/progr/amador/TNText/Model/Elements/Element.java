package com.progr.amador.TNText.Model.Elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.progr.amador.TNText.Model.Position;

import static com.progr.amador.TNText.Application.getTerminal;

public class  Element {
    private Position position;

    public Element(int x, int y) {this.position = new Position(x, y);}

    public Position getPosition() {return this.position;}
    public int getX() {return getPosition().getX();}
    public int getY() {return getPosition().getY();}

    public void setPosition(Position position) {this.position = position;}

    public void draw(String color, String symbol) {
        getTerminal().getGraphics().setForegroundColor(TextColor.Factory.fromString(color));
        getTerminal().getGraphics().putString(new TerminalPosition(position.getX(), position.getY()), symbol);
    }


}

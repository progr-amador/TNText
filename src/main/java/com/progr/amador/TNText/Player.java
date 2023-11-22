package com.progr.amador.TNText;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Player extends Element{
    private int lifes;

    public Player(int x, int y) {super(x,y); this.lifes=1;}

    public void takeLife(){this.lifes--;}
    public void earnLife(){this.lifes++;}
    public int getLifes() {return lifes;}

    public Position moveUp() {return new Position(getX(), getY()-1);}
    public Position moveDown() {return new Position(getX(), getY()+1);}
    public Position moveRight() {return new Position(getX()+1, getY());}
    public Position moveLeft() {return new Position(getX()-1, getY());}

    public void draw(TextGraphics graphics, String color, String symbol) {
        super.draw(graphics, color, symbol);
        graphics.enableModifiers(SGR.BOLD);
    }

}

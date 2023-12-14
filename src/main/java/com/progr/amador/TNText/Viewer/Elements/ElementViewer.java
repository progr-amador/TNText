package com.progr.amador.TNText.Viewer.Elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.progr.amador.TNText.Model.Elements.Element;
import com.progr.amador.TNText.Viewer.Viewer;

public class ElementViewer extends Viewer<Element> {
    public ElementViewer(Element model) {
        super(model);
    }
    public void draw(TextGraphics graphics, String color, String symbol) {
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(new TerminalPosition(getModel().getX(), getModel().getY()), symbol);
    }
}

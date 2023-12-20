package com.progr.amador.TNText.Viewer.Elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.progr.amador.TNText.Model.Elements.Element;
import com.progr.amador.TNText.Viewer.Viewer;

import static com.progr.amador.TNText.Application.getTerminal;

public class ElementViewer extends Viewer<Element> {
    public ElementViewer(Element model) {
        super(model);
    }
    public void draw(String color, String symbol) {
        getTerminal().getGraphics().setForegroundColor(TextColor.Factory.fromString(color));
        getTerminal().getGraphics().putString(new TerminalPosition(getModel().getX(), getModel().getY()), symbol);
    }
}

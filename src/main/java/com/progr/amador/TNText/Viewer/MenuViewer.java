package com.progr.amador.TNText.Viewer;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.progr.amador.TNText.Model.Elements.Element;
import com.progr.amador.TNText.Model.Elements.Text;
import com.progr.amador.TNText.Model.Menu;

public class MenuViewer extends Viewer<Menu>{
    public MenuViewer(Menu model) {
        super(model);
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(getModel().getWidth(), getModel().getHeight()+2), ' ');

        new Text(0, 5).draw(graphics, "    TNTEXT   ", false);
        new Text(0, 7).draw(graphics, "     PLAY    ", getModel().getCurrent() == 0);
        new Text(0, 8).draw(graphics, "   SETTINGS   ", getModel().getCurrent() == 1);
        new Text(0, 9).draw(graphics, "     EXIT    " , getModel().getCurrent() == 2);
        }
    }

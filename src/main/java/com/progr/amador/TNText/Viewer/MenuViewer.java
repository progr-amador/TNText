package com.progr.amador.TNText.Viewer;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.progr.amador.TNText.Model.Elements.Text;
import com.progr.amador.TNText.Model.Menu;

import static com.progr.amador.TNText.Application.getTerminal;

public class MenuViewer extends Viewer<Menu>{
    public MenuViewer(Menu model) {
        super(model);
    }

    public void draw() {
        getTerminal().getGraphics().setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        getTerminal().getGraphics().fillRectangle(new TerminalPosition(0,0), new TerminalSize(getModel().getWidth(), getModel().getHeight()+2), ' ');

        new Text(0, 5).draw("    TNTEXT   ", false);
        new Text(0, 7).draw("     PLAY    ", getModel().getCurrent() == 0);
        new Text(0, 8).draw("   SETTINGS   ", getModel().getCurrent() == 1);
        new Text(0, 9).draw("     EXIT    " , getModel().getCurrent() == 2);
        }
    }

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
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(getModel().getWidth(), getModel().getHeight()), ' ');

        int nsei = 1;

        if(nsei == 1){
            //TODO: SEM CONTROLS
            new Text(0, 1).draw(graphics, "    TNTEXT   ", false);
            new Text(0, 3).draw(graphics, "     GAME    ", getModel().getCurrent() == 0);
            new Text(0, 4).draw(graphics, "   SETTINGS   ", getModel().getCurrent() == 1);
            new Text(0, 5).draw(graphics, "     EXIT    " , getModel().getCurrent() == 2);
        }
        else if (nsei == 2){
            //TODO: COM UMA LINHA SEPARADA
            new Text(0, 1).draw(graphics, "    TNTEXT   ", false);

        new Text(0, 2).draw(graphics, "     GAME    ", getModel().getCurrent() == 0);

        new Text(0, 3).draw(graphics, "   SETTINGS   ", getModel().getCurrent() == 1);

        new Text(0, 4).draw(graphics, "     EXIT    " , getModel().getCurrent() == 2);
        }
        else if (nsei == 3){
            //TODO: ORIGINAL
            new Text(0, 1).draw(graphics, "    TNTEXT   ", false);

            new Text(0, 2).draw(graphics, "     GAME    ", getModel().getCurrent() == 0);

            new Text(0, 3).draw(graphics, "   SETTINGS   ", getModel().getCurrent() == 1);

            new Text(0, 4).draw(graphics, "     EXIT    " , getModel().getCurrent() == 2);

            new Text(0, 6).draw(graphics, " \u0081 CONTROLS \u0082", true);


            new Element(1, 9).draw(graphics, "#6B93C5", "W   goUP   \u008A");
            new Element(1, 10).draw(graphics, "#6B93C5", "S   goDO   \u008B");
            new Element(1, 11).draw(graphics, "#6B93C5", "A   goLF   \u0088");
            new Element(1, 12).draw(graphics, "#6B93C5", "D   goRG   \u0089");
            new Element(1, 13).draw(graphics, "#6B93C5", "\u0087   bomb   \u008C");
        }
    }
}

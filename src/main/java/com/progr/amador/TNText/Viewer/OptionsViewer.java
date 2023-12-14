package com.progr.amador.TNText.Viewer;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Model.Elements.Element;
import com.progr.amador.TNText.Model.Elements.Text;
import com.progr.amador.TNText.Model.Options;

public class OptionsViewer extends Viewer<Options> {
    public OptionsViewer(Options model) {
        super(model);
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(getModel().getWidth(), getModel().getHeight()), ' ');

        new Text(1, 1).draw(graphics, "PlusBomb", getModel().getCurrent() == 0);
        new Text(1, 2).draw(graphics, "PlusPower", getModel().getCurrent() == 1);
        new Text(1, 3).draw(graphics, "Menu", getModel().getCurrent() == 2);

        if(Application.getInstance().checkPlusBomb()) new Text(11, 1).draw(graphics, "ON", getModel().getCurrent() == 0);
        else new Text(11, 1).draw(graphics, "OFF", getModel().getCurrent() == 0);

        if(Application.getInstance().checkPlusPower()) new Text(11, 2).draw(graphics, "ON", getModel().getCurrent() == 1);
        else new Text(11, 2).draw(graphics, "OFF", getModel().getCurrent() == 1);

        int nsei = 3    ;


        if(nsei == 1){
            //TODO:CONTROLS NAS SETTINGS
            new Text(1, 6).draw(graphics, "\u0081 CONTROLS \u0082", true);

            new Element(1, 9).draw(graphics, "#6B93C5", "W   goUP   \u008A");
            new Element(1, 10).draw(graphics, "#6B93C5", "S   goDO   \u008B");
            new Element(1, 11).draw(graphics, "#6B93C5", "A   goLF   \u0088");
            new Element(1, 12).draw(graphics, "#6B93C5", "D   goRG   \u0089");
            new Element(1, 13).draw(graphics, "#6B93C5", "\u0087   bomb   \u008C");
        }
        else if (nsei == 2){
            //TODO:PLAYERS COM CORES

            new Text(1, 6).draw(graphics, "  CONTROLS  ", true);

            new Element(1, 6).draw(graphics, "#FFFFFF", "\u0081");
            new Element(12, 6).draw(graphics, "#F27379", "\u0082");

            new Element(1, 9).draw(graphics, "#6B93C5", "W   goUP   \u008A");
            new Element(1, 10).draw(graphics, "#6B93C5", "S   goDO   \u008B");
            new Element(1, 11).draw(graphics, "#6B93C5", "A   goLF   \u0088");
            new Element(1, 12).draw(graphics, "#6B93C5", "D   goRG   \u0089");
            new Element(1, 13).draw(graphics, "#6B93C5", "\u0087   bomb   \u008C");
        }
        else if (nsei == 3){
            //TODO:CORES TODAS DIFERENTES

            new Text(3, 6).draw(graphics, "CONTROLS", false);

            new Text(1, 6).draw(graphics, "#FFFFFF", "\u0081");
            new Text(12, 6).draw(graphics, "#F27379", "\u0082");

            new Text(5,  8).draw(graphics, "goUP", false);
            new Text(5,  9).draw(graphics, "goDO", false);
            new Text(5, 10).draw(graphics, "goLF", false);
            new Text(5, 11).draw(graphics, "goRG", false);
            new Text(5, 12).draw(graphics, "bomb", false);

            new Text(1,  8).draw(graphics, "#FFFFFF", "W");
            new Text(1,  9).draw(graphics, "#FFFFFF", "S");
            new Text(1, 10).draw(graphics, "#FFFFFF", "A");
            new Text(1, 11).draw(graphics, "#FFFFFF", "D");
            new Text(1, 12).draw(graphics, "#FFFFFF", "\u0087");

            new Text(12,  8).draw(graphics, "#F27379", "\u008A");
            new Text(12,  9).draw(graphics, "#F27379", "\u008B");
            new Text(12, 10).draw(graphics, "#F27379", "\u0088");
            new Text(12, 11).draw(graphics, "#F27379", "\u0089");
            new Text(12, 12).draw(graphics, "#F27379", "\u008C");
        }
    }
}

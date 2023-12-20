package com.progr.amador.TNText.Viewer;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Model.Elements.Text;
import com.progr.amador.TNText.Model.Options;

import static com.progr.amador.TNText.Application.getTerminal;

public class OptionsViewer extends Viewer<Options> {
    public OptionsViewer(Options model) {
        super(model);
    }

    public void draw() {
        getTerminal().getGraphics().setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        getTerminal().getGraphics().fillRectangle(new TerminalPosition(0,0), new TerminalSize(getModel().getWidth(), getModel().getHeight()+2), ' ');

        new Text(1, 1).draw("PlusBomb", getModel().getCurrent() == 0);
        new Text(1, 2).draw("PlusPower", getModel().getCurrent() == 1);
        new Text(1, 3).draw("Sound", getModel().getCurrent() == 2);
        new Text(1, 4).draw("Menu", getModel().getCurrent() == 3);

        if(Application.getInstance().checkPlusBomb()) new Text(11, 1).draw("ON", getModel().getCurrent() == 0);
        else new Text(11, 1).draw("OFF", getModel().getCurrent() == 0);

        if(Application.getInstance().checkPlusPower()) new Text(11, 2).draw("ON", getModel().getCurrent() == 1);
        else new Text(11, 2).draw("OFF", getModel().getCurrent() == 1);

        if(Application.getInstance().checkSound()) new Text(11, 3).draw("ON", getModel().getCurrent() == 2);
        else new Text(11, 3).draw("OFF", getModel().getCurrent() == 2);

        new Text(3, 6).draw("CONTROLS", false);

        new Text(1, 6).draw("#FFFFFF", "\u0081");
        new Text(12, 6).draw("#F27379", "\u0082");

        new Text(5,  8).draw("goUP", false);
        new Text(5,  9).draw("goDO", false);
        new Text(5, 10).draw("goLF", false);
        new Text(5, 11).draw("goRG", false);
        new Text(5, 12).draw("bomb", false);

        new Text(1,  8).draw("#FFFFFF", "W");
        new Text(1,  9).draw("#FFFFFF", "S");
        new Text(1, 10).draw("#FFFFFF", "A");
        new Text(1, 11).draw("#FFFFFF", "D");
        new Text(1, 12).draw("#FFFFFF", "\u0087");

        new Text(12,  8).draw("#F27379", "\u008A");
        new Text(12,  9).draw("#F27379", "\u008B");
        new Text(12, 10).draw("#F27379", "\u0088");
        new Text(12, 11).draw("#F27379", "\u0089");
        new Text(12, 12).draw("#F27379", "\u008C");

        new Text(1, 14).draw("EXIT      ESC", false);
        new Text(1, 15).draw("MENU       M", false);

    }
    }

package com.progr.amador.TNText.Viewer;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.progr.amador.TNText.Controller.Elements.GameController;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.Elements.*;
import com.progr.amador.TNText.Model.Elements.Powerup.Powerup;
import com.progr.amador.TNText.Model.Position;

import java.util.ArrayList;
import java.util.List;

import static com.progr.amador.TNText.Application.getTerminal;

public class ArenaViewer extends GameViewer {
    public ArenaViewer(Arena arena) {
        super(arena);
    }

    public void draw() {
        getTerminal().getGraphics().setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        getTerminal().getGraphics().fillRectangle(new TerminalPosition(0,0), new TerminalSize(getModel().getWidth(), getModel().getHeight()+2), ' ');

        for (Powerup powerup : getModel().getPowerups()) if (powerup != null) powerup.draw();

        for (Brick brick : getModel().getBricks()) brick.draw();
        for (Wood wood : getModel().getWoods()) if (wood != null) wood.draw();
        for (Bomb bomb : getModel().getBombs()) if (bomb != null) bomb.draw();
        for (Explosion explosion : getModel().getExplosions()) if (explosion != null) explosion.draw();

        switch (getModel().getVictor()){
            case -1: {
                getModel().getPlayer1().draw("#FFFFFF", "\u0081");
                getModel().getPlayer2().draw("#F27379", "\u0082");
                break;
            }
            case 0: {
                for(int i = 1; i <= 13; i++)
                    new Text(1, i).draw("             ", false);
                new Text(1, 7).draw("IT'S  A  DRAW", false);
                getModel().getPlayer1().setPosition(new Position(7, 7));
                getModel().getPlayer1().draw("#FFFFFF", "\u0081");
                getModel().getPlayer2().setPosition(new Position(12, 7));
                getModel().getPlayer2().draw("#F27379", "\u0082");
                break;
            }
            case 1: {
                for(int i = 1; i <= 13; i++)
                    new Text(1, i).draw("             ", false);
                new Text(1, 7).draw("PLAYER 1 WON!", false);
                getModel().getPlayer1().setPosition(new Position(3, 7));
                getModel().getPlayer1().draw("#FFFFFF", "\u0081");
                break;
            }
            case 2: {
                for(int i = 1; i <= 13; i++)
                    new Text(1, i).draw("             ", false);
                new Text(1, 7).draw("PLAYER 2 WON!", false);
                getModel().getPlayer2().setPosition(new Position(3, 7));
                getModel().getPlayer2().draw("#F27379", "\u0082");
            }
        }

        new Text(5, 15).draw("BOMBS", false);
        new Text(5, 16).draw("POWER", false);

        new Text(1, 15).draw("#FFFFFF", getModel().getPlayer1().getBagString());
        new Text(1, 16).draw("#FFFFFF", getModel().getPlayer1().getPowerString());

        new Text(13, 15).draw("#F27379", getModel().getPlayer2().getBagString());
        new Text(13, 16).draw("#F27379", getModel().getPlayer2().getPowerString());

    }
}

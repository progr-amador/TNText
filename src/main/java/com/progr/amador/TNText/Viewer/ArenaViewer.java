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

public class ArenaViewer extends GameController {

    //private final


    public ArenaViewer(Arena arena) {
        super(arena);
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(getModel().getWidth(), getModel().getHeight()), ' ');

        List<Powerup> powerups_copy = new ArrayList<>(getModel().getPowerups());
        for (Powerup powerup : powerups_copy) if (powerup != null) powerup.draw(graphics, "#9C929A");

        for (Brick brick : getModel().getBricks()) brick.draw(graphics, "#6B93C5", "\u0080");
        List<Wood> woods_copy = new ArrayList<>(getModel().getWoods());
        for (Wood wood : woods_copy) if (wood != null) wood.draw(graphics, "#9C929A", "\u0090");
        List<Bomb> bombs_copy = new ArrayList<>(getModel().getBombs());
        for (Bomb bomb : bombs_copy) if (bomb != null) bomb.draw(graphics, "#000000", "\u008D");
        List<Explosion> explosions_copy = new ArrayList<>(getModel().getExplosions());
        for (Explosion explosion : explosions_copy) if (explosion != null) explosion.draw(graphics, "#FFA500", "\u0085");

        if (getModel().getVictor() == -1) {
            getModel().getPlayer1().draw(graphics, "#FFFFFF", "\u0081");
            getModel().getPlayer2().draw(graphics, "#F27379", "\u0082");
        } else if (getModel().getVictor() == 0) {
            for(int i = 1; i <= 13; i++)
                new Text(1, i).draw(graphics, "             ", false);
            new Text(1, 7).draw(graphics, "IT'S  A  DRAW", false);
            getModel().getPlayer1().setPosition(new Position(7, 7));
            getModel().getPlayer1().draw(graphics, "#FFFFFF", "\u0081");
            getModel().getPlayer2().setPosition(new Position(12, 7));
            getModel().getPlayer2().draw(graphics, "#F27379", "\u0082");
        } else if (getModel().getVictor() == 1) {
            for(int i = 1; i <= 13; i++)
                new Text(1, i).draw(graphics, "             ", false);
            new Text(1, 7).draw(graphics, "PLAYER 1 WON!", false);
            getModel().getPlayer1().setPosition(new Position(3, 7));
            getModel().getPlayer1().draw(graphics, "#FFFFFF", "\u0081");
        } else if (getModel().getVictor() == 2) {
            for(int i = 1; i <= 13; i++)
                new Text(1, i).draw(graphics, "             ", false);
            new Text(1, 7).draw(graphics, "PLAYER 2 WON!", false);
            getModel().getPlayer2().setPosition(new Position(3, 7));
            getModel().getPlayer2().draw(graphics, "#F27379", "\u0082");
        }
    }
}

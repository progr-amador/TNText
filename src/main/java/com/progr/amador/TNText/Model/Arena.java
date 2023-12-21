package com.progr.amador.TNText.Model;

import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Model.Elements.*;
import com.progr.amador.TNText.Model.Elements.Powerup.Powerup;
import com.progr.amador.TNText.Sound;

import java.util.concurrent.CopyOnWriteArrayList;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private final int width;
    private final int height;
    private int victor = -1;
    private final Player player1;
    private final Player player2;
    private final List<Brick> bricks = new ArrayList<>();
    private final CopyOnWriteArrayList<Wood> woods = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Bomb> bombs = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Explosion> explosions = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Powerup> powerups = new CopyOnWriteArrayList<>();

    Sound sound = new Sound();

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.player1 = new Player(1, 1);
        this.player2 = new Player(width-2, height-2);
    }

    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public List<Brick> getBricks() { return bricks; }
    public CopyOnWriteArrayList<Wood> getWoods() { return woods; }
    public CopyOnWriteArrayList<Bomb> getBombs() {return bombs;}
    public CopyOnWriteArrayList<Explosion> getExplosions() {return explosions;}
    public CopyOnWriteArrayList<Powerup> getPowerups() {
        return powerups;
    }
    public Player getPlayer1() { return player1; }
    public Player getPlayer2() {
        return player2;
    }
    public int getVictor() { return victor; }

    public Sound getSound() {return sound;}

    public void whoWon() {
        boolean player1_dead = false, player2_dead = false;
        for (Explosion explosion : explosions) {
            if (explosion != null) {
                if (explosion.getPosition().equals(player1.getPosition())) player1_dead = true;
                if (explosion.getPosition().equals(player2.getPosition())) player2_dead = true;
                if (player1_dead && player2_dead) break;
            }
        }
        if (player1_dead && player2_dead) victor = 0;
        else if (player1_dead) victor = 2;
        else if (player2_dead) victor = 1;
    }

    public void playMusic() {
        sound.setFile(1);
        if( Application.getInstance().checkSound()) sound.play();
        sound.loop();
    }

}
package com.progr.amador.TNText.Model;

import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Model.Elements.*;
import com.progr.amador.TNText.Model.Elements.Powerup.PlusBomb;
import com.progr.amador.TNText.Model.Elements.Powerup.PlusPower;
import com.progr.amador.TNText.Model.Elements.Powerup.Powerup;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    private final int width;
    private final int height;
    private int victor = -1;
    private final Player player1;
    private final Player player2;
    private final List<Brick> bricks = new ArrayList<>();
    private CopyOnWriteArrayList<Wood> woods = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Bomb> bombs = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Explosion> explosions = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Powerup> powerups = new CopyOnWriteArrayList<>();


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
    public void setWoods(CopyOnWriteArrayList<Wood> woods) { this.woods = woods; }
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

    public void whoWon() {  // devia ser passado para o game controller talvez
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

}
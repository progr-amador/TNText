package com.progr.amador.TNText.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.progr.amador.TNText.Model.Elements.*;
import com.progr.amador.TNText.Model.Elements.Powerup.PlusBomb;
import com.progr.amador.TNText.Model.Elements.Powerup.PlusPower;
import com.progr.amador.TNText.Model.Elements.Powerup.Powerup;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Collections.addAll;

public class Arena {

    private int width, height, victor = -1;
    private Player player1;
    private Player player2;
    private final List<Brick> bricks;
    private List<Wood> woods = new ArrayList<>();
    private List<Bomb> bombs = new ArrayList<>();
    private List<Explosion> explosions = new ArrayList<>();
    private List<Powerup> powerups = new ArrayList<>();


    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.bricks = createBricks();
        this.woods = createWoods();
        this.player1 = new Player(1, 1);
        this.player2 = new Player(width-2, height-2);
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public List<Wood> getWoods() {
        return woods;
    }

    public List<Powerup> getPowerups() {
        return powerups;
    }

    public void setPowerups(List<Powerup> powerups) {
        this.powerups = powerups;
    }

    public Player getPlayer1() { return player1; }

    public Player getPlayer2() {
        return player2;
    }

    public int getVictor() { return victor; }

    public void addBomb(Bomb bomb) {
        if(bomb.getPlayer().getBag() == 0) return;
        bombs.add(bomb);
        bomb.getPlayer().lessBag();
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(3000); // Wait for additional 1.5 seconds
                if(!bomb.getHasExploded()) explosionPlanner(bomb);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void explosionPlanner(Bomb bomb) {
        List<Explosion> blastzone = new ArrayList<>();
        bomb.setHasExploded();
        blastzone.add(new Explosion(bomb.getX(), bomb.getY()));
        boolean e_right = true, e_left = true, e_up = true, e_down = true;

        for(int i = 1; i <= bomb.getRadius(); i++){
            if(e_right) e_right = expandDirection(new Position(bomb.getX() + i, bomb.getY()), blastzone);
            if(e_left) e_left = expandDirection(new Position(bomb.getX() - i, bomb.getY()), blastzone);
            if(e_down) e_down = expandDirection(new Position(bomb.getX(), bomb.getY() + i), blastzone);
            if(e_up) e_up = expandDirection(new Position(bomb.getX(), bomb.getY() - i), blastzone);
        }
        explosions.addAll(blastzone);
        bombs.remove(bomb);

        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1500); // Wait for additional 1.5 seconds
                explosions.removeAll(blastzone);
                bomb.getPlayer().plusBag();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean expandDirection(Position direction, List<Explosion> blastzone){
        Element can_it_move = canElementMove(direction);
        Explosion explode = new Explosion(direction.getX(), direction.getY());

        if(can_it_move == null){
            blastzone.add(explode);
            return true;
        }
        else if (can_it_move instanceof Wood){
            blastzone.add(explode);
            woods.remove(can_it_move);
        }
        else if (can_it_move instanceof Bomb close_bomb){
            if(!close_bomb.getHasExploded()) explosionPlanner(close_bomb);
        }

        return false;
    }

    private Element canElementMove(Position position) {  // devia ser passado para o game controller talvez
        for (Brick brick : bricks) {
            if (brick.getPosition().equals(position)) return brick;
        }
        for (Wood wood : woods) {
            if (wood.getPosition().equals(position)) return wood;
        }
        for (Bomb bomb : bombs) {
            if (bomb.getPosition().equals(position)) return bomb;
        }
        return null;
    }

    private List<Brick> createBricks() {
        List<Brick> bricks = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            bricks.add(new Brick(c, 0));
            bricks.add(new Brick(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            bricks.add(new Brick(0, r));
            bricks.add(new Brick(width - 1, r));
        }
        for(int x = 2; x < width - 1; x=x+2){
            for(int y = 2; y < height - 1; y=y+2){
                bricks.add(new Brick(x, y));
            }
        }
        return bricks;
    }

    private List<Wood> createWoods() {
        for (int x = 1; x < width-1; x++) {
            for (int y = 1; y < height-1; y++) {
                if ((x % 2 != 0 || y % 2 != 0) &
                    !(x == 1 & y == 1) & !(x == 1 & y == 2) & !(x == 2 & y == 1) &
                    !(x == width - 2 & y == height - 2) & !(x == width - 2 & y == height - 3) & !(x == width - 3 & y == height - 2)) {
                    shouldAddWood(x, y);
                }
            }
        }
        return woods;
    }

    private void shouldAddWood(int x, int y) {
        if(new Random().nextDouble() < 0.3) { // Adjust this value (0.0 to 1.0) for your desired spawn rate
            woods.add(new Wood(x, y));
            shouldAddUpgrade(x, y);
        }
    }

    private void shouldAddUpgrade(int x, int y) {
        if(new Random().nextDouble() < 0.27) whichUpgrade(x, y);
    }

    private void whichUpgrade(int x, int y) {
        if(new Random().nextDouble() < 0.5) powerups.add(new PlusBomb(x, y));
        else powerups.add(new PlusPower(x, y));
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        List<Powerup> powerups_copy = new ArrayList<>(powerups);
        for (Powerup powerup : powerups_copy) if (powerup != null) powerup.draw(graphics, "#9C929A");

        for (Brick brick : bricks) brick.draw(graphics, "#6B93C5", "\u0080");
        List<Wood> woods_copy = new ArrayList<>(woods);
        for (Wood wood : woods_copy) if (wood != null) wood.draw(graphics, "#9C929A", "\u0090");
        List<Bomb> bombs_copy = new ArrayList<>(bombs);
        for (Bomb bomb : bombs_copy) if (bomb != null) bomb.draw(graphics, "#000000", "\u008D");
        List<Explosion> explosions_copy = new ArrayList<>(explosions);
        for (Explosion explosion : explosions_copy) if (explosion != null) explosion.draw(graphics, "#FFA500", "\u0085");

        if (victor == -1) {
            player1.draw(graphics, "#FFFFFF", "\u0081");
            player2.draw(graphics, "#F27379", "\u0082");
        } else if (victor == 0) {
            for(int i = 1; i <= 13; i++)
                new Text(1, i).draw(graphics, "             ", false);
            new Text(1, 7).draw(graphics, "IT'S  A  DRAW", false);
            player1.setPosition(new Position(7, 7));
            player1.draw(graphics, "#FFFFFF", "\u0081");
            player2.setPosition(new Position(12, 7));
            player2.draw(graphics, "#F27379", "\u0082");
        } else if (victor == 1) {
            for(int i = 1; i <= 13; i++)
                new Text(1, i).draw(graphics, "             ", false);
            new Text(1, 7).draw(graphics, "PLAYER 1 WON!", false);
            player1.setPosition(new Position(3, 7));
            player1.draw(graphics, "#FFFFFF", "\u0081");
        } else if (victor == 2) {
            for(int i = 1; i <= 13; i++)
                new Text(1, i).draw(graphics, "             ", false);
            new Text(1, 7).draw(graphics, "PLAYER 2 WON!", false);
            player2.setPosition(new Position(3, 7));
            player2.draw(graphics, "#F27379", "\u0082");
        }
    }

    public void whoWon() {  // devia ser passado para o game controller talvez
        List<Explosion> explosions_copy = new ArrayList<>(explosions);
        Position player1_pos = player1.getPosition();
        Position player2_pos = player2.getPosition();
        boolean player1_dead = false, player2_dead = false;
        for (Explosion explosion : explosions_copy) {
            if (explosion != null) {
                if (explosion.getPosition().equals(player1_pos)) player1_dead = true;
                if (explosion.getPosition().equals(player2_pos)) player2_dead = true;
                if (player1_dead && player2_dead) break;
            }
        }
        if (player1_dead && player2_dead) victor = 0;
        else if (player1_dead) { player1.kill(); victor = 2; }
        else if (player2_dead) { player2.kill(); victor = 1; }
    }

}
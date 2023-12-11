package com.progr.amador.TNText.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.progr.amador.TNText.Model.Elements.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Collections.addAll;

public class Arena {

    private int width, height;
    private Player player1 = new Player(1, 1);
    private Player player2 = new Player(13, 13);
    private List<Brick> bricks;
    private List<Wood> woods;
    private List<Bomb> bombs;
    private List<Explosion> explosions;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.bricks = createBricks();
        this.woods = createWoods();
        this.explosions = new ArrayList<>();
        this.bombs = new ArrayList<>();
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public List<Wood> getWoods() {
        return woods;
    }

    public Player getPlayer1() { return player1; }

    public Player getPlayer2() {
        return player2;
    }

    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
        //explosionPlanner(bomb);
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(3000); // Wait for additional 1.5 seconds
                if(!bomb.getHasExploded()) explosionPlanner(bomb);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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
        else if (can_it_move.getClass() == Wood.class){
            blastzone.add(explode);
            woods.remove(can_it_move);
        }
        else if (can_it_move.getClass() == Bomb.class){
            Bomb close_bomb = (Bomb) can_it_move;
            if(!close_bomb.getHasExploded()) explosionPlanner(close_bomb);
        }

        return false;
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
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
        List<Wood> woods = new ArrayList<>();
        for (int x = 1; x < width-1; x++) {
            for (int y = 1; y < height-1; y++) {
                if ((x % 2 != 0 || y % 2 != 0) &
                    !(x == 1 & y == 1) & !(x == 1 & y == 2) & !(x == 2 & y == 1) &
                    !(x == width - 2 & y == height - 2) & !(x == width - 2 & y == height - 3) & !(x == width - 3 & y == height - 2)) {
                    if (shouldAddWood()) woods.add(new Wood(x, y));
                }
            }
        }
        return woods;
    }

    private boolean shouldAddWood() {
        Random random = new Random();
        // Adjust the spawn rate by modifying the probability
        double spawnRate = 0.1; // Adjust this value (0.0 to 1.0) for your desired spawn rate
        return random.nextDouble() < spawnRate;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        for (Brick brick : bricks) brick.draw(graphics, "#6B93C5", "\u0080");
        List<Wood> woods_copy = new ArrayList<>(woods);
        for (Wood wood : woods_copy) if(wood != null) wood.draw(graphics, "#9C929A", "#");
        List<Bomb> bombs_copy = new ArrayList<>(bombs);
        for (Bomb bomb : bombs_copy) if(bomb != null) bomb.draw(graphics, "#000000", "\u0083");
        List<Explosion> explosions_copy = new ArrayList<>(explosions);
        for (Explosion explosion : explosions_copy) if(explosion != null) explosion.draw(graphics, "#FFA500", "\u0085");

        if (whoWon() == -1){
            player1.draw(graphics, "#FFFFFF", "\u0081");
            player2.draw(graphics, "#F27379", "\u0082");
        }
        else if (whoWon() == 1){
            new Text(1, 6).draw(graphics, "             ", false);
            new Text(1, 7).draw(graphics, "PLAYER 1 WON!", false);
            new Text(1, 8).draw(graphics, "             ", false);
            player1.setPosition(new Position(3, 7));
            player1.draw(graphics, "#FFFFFF", "\u0081");

        }
        else if (whoWon() == 2) {
            new Text(1, 6).draw(graphics, "             ", false);
            new Text(1, 7).draw(graphics, "PLAYER 2 WON!", false);
            new Text(1, 8).draw(graphics, "             ", false);
            player2.setPosition(new Position(3, 7));
            player2.draw(graphics, "#F27379", "\u0082");
        }
        else if (whoWon() == 0) {
            new Text(1, 6).draw(graphics, "             ", false);
            new Text(1, 7).draw(graphics, "IT'S  A  DRAW", false);
            new Text(1, 8).draw(graphics, "             ", false);
        }

        /*if(player1.getStatus()) player1.draw(graphics, "#FFFFFF", "\u0081");
        else{
            new Text(1, 6).draw(graphics, "             ", false);
            new Text(1, 7).draw(graphics, "PLAYER 2 WON!", false);
            new Text(1, 8).draw(graphics, "             ", false);
            player2.setPosition(new Position(3, 7));
            player2.draw(graphics, "#F27379", "\u0082");
        }
        if(player2.getStatus()) player2.draw(graphics, "#F27379", "\u0082");
        else {
            new Text(1, 1).draw(graphics, "             ", false);
            new Text(1, 2).draw(graphics, "             ", false);
            new Text(1, 3).draw(graphics, "             ", false);
            new Text(1, 4).draw(graphics, "             ", false);
            new Text(1, 5).draw(graphics, "#############", false);
            new Text(1, 6).draw(graphics, "             ", false);
            new Text(1, 7).draw(graphics, "PLAYER 1 WON!", false);
            new Text(1, 8).draw(graphics, "             ", false);
            new Text(1, 9).draw(graphics, "#############", false);
            new Text(1, 10).draw(graphics, "             ", false);
            new Text(1, 11).draw(graphics, "             ", false);
            new Text(1, 12).draw(graphics, "             ", false);
            new Text(1, 13).draw(graphics, "             ", false);

            new Text(1, 6).draw(graphics, "             ", false);
            new Text(1, 7).draw(graphics, "PLAYER 1 WON!", false);
            new Text(1, 8).draw(graphics, "             ", false);

            player1.setPosition(new Position(3, 7));
            player1.draw(graphics, "#FFFFFF", "\u0081");
        }*/
    }

    public int whoWon() {  // devia ser passado para o game controller talvez
        List<Explosion> explosions_copy = new ArrayList<>(explosions);
        boolean player1_dead = false, player2_dead = false;
        for (Explosion explosion : explosions_copy) {
            if (explosion != null && explosion.getPosition().equals(player1.getPosition())) {
                player1_dead = true;
            }
            if (explosion != null && explosion.getPosition().equals(player2.getPosition())) {
                player2_dead = true;
            }
            if(player1_dead && player2_dead) return 0;
            else if (player1_dead){
                player1.kill();
                return 2;
            }
            else if (player2_dead){
                player2.kill();
                return 1;
            }

        }
        return -1;
    }

}
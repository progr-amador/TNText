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
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
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
        Explosion center = new Explosion(bomb.getX(), bomb.getY());
        explosions.add(center); //BOMB CENTER
        blastzone.add(center);
        boolean e_right = true, e_left = true, e_up = true, e_down = true;

        for(int i = 1; i <= bomb.getRadius(); i++){
            if(!(e_right || e_left || e_down || e_up)) break;

            if(e_right) { //EXPAND RIGHT
                Position right = new Position(bomb.getX() + i, bomb.getY());
                Element can_right = canElementMove(right);
                Explosion explode_right = new Explosion(right.getX(), right.getY());
                if (can_right != null) {
                    if (can_right.getClass() == Wood.class) {
                        explosions.add(explode_right);
                        blastzone.add(explode_right);
                        woods.remove(can_right);
                        e_right = false;
                    }
                    else if( can_right.getClass() == Brick.class) e_right = false;
                    else {
                        Bomb idk = (Bomb) can_right;
                        if(!idk.getHasExploded()) explosionPlanner(idk);
                    }
                }
                else {
                    explosions.add(explode_right);
                    blastzone.add(explode_right);
                }
            }

            if(e_left) { //EXPAND LEFT
                Position left = new Position(bomb.getX() - i, bomb.getY());
                Element can_left = canElementMove(left);
                Explosion explode_left = new Explosion(left.getX(), left.getY());
                if (can_left != null) {
                    if (can_left.getClass() == Wood.class) {
                        explosions.add(explode_left);
                        blastzone.add(explode_left);
                        woods.remove(can_left);
                        e_left = false;
                    }
                    else if( can_left.getClass() == Brick.class) e_left = false;
                    else {
                        Bomb idk = (Bomb) can_left;
                        if(!idk.getHasExploded()) explosionPlanner(idk);
                    }
                }
                else {
                    explosions.add(explode_left);
                    blastzone.add(explode_left);
                }
            }

            if(e_down) { //EXPAND DOWN
                Position down = new Position(bomb.getX(), bomb.getY() + i);
                Element can_down = canElementMove(down);
                Explosion explode_down = new Explosion(down.getX(), down.getY());
                if (can_down != null) {
                    if (can_down.getClass() == Wood.class) {
                        explosions.add(explode_down);
                        blastzone.add(explode_down);
                        woods.remove(can_down);
                        e_down = false;
                    }
                    else if( can_down.getClass() == Brick.class) e_down = false;
                    else {
                        Bomb idk = (Bomb) can_down;
                        if(!idk.getHasExploded()) explosionPlanner(idk);
                    }
                }
                else {
                    explosions.add(explode_down);
                    blastzone.add(explode_down);
                }
            }

            if(e_up) { //EXPAND UP
                Position up = new Position(bomb.getX(), bomb.getY() - i);
                Element can_up = canElementMove(up);
                Explosion explode_up = new Explosion(up.getX(), up.getY());
                if (can_up != null) {
                    if (can_up.getClass() == Wood.class) {
                        explosions.add(explode_up);
                        blastzone.add(explode_up);
                        woods.remove(can_up);
                        e_up = false;
                    }
                    else if( can_up.getClass() == Brick.class) e_up = false;
                    else {
                        Bomb idk = (Bomb) can_up;
                        if(!idk.getHasExploded()) explosionPlanner(idk);
                    }
                }
                else {
                    explosions.add(explode_up);
                    blastzone.add(explode_up);
                }
            }
        }


        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1500); // Wait for additional 1.5 seconds
                for(Explosion blast: blastzone) explosions.remove(blast);
                bombs.remove(bomb);
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
                if ((x%2!=0 || y%2!=0) &
                        !(x==1 & y==1) & !(x==1 & y==2) & !(x==2 & y==1) &
                        !(x==13 & y==13) & !(x==13 & y==12) & !(x==12 & y==13)) {
                    if (shouldAddWood()) {
                        woods.add(new Wood(x, y));
                    }
                }
            }
        }
        return woods;
    }
    private boolean shouldAddWood() {
        Random random = new Random();
        // Adjust the spawn rate by modifying the probability
        double spawnRate = 0.4; // Adjust this value (0.0 to 1.0) for your desired spawn rate
        return random.nextDouble() < spawnRate;
    }

    public void draw(TextGraphics graphics) throws CloneNotSupportedException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        for (Brick brick : bricks) brick.draw(graphics, "#6B93C5", "\u0080");
        List<Wood> woods_copy = new ArrayList<Wood>(woods);
        for (Wood wood : woods_copy) wood.draw(graphics, "#9C929A", "#");
        List<Bomb> bombs_copy = new ArrayList<Bomb>(bombs);
        for (Bomb bomb : bombs_copy) bomb.draw(graphics, "#000000", "\u0083");
        List<Explosion> explosions_copy = new ArrayList<Explosion>(explosions);
        for (Explosion explosion : explosions_copy) explosion.draw(graphics, "#FFA500", "\u0085");

        if(player1.getStatus()) player1.draw(graphics, "#FFFFFF", "\u0081");
        else{
            new Text(1, 6).draw(graphics, "             ", false);
            new Text(1, 7).draw(graphics, "PLAYER 2 WON!", false);
            new Text(1, 8).draw(graphics, "             ", false);
            player2.setPosition(new Position(3, 7));
            player2.draw(graphics, "#F27379", "\u0082");
        }
        if(player2.getStatus()) player2.draw(graphics, "#F27379", "\u0082");
        else {
            /*new Text(1, 1).draw(graphics, "             ", false);
            new Text(1, 2).draw(graphics, "             ", false);
            new Text(1, 3).draw(graphics, "             ", false);
            new Text(1, 4).draw(graphics, "             ", false);
            new Text(1, 5).draw(graphics, "             ", false);
            new Text(1, 6).draw(graphics, "             ", false);
            new Text(1, 7).draw(graphics, "PLAYER 1 WON!", false);
            new Text(1, 8).draw(graphics, "             ", false);
            new Text(1, 9).draw(graphics, "             ", false);
            new Text(1, 10).draw(graphics, "             ", false);
            new Text(1, 11).draw(graphics, "             ", false);
            new Text(1, 12).draw(graphics, "             ", false);
            new Text(1, 13).draw(graphics, "             ", false);*/

            new Text(1, 6).draw(graphics, "             ", false);
            new Text(1, 7).draw(graphics, "PLAYER 1 WON!", false);
            new Text(1, 8).draw(graphics, "             ", false);

            player1.setPosition(new Position(3, 7));
            player1.draw(graphics, "#FFFFFF", "\u0081");
        }
    }

    public Player whoExploded() {  // devia ser passado para o game controller talvez
        for (Explosion explosion : explosions) {
            if (explosion.getPosition().equals(player1.getPosition())) {
                player1.kill();
                return player1;
            }
            if (explosion.getPosition().equals(player2.getPosition())) {
                player2.kill();
                return player2;
            }
        }
        return null;
    }

}
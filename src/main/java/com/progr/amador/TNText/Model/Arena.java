package com.progr.amador.TNText.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.progr.amador.TNText.Model.Elements.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
        explosionPlanner(bomb);
    }

    public void explosionPlanner(Bomb bomb) {
        //TODO: wait 3 seconds
        explosions.add(new Explosion(bomb.getX(), bomb.getY())); //BOMB CENTER
        boolean e_right = true, e_left = true, e_up = true, e_down = true;

        for(int i = 1; i <= bomb.getRadius(); i++){

            if(e_right) { //EXPAND RIGHT
                Position right = new Position(bomb.getX() + i, bomb.getY());
                Element can_right = canElementMove(right);
                if (can_right != null) {
                    if (can_right.getClass() == Wood.class) {
                        explosions.add(new Explosion(right.getX(), right.getY()));
                        woods.remove(can_right);
                        // TODO: remove from woods
                    }
                    e_right = false;
                }
                else explosions.add(new Explosion(right.getX(), right.getY()));
            }

            if(e_left) { //EXPAND LEFT
                Position left = new Position(bomb.getX() - i, bomb.getY());
                Element can_left = canElementMove(left);
                if (can_left != null) {
                    if (can_left.getClass() == Wood.class) {
                        explosions.add(new Explosion(left.getX(), left.getY()));
                        woods.remove(can_left);
                        // TODO: remove from woods
                    }
                    e_left = false;
                }
                else explosions.add(new Explosion(left.getX(), left.getY()));
            }

            if(e_down) { //EXPAND DOWN
                Position down = new Position(bomb.getX(), bomb.getY() + i);
                Element can_down = canElementMove(down);
                if (can_down != null) {
                    if (can_down.getClass() == Wood.class) {
                        explosions.add(new Explosion(down.getX(), down.getY()));
                        woods.remove(can_down);
                        // TODO: remove from woods
                    }
                    e_down = false;
                }
                else explosions.add(new Explosion(down.getX(), down.getY()));
            }

            if(e_up) { //EXPAND UP
                Position up = new Position(bomb.getX(), bomb.getY() - i);
                Element can_up = canElementMove(up);
                if (can_up != null) {
                    if (can_up.getClass() == Wood.class) {
                        explosions.add(new Explosion(up.getX(), up.getY()));
                        woods.remove(can_up);
                        // TODO: remove from woods
                    }
                    e_up = false;
                }
                else explosions.add(new Explosion(up.getX(), up.getY()));
            }

            if(!(e_right || e_left || e_down || e_up)) break;
        }
    }

    private Element canElementMove(Position position) {  // devia ser passado para o game controller talvez
        for (Brick brick : bricks) {
            if (brick.getPosition().equals(position)) return brick;
        }
        for (Wood wood : woods) {
            if (wood.getPosition().equals(position)) return wood;
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
        double spawnRate = 0.1; // Adjust this value (0.0 to 1.0) for your desired spawn rate
        return random.nextDouble() < spawnRate;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
        player1.draw(graphics, "#FFFFFF", "\u0081");
        player2.draw(graphics, "#F27379", "\u0082");

        for (Brick brick : bricks) brick.draw(graphics, "#6B93C5", "\u0080");
        for (Wood wood : woods) wood.draw(graphics, "#9C929A", "#");
        for (Bomb bomb : bombs) bomb.draw(graphics, "#000000", "\u0083");
        for (Explosion explosion : explosions) explosion.draw(graphics, "#FFA500", "\u0085");
    }

}

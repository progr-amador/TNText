package com.progr.amador.TNText.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.progr.amador.TNText.Model.Elements.Brick;
import com.progr.amador.TNText.Model.Elements.Player;
import com.progr.amador.TNText.Model.Elements.Wood;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    private int width, height;
    private Player player1 = new Player(1, 1);
    private Player player2 = new Player(13, 13);
    private List<Brick> bricks;
    public List<Wood> woods;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.bricks = createBricks();
        this.woods = createWoods();
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
        double spawnRate = 0.3; // Adjust this value (0.0 to 1.0) for your desired spawn rate
        return random.nextDouble() < spawnRate;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
        player1.draw(graphics, "#FFFFFF", "\u0081");
        player2.draw(graphics, "#F27379", "\u0082");

        for (Brick brick : bricks) brick.draw(graphics, "#6B93C5", "\u0080");
        for (Wood wood : woods) wood.draw(graphics, "#9C929A", "#");
    }

}

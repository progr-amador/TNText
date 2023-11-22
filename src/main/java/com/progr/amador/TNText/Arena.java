package com.progr.amador.TNText;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width, height;
    private Player player1 = new Player(1, 1);
    private Player player2 = new Player(59, 19);


    private List<Brick> bricks;
    public List<Wood> woods;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.bricks = createBricks();
        //this.woods = createWoods();
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

    private boolean canElementMove(Position position) {
        for (Brick brick : bricks) {
            if (brick.getPosition().equals(position)) return false;
        }
        return true;
    }
    private void moveHero(Player player,Position position) {
        if (canElementMove(position)) {
            player.setPosition(position);
        }
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#373F47"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
        player1.draw(graphics, "#FFFFFF", "☻");
        player2.draw(graphics, "#F27379", "☻");

        for (Brick brick : bricks) brick.draw(graphics, "#6C91C2", "█");
    }


    public boolean processKey(KeyStroke key, Screen screen) throws IOException {
        switch (key.getKeyType()) {
            case ArrowLeft -> {
                moveHero(player2, player2.moveLeft());
                break;
            }
            case ArrowRight -> {
                moveHero(player2, player2.moveRight());
                break;
            }
            case ArrowUp -> {
                moveHero(player2, player2.moveUp());
                break;
            }
            case ArrowDown -> {
                moveHero(player2, player2.moveDown());
                break;
            }
            case Character -> {
                if (key.getCharacter() == 'a' || key.getCharacter() == 'A') moveHero(player1, player1.moveLeft());
                else if (key.getCharacter() == 'd' || key.getCharacter() == 'D') moveHero(player1, player1.moveRight());
                else if (key.getCharacter() == 'w' || key.getCharacter() == 'W') moveHero(player1, player1.moveUp());
                else if (key.getCharacter() == 's' || key.getCharacter() == 'S') moveHero(player1, player1.moveDown());

                else if (key.getCharacter() == 'q' || key.getCharacter() == 'Q') screen.close();
                break;
            }
            case EOF -> {
                return true;
            }
            default -> {
                break;
            }
        }
        return false;
    }
}

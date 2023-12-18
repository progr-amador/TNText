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
        createBricks();
        createWoods();
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

    public void addBomb(Bomb bomb) {
        if (bomb.getPlayer().getBag() <= 0) return;

        for (Bomb other_bomb : bombs) if (other_bomb != null && other_bomb.getPosition().equals(bomb.getPosition())) return;

        bomb.getPlayer().lessBag();
        bombs.add(bomb);

        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(2500); // Wait for 2.5 seconds
                if (!bomb.hasExploded()) explosionPlanner(bomb);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void explosionPlanner(Bomb bomb) {
        List<Explosion> blastzone = new ArrayList<>();
        bomb.explode();
        blastzone.add(new Explosion(bomb.getX(), bomb.getY()));
        boolean e_right = true, e_left = true, e_up = true, e_down = true;

        bombs.remove(bomb);
        for(int i = 1; i <= bomb.getRadius(); i++){
            if(e_right) e_right = expandDirection(new Position(bomb.getX() + i, bomb.getY()), blastzone);
            if(e_left) e_left = expandDirection(new Position(bomb.getX() - i, bomb.getY()), blastzone);
            if(e_down) e_down = expandDirection(new Position(bomb.getX(), bomb.getY() + i), blastzone);
            if(e_up) e_up = expandDirection(new Position(bomb.getX(), bomb.getY() - i), blastzone);
        }

        explosions.addAll(blastzone);

        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000); // Wait for additional 1 second
                bomb.getPlayer().plusBag();
                explosions.removeAll(blastzone);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean expandDirection(Position direction, List<Explosion> blastzone){
        Element can_it_move = canElementMove(direction);
        Explosion explosion = new Explosion(direction.getX(), direction.getY());

        if (can_it_move == null) {
            blastzone.add(explosion);
            return true;
        } else if (can_it_move instanceof Wood) {
            blastzone.add(explosion);
            woods.remove(can_it_move);
        } else if (can_it_move instanceof Bomb close_bomb) {
            if (!close_bomb.hasExploded()) explosionPlanner(close_bomb);
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

    private void createBricks() {
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
    }

    private void createWoods() {
        for (int x = 1; x < width-1; x++) {
            for (int y = 1; y < height-1; y++) {
                if ((x % 2 != 0 || y % 2 != 0) &
                    !(x == 1 & y == 1) & !(x == 1 & y == 2) & !(x == 2 & y == 1) &
                    !(x == width - 2 & y == height - 2) & !(x == width - 2 & y == height - 3) & !(x == width - 3 & y == height - 2)) {
                    shouldAddWood(x, y);
                }
            }
        }
    }

    private void shouldAddWood(int x, int y) {
        if(new Random().nextDouble() < 0.3) { // Adjust this value (0.0 to 1.0) for your desired spawn rate
            woods.add(new Wood(x, y));
            shouldAddPowerup(x, y);
        }
    }

    private void shouldAddPowerup(int x, int y) {
        if(new Random().nextDouble() < 0.25) whichPowerup(x, y);
    }

    private void whichPowerup(int x, int y) {
        if(Application.getInstance().checkPlusPower() && Application.getInstance().checkPlusBomb()) {
            if (new Random().nextDouble() < 0.5) powerups.add(new PlusBomb(x, y));
            else powerups.add(new PlusPower(x, y));
        }
        else if (Application.getInstance().checkPlusPower()) powerups.add(new PlusPower(x, y));
        else if (Application.getInstance().checkPlusBomb()) powerups.add(new PlusBomb(x, y));
    }

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
package com.progr.amador.TNText.Model;

import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Model.Elements.*;
import com.progr.amador.TNText.Model.Elements.Powerup.PlusBomb;
import com.progr.amador.TNText.Model.Elements.Powerup.PlusPower;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class ArenaBuilder {
    private Arena arena;

    public ArenaBuilder(Arena arena) {
        this.arena = arena;
        createBricks();
        createWoods();
    }

    public void addBomb(Bomb bomb) {
        if (bomb.getPlayer().getBag() <= 0) return;

        for (Bomb other_bomb : arena.getBombs()) if (other_bomb != null && other_bomb.getPosition().equals(bomb.getPosition())) return;

        bomb.getPlayer().lessBag();
        arena.getBombs().add(bomb);

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
        CopyOnWriteArrayList<Explosion> blastzone = new CopyOnWriteArrayList<>();
        bomb.explode();
        blastzone.add(new Explosion(bomb.getX(), bomb.getY()));
        boolean e_right = true, e_left = true, e_up = true, e_down = true;

        arena.getBombs().remove(bomb);
        for(int i = 1; i <= bomb.getRadius(); i++){
            if(e_right) e_right = expandDirection(new Position(bomb.getX() + i, bomb.getY()), blastzone);
            if(e_left) e_left = expandDirection(new Position(bomb.getX() - i, bomb.getY()), blastzone);
            if(e_down) e_down = expandDirection(new Position(bomb.getX(), bomb.getY() + i), blastzone);
            if(e_up) e_up = expandDirection(new Position(bomb.getX(), bomb.getY() - i), blastzone);
        }

        arena.getExplosions().addAll(blastzone);

        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000); // Wait for additional 1 second
                bomb.getPlayer().plusBag();
                arena.getExplosions().removeAll(blastzone);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean expandDirection(Position direction, CopyOnWriteArrayList<Explosion> blastzone){

        for (Brick brick : arena.getBricks()) {
            if (brick.getPosition().equals(direction)) return false;
        }
        for (Wood wood : arena.getWoods()) {
            if (wood.getPosition().equals(direction)) {
                blastzone.add(new Explosion(direction.getX(), direction.getY()));
                arena.getWoods().remove(wood);
                return false;
            }
        }
        for (Bomb bomb : arena.getBombs()) {
            if (bomb.getPosition().equals(direction)) {
                if (!bomb.hasExploded()) explosionPlanner(bomb);
                return false;
            }
        }

        blastzone.add(new Explosion(direction.getX(), direction.getY()));
        return true;
    }

    private void createBricks() {
        for (int c = 0; c < arena.getWidth(); c++) {
            arena.getBricks().add(new Brick(c, 0));
            arena.getBricks().add(new Brick(c, arena.getHeight() - 1));
        }
        for (int r = 1; r < arena.getHeight() - 1; r++) {
            arena.getBricks().add(new Brick(0, r));
            arena.getBricks().add(new Brick(arena.getWidth() - 1, r));
        }
        for(int x = 2; x < arena.getWidth() - 1; x=x+2){
            for(int y = 2; y < arena.getHeight() - 1; y=y+2){
                arena.getBricks().add(new Brick(x, y));
            }
        }
    }

    private void createWoods() {
        for (int x = 1; x < arena.getWidth()-1; x++) {
            for (int y = 1; y < arena.getHeight()-1; y++) {
                if ((x % 2 != 0 || y % 2 != 0) &
                        !(x == 1 & y == 1) & !(x == 1 & y == 2) & !(x == 2 & y == 1) &
                        !(x == arena.getWidth() - 2 & y == arena.getHeight() - 2) &
                        !(x == arena.getWidth() - 2 & y == arena.getHeight() - 3) &
                        !(x == arena.getWidth() - 3 & y == arena.getHeight() - 2)) {
                    shouldAddWood(x, y);
                }
            }
        }
    }

    private void shouldAddWood(int x, int y) {
        if(new Random().nextDouble() < 0.3) { // Adjust this value (0.0 to 1.0) for your desired spawn rate
            arena.getWoods().add(new Wood(x, y));
            shouldAddPowerup(x, y);
        }
    }

    private void shouldAddPowerup(int x, int y) {
        if(new Random().nextDouble() < 0.25) whichPowerup(x, y);
    }

    private void whichPowerup(int x, int y) {
        if(Application.getInstance().checkPlusPower() && Application.getInstance().checkPlusBomb()) {
            if (new Random().nextDouble() < 0.5) arena.getPowerups().add(new PlusBomb(x, y));
            else arena.getPowerups().add(new PlusPower(x, y));
        }
        else if (Application.getInstance().checkPlusPower()) arena.getPowerups().add(new PlusPower(x, y));
        else if (Application.getInstance().checkPlusBomb()) arena.getPowerups().add(new PlusBomb(x, y));
    }

}

package com.progr.amador.TNText.Controller.Elements;

import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Model.ArenaBuilder;
import com.progr.amador.TNText.Model.Elements.Brick;
import com.progr.amador.TNText.Model.Elements.Player;
import com.progr.amador.TNText.Model.Elements.Powerup.PlusBomb;
import com.progr.amador.TNText.Model.Elements.Powerup.PlusPower;
import com.progr.amador.TNText.Model.Elements.Powerup.Powerup;
import com.progr.amador.TNText.Model.Elements.Wood;
import com.progr.amador.TNText.Model.Position;

public class GameController extends Controller<Arena> {
    
    public GameController(Arena arena) {super(arena);}

    public void movePlayer(Player player, Position position) {
        for (Brick brick : this.getModel().getBricks()) {
            if (brick.getPosition().equals(position)) return;
        }
        for (Wood wood : this.getModel().getWoods()) {
            if (wood.getPosition().equals(position)) return;
        }

        for (Powerup powerup: getModel().getPowerups()){
            if (powerup.getPosition().equals(position)) {
                if (powerup instanceof PlusBomb) {
                    player.plusBag();
                }
                if (powerup instanceof PlusPower) {
                    player.plusPower();
                }
                getModel().getPowerups().remove(powerup);
            }
        }

        player.setPosition(position);
    }
}

package com.progr.amador.TNText.Controller;

import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Model.Elements.Brick;
import com.progr.amador.TNText.Model.Elements.Player;
import com.progr.amador.TNText.Model.Elements.Powerup.Powerup;
import com.progr.amador.TNText.Model.Elements.Wood;
import com.progr.amador.TNText.Model.Position;

public class GameController extends Controller<Arena> {
    
    public GameController(Arena arena) {super(arena);}

    public void movePlayer(Player player, Position position) {
        for (Brick brick : getModel().getBricks()) {
            if (brick.getPosition().equals(position)) return;
        }
        for (Wood wood : getModel().getWoods()) {
            if (wood.getPosition().equals(position)) return;
        }

        for (Powerup powerup: getModel().getPowerups()){
            if (powerup.getPosition().equals(position)) {
                powerup.execute(player);
                getModel().getPowerups().remove(powerup);
                break;
            }
        }

        player.setPosition(position);
    }
}

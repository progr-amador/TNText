package com.progr.amador.TNText.Controller.Elements;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.progr.amador.TNText.Application;
import com.progr.amador.TNText.Model.Arena;
import com.progr.amador.TNText.Controller.Controller;
import com.progr.amador.TNText.Model.Elements.Bomb;
import com.progr.amador.TNText.Model.Elements.Player;
import com.progr.amador.TNText.Model.Menu;
import com.progr.amador.TNText.State.MenuState;

import java.io.IOException;

public class GameController extends Controller<Arena> {
    public GameController(Arena arena) {super(arena);}
}

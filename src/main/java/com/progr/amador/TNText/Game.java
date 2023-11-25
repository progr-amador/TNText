package com.progr.amador.TNText;


import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.progr.amador.TNText.Controller.Elements.ArenaController;
import com.progr.amador.TNText.Controller.Elements.PlayerController;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Game {
    Screen screen = null;
    ArenaController arenaController = new ArenaController(new Arena(15,15));

    public Game() {
        try {
            File fontFile = new File("src/main/resources/Square-Regular.ttf");
            Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            DefaultTerminalFactory factory = new DefaultTerminalFactory();

            Font loadedFont = font.deriveFont(Font.PLAIN, 50);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
            factory.setTerminalEmulatorFontConfiguration(fontConfig);
            factory.setForceAWTOverSwing(true);
            factory.setInitialTerminalSize(new TerminalSize(15, 15));
            factory.setTerminalEmulatorTitle("TNText");

            Terminal terminal = factory.createTerminal();

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public Screen getScreen() {return screen;}

    public ArenaController getArenaController() {return arenaController;}

    private void draw() throws IOException {
        screen.clear();
        arenaController.getPlayerController().getModel().draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() throws IOException {
        while(true){
            try {
                draw(); // Call the private draw method within the Game class
            } catch (IOException e) {
                e.printStackTrace();
            }

            boolean over = false;
            KeyStroke key = screen.readInput();
            over = arenaController.getPlayerController().processKey(arenaController.getModel().getPlayer1(), arenaController.getModel().getPlayer2(), key, screen) ;
            if (over) break;
        }
    }
}
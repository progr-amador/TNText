package com.progr.amador.TNText;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TerminalGUI2 {
    Screen screen;

    public TerminalGUI2() {
        try {
            SwingTerminal terminal = new SwingTerminal();
            terminal.setFont(new Font("src/main/resources/fonts/Square-Regular.ttf", Font.PLAIN, 50));
            //terminal.
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Font getFont() throws IOException, FontFormatException {
        File fontFile = new File("src/main/resources/fonts/Square-Regular.ttf");
        //File fontFile = new File("src/main/resources/fonts/Square-Regular.ttf");
        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        return font.deriveFont(Font.PLAIN, 50);
    }

    public Terminal getTerminal() throws IOException, FontFormatException {
        DefaultTerminalFactory factory = new DefaultTerminalFactory();

        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(getFont());
        factory.setTerminalEmulatorFontConfiguration(fontConfig);
        factory.setForceAWTOverSwing(true);
        factory.setInitialTerminalSize(new TerminalSize(15, 15));
        factory.setTerminalEmulatorTitle("TNText");

        return factory.createTerminal();
    }
}

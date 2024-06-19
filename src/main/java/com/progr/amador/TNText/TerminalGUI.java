package com.progr.amador.TNText;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.io.InputStream;


public class TerminalGUI {
    Screen screen;
    //Sound sound = new Sound();

    TextGraphics textGraphics;

    public TerminalGUI() {
        try {
            Terminal terminal = getTerminal();

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
            textGraphics = screen.newTextGraphics();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Terminal getTerminal() throws IOException, FontFormatException, URISyntaxException {
        DefaultTerminalFactory factory = new DefaultTerminalFactory();

        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(getFont());
        factory.setTerminalEmulatorFontConfiguration(fontConfig);
        factory.setForceAWTOverSwing(true);
        factory.setInitialTerminalSize(new TerminalSize(15, 17));
        factory.setTerminalEmulatorTitle("TNText");

        return factory.createTerminalEmulator();
    }

    public Font getFont() throws IOException, FontFormatException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("fonts/SquareGameEdit.ttf");
        if (inputStream == null) {
            throw new IOException("Font file not found");
        }

        Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        return font.deriveFont(Font.PLAIN, 45);
    }

    public Screen getScreen() {
        return screen;
    }

    public TextGraphics getGraphics() { return textGraphics; }
}

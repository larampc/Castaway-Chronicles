package castaway_chronicles.gui;

import castaway_chronicles.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

public class LanternaGUI implements GUI{
    private final Screen screen;
    private final TextGraphics graphics;
    private final HashMap<String, Sprite> images = new HashMap<>();
    private final AWTTerminalFrame terminal;
    private boolean bigger;
    private InputEvent action;

    public LanternaGUI(AWTTerminalFrame terminal, Screen screen) throws URISyntaxException, IOException {
        this.screen = screen;
        this.graphics = screen.newTextGraphics();
        this.terminal = terminal;
        loadSprites();
    }

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        this.terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
        this.graphics = screen.newTextGraphics();
        loadSprites();
    }
    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private AWTTerminalFrame createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        Terminal terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(width, height))
                .setTerminalEmulatorFontConfiguration(fontConfig)
                .setForceAWTOverSwing(true)
                .createTerminal();
        MouseAdapter mouseAdapter = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                action = e;
            }
        };
        ((AWTTerminalFrame)terminal).getComponent(0).addMouseListener(mouseAdapter);
        ((AWTTerminalFrame)terminal).getComponent(0).addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
               action = e;
            }
        });
        return (AWTTerminalFrame) terminal;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        assert resource != null;
        File fontFile = new File(resource.toURI());
        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font newfont = font.deriveFont(Font.PLAIN, 4);
        return new SwingTerminalFontConfiguration(true, AWTTerminalFontConfiguration.BoldMode.EVERYTHING, newfont);
    }

    private void loadSprites() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("Images");
        assert resource != null;
        File fontFile = new File(resource.toURI());
        loadFiles(fontFile, images);
    }
    private void loadFiles(File dir, HashMap<String, Sprite> images) throws IOException {
        if (!dir.isDirectory()) {
            if(dir.getName().equals("question.png")) images.put("?", new Sprite(dir));
            else if(dir.getName().equals("point.png")) images.put(".", new Sprite(dir));
            else images.put(dir.getName().split(".png", -1)[0], new Sprite(dir));
            return;
        }
        for (File f : Objects.requireNonNull(dir.listFiles())) {
            loadFiles(f, images);
        }
    }
    public void loadEnding(String name) throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("Endings/"+name);
        assert resource != null;
        File fontFile = new File(resource.toURI());
        loadFiles(fontFile, images);
    }
    @Override
    public void resizeTerminal() {
        screen.doResizeIfNecessary();
        if (bigger) {
            terminal.setSize(terminal.getWidth(), 637);
        }
        else {
            terminal.setSize(terminal.getWidth(), 765);
        }
        bigger = !bigger;
        while (screen.doResizeIfNecessary() == null);
    }
    @Override
    public boolean isBigger() {
        return bigger;
    }
    @Override
    public void drawImage(Position position, String name) {
        if (images.get(name) != null) {
            images.get(name).drawSprite(position, graphics);
        }
    }

    @Override
    public void drawText(Position startPosition, int maxsize, String text, boolean underlined) {
        String[] arrOfStr = text.split(" ", -1);
        Position position = new Position(startPosition.getX(), startPosition.getY());
        for (String word : arrOfStr) {
            int wordsize = 0;
            for(int i = 0; i < word.length(); i++)
                wordsize += images.get(String.valueOf(word.charAt(i))).getWidth();
            if (position.getX() + wordsize > startPosition.getX() +maxsize)
                position = new Position(startPosition.getX(), position.getY()+10);
            if(underlined) drawLine(position.getDown(8).getLeft(1),wordsize+2);
            for(int i = 0; i < word.length(); i++) {
                drawImage(position, String.valueOf(word.charAt(i)));
                position = position.getRight(images.get(String.valueOf(word.charAt(i))).getWidth());
            }
            position = position.getRight(2);
        }
    }
    @Override
    public void drawLine(Position position, int size) {
        graphics.setForegroundColor(new TextColor.RGB(255,255,255));
        graphics.drawLine(new TerminalPosition(position.getX(), position.getY()), new TerminalPosition(position.getX()+size, position.getY()), '_');
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    @Override
    public InputEvent getNextAction() {
        InputEvent return_action = action;
        action = null;
        return return_action;
    }
    public boolean imageIsLoaded(String name) {
        return images.get(name) != null;
    }
}

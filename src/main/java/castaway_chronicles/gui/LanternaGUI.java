package castaway_chronicles.gui;

import castaway_chronicles.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;


public class LanternaGUI implements GUI{
    private final Screen screen;

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }

//    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
//        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
//        Terminal terminal = createTerminal(width, height, fontConfig);
//        this.screen = createScreen(terminal);
//    }
//    private Screen createScreen(Terminal terminal) throws IOException {
//        final Screen screen;
//        screen = new TerminalScreen(terminal);
//
//        screen.setCursorPosition(null);
//        screen.startScreen();
//        screen.doResizeIfNecessary();
//        return screen;
//    }
//
//    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
//        TerminalSize terminalSize = new TerminalSize(width, height + 1);
//        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
//                .setInitialTerminalSize(terminalSize);
//        terminalFactory.setForceAWTOverSwing(true);
//        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
//        return terminalFactory.createTerminal();
//    }
//
//    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
//        File fontFile = new File("src/main/resources/fonts/square.ttf");
//        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);
//
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        ge.registerFont(font);
//
//        Font newfont = font.deriveFont(Font.PLAIN, 4);
//
//        return new SwingTerminalFontConfiguration(true, AWTTerminalFontConfiguration.BoldMode.EVERYTHING, newfont);
//    }

    @Override
    public void drawImage(Position position, TextImage image) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.drawImage(new TerminalPosition(position.getX(), position.getY()), image);
    }

    @Override
    public void drawText(Position position, String text) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(new TextColor.RGB(255,255,255));
        graphics.putString(position.getX(), position.getY(), text);
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
}

package castaway_chronicles.gui;

import castaway_chronicles.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GUITest {
    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics graphics;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        gui = new LanternaGUI(screen);
    }

    @Test
    void drawImage() {
        BufferedImage image = new BufferedImage(10,10, BufferedImage.TYPE_INT_ARGB);
        Color myWhite = new Color(255, 255, 255); // Color white
        int rgb = myWhite.getRGB();
        image.setRGB(0,0, rgb);
        gui.drawImage(new Position(0, 0), image);

        TextCharacter c = new TextCharacter(' ', new TextColor.RGB(255, 255, 255), new TextColor.RGB(255, 255, 255));
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(0,0,c);
    }

    @Test
    void drawLine() {
        gui.drawLine(new Position(1, 1));

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(new TextColor.RGB(255,255,255));
        Mockito.verify(graphics, Mockito.times(1)).drawLine(new TerminalPosition(1, 1), new TerminalPosition(31, 1), '_');
    }

    @Test
    void refresh() throws IOException {
        gui.refresh();
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }

    @Test
    void clear() {
        gui.clear();
        Mockito.verify(screen, Mockito.times(1)).clear();
    }

    @Test
    void close() throws IOException {
        gui.close();
        Mockito.verify(screen, Mockito.times(1)).close();
    }
}

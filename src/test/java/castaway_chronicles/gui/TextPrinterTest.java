package castaway_chronicles.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextPrinterTest {
    private Screen screen;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
    }

    @Test
    public void getLetters() throws IOException, URISyntaxException {
        TextPrinter printer = new TextPrinter(new TerminalPosition(1,1), 100, screen, "");
        ArrayList<BufferedImage> buffer = new ArrayList<>();

        assertEquals(buffer, printer.getLetters(""));

        URL resource = getClass().getClassLoader().getResource("letters/uppercase/question.png");
        assert resource != null;
        buffer.add(ImageIO.read(new File(resource.toURI())));
        resource = getClass().getClassLoader().getResource("letters/uppercase/point.png");
        assert resource != null;
        buffer.add(ImageIO.read(new File(resource.toURI())));
        resource = getClass().getClassLoader().getResource("letters/uppercase/W.png");
        assert resource != null;
        buffer.add(ImageIO.read(new File(resource.toURI())));
        resource = getClass().getClassLoader().getResource("letters/lowercase/W.png");
        assert resource != null;
        buffer.add(ImageIO.read(new File(resource.toURI())));

        assertEquals(buffer.get(0).getData().toString(), printer.getLetters("?.Ww").get(0).getData().toString());
        assertEquals(buffer.get(1).getData().toString(), printer.getLetters("?.Ww").get(1).getData().toString());
        assertEquals(buffer.get(2).getData().toString(), printer.getLetters("?.Ww").get(2).getData().toString());
        assertEquals(buffer.get(3).getData().toString(), printer.getLetters("?.Ww").get(3).getData().toString());
    }

    @Test
    public void drawLetter() throws IOException, URISyntaxException {
        TextPrinter printer = new TextPrinter(new TerminalPosition(1,1), 100, screen, "?.Ww");

        URL resource = getClass().getClassLoader().getResource("Images/Test/test.png");
        assert resource != null;
        BufferedImage img = ImageIO.read(new File(resource.toURI()));

        printer.drawLetter(img);

        TextCharacter c1 = new TextCharacter(' ', new TextColor.RGB(0, 0, 0), new TextColor.RGB(0, 0, 0));
        TextCharacter c2 = new TextCharacter(' ', new TextColor.RGB(255, 255, 255), new TextColor.RGB(255, 255, 255));

        Mockito.verify(screen, Mockito.times(1)).setCharacter(2,2,c1);
        Mockito.verify(screen, Mockito.times(1)).setCharacter(10,2,c2);
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }
}

package castaway_chronicles.gui;

import castaway_chronicles.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import static org.mockito.ArgumentMatchers.*;

public class GUITest {
    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics graphics;

    @BeforeEach
    void setUp() throws URISyntaxException, IOException {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        gui = new LanternaGUI(screen);
    }

    @Test
    void drawImage() {
        gui.drawImage(new Position(0, 0), "test");
        TextCharacter c = new TextCharacter(' ', new TextColor.RGB(255, 255, 255), new TextColor.RGB(255, 255, 255));
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(10,10, c);
        Mockito.verify(graphics, Mockito.times(0)).setCharacter(eq(11),eq(11), any(TextCharacter.class));
        Mockito.verify(graphics, Mockito.times(96)).setCharacter(intThat(inte -> (inte>0 && inte <11)),intThat(inte -> (inte>0 && inte <11)), any(TextCharacter.class));
    }

    @Test
    void drawText() throws IOException, InterruptedException {
        gui.drawText(new Position(0,0),100,"Lorem ipsum ,.!?", 1);
        Mockito.verify(screen,Mockito.times(15)).refresh();

        TextCharacter c = new TextCharacter(' ', new TextColor.RGB(0, 0, 0), new TextColor.RGB(0, 0, 0));
        gui.drawText(new Position(0,0),67,"Lorem ipsum ,.!? j", 0);
        gui.drawText(new Position(0,0),70,"Lorem ipsum ,.!? jjjjj", 0);


        Mockito.verify(graphics, Mockito.times(3)).setCharacter(63,6, c);
        Mockito.verify(graphics, Mockito.times(2)).setCharacter(1,10, c);

        Mockito.verify(screen,Mockito.times(17)).refresh();
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


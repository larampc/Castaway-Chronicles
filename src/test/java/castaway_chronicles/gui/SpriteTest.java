package castaway_chronicles.gui;

import castaway_chronicles.model.Position;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class SpriteTest {
    private Position positionMock;
    private BufferedImage bufferedMock;
    private TextGraphics graphicsMock;

    @BeforeEach
    void setUp() {
        positionMock = Mockito.mock(Position.class);
        bufferedMock = Mockito.mock(BufferedImage.class);
        graphicsMock = Mockito.mock(TextGraphics.class);
    }
    @Test
    void drawSprite() throws IOException {
        Mockito.when(bufferedMock.getHeight()).thenReturn(1);
        Mockito.when(bufferedMock.getWidth()).thenReturn(1);
        Mockito.when(bufferedMock.getRGB(0, 0)).thenReturn(-26755200);
        Mockito.when(positionMock.getX()).thenReturn(0);
        Mockito.when(positionMock.getY()).thenReturn(0);
        Sprite sprite = new Sprite(new File(Path.of("src","main","resources","Images","Test","test.png").toString())){
            @Override
            public BufferedImage getSprite() {
                return bufferedMock;
            }
        };
        sprite.drawSprite(positionMock, graphicsMock);
        TextCharacter c = new TextCharacter(' ', new TextColor.RGB(103, 191, 128), new TextColor.RGB(103, 191, 128));
        Mockito.verify(graphicsMock).setCharacter(0,0, c);
    }
    @Test
    void drawSpriteTransparent() throws IOException {
        Mockito.when(bufferedMock.getHeight()).thenReturn(1);
        Mockito.when(bufferedMock.getWidth()).thenReturn(1);
        Mockito.when(bufferedMock.getRGB(0, 0)).thenReturn(0);
        Mockito.when(positionMock.getX()).thenReturn(0);
        Mockito.when(positionMock.getY()).thenReturn(0);
        Sprite sprite = new Sprite(new File(Path.of("src","main","resources","Images","Test","test.png").toString())){
            @Override
            public BufferedImage getSprite() {
                return bufferedMock;
            }
        };
        sprite.drawSprite(positionMock, graphicsMock);
        Mockito.verify(graphicsMock, Mockito.never()).setCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(TextCharacter.class));
    }
}

package castaway_chronicles.view;

import com.googlecode.lanterna.graphics.TextImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class ImagesTest {
    private ArrayList<String> ImagesList;
    @BeforeEach
    void setUp() {
        ImagesList = new ArrayList<>();
        ImagesList.add("test");
    }

    @Test
    public void ImagesLoad(){
        try {
            Images image = new Images(ImagesList, "Test");
            assertNotNull(image.getImage("test"));
        } catch (URISyntaxException | IOException e) {
            fail();
        }
    }

    @Test
    public void ImagesNotLoad() {
        try {
            Images image = new Images(ImagesList, "Test");
            assertNull(image.getImage("no test"));
        } catch (URISyntaxException | IOException e) {
            fail();
        }
    }

    @Test
    public void ImagesSize(){
        try {
            Images image = new Images(ImagesList, "Test");
            TextImage textImage = image.getImage("test");
            assertEquals(textImage.getSize().getRows(), 12);
            assertEquals(textImage.getSize().getColumns(), 12);
        } catch (URISyntaxException | IOException e) {
            fail();
        }
    }

    @Test
    public void ImagesContent(){
        try {
            Images image = new Images(ImagesList, "Test");
            TextImage textImage = image.getImage("test");
            URL resource = getClass().getClassLoader().getResource("Images/Test/test.png");
            File fontFile = new File(resource.toURI());
            BufferedImage img = ImageIO.read(fontFile);

            for (int x = 0; x < textImage.getSize().getColumns(); x++){
                for (int y = 0; y < textImage.getSize().getRows(); y++){
                    int a = img.getRGB(x, y);
                    int alpha = (a >> 24) & 0xff;
                    int red = (a >> 16) & 255;
                    int green = (a >> 8) & 255;
                    int blue = (a) & 255;

                    if (alpha != 0) {
                        assertEquals(red, textImage.getCharacterAt(x,y).getBackgroundColor().getRed());
                        assertEquals(green, textImage.getCharacterAt(x,y).getBackgroundColor().getGreen());
                        assertEquals(blue, textImage.getCharacterAt(x,y).getBackgroundColor().getBlue());
                    }
                    else {
                        assertEquals(0, textImage.getCharacterAt(x,y).getBackgroundColor().getRed());
                        assertEquals(0, textImage.getCharacterAt(x,y).getBackgroundColor().getGreen());
                        assertEquals(0, textImage.getCharacterAt(x,y).getBackgroundColor().getBlue());
                    }

                }
            }
        } catch (URISyntaxException | IOException e) {
            fail();
        }
    }
}

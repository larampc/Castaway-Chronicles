package castaway_chronicles.view;

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
            BufferedImage textImage = image.getImage("test");
            assertEquals(textImage.getWidth(), 12);
            assertEquals(textImage.getHeight(), 12);
        } catch (URISyntaxException | IOException e) {
            fail();
        }
    }

    @Test
    public void ImagesContent(){
        try {
            Images image = new Images(ImagesList, "Test");
            BufferedImage textImage = image.getImage("test");
            URL resource = getClass().getClassLoader().getResource("Images/Test/test.png");
            assertNotNull(resource);
            File fontFile = new File(resource.toURI());
            BufferedImage img = ImageIO.read(fontFile);

            for (int x = 0; x < textImage.getWidth(); x++){
                for (int y = 0; y < textImage.getHeight(); y++){
                    assertEquals(img.getRGB(x, y), textImage.getRGB(x,y));
                }
            }
        } catch (URISyntaxException | IOException e) {
            fail();
        }
    }
}

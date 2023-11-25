package castaway_chronicles.view;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class Images {
    private HashMap<String, TextImage> images;
    private final String type;
    public Images(List<String> images_to_load, String type) throws URISyntaxException, IOException {
        for (String name: images_to_load) {
            images.put(name, loadpngimage(name));
        }
        this.type = type;
    }
    private TextImage loadpngimage(String filename) throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("Images/" + type + File.separator + filename + ".png");
        File fontFile = new File(resource.toURI());
        BufferedImage img = ImageIO.read(fontFile);

        int width = img.getWidth();
        int height = img.getHeight();
        TextImage image = new BasicTextImage(width, height);

        for (int x = 0; x < img.getWidth(); x++){
            for (int y = 0; y < img.getHeight(); y++){
                int a = img.getRGB(x, y);
                int alpha = (a >> 24) & 0xff;
                int red = (a >> 16) & 255;
                int green = (a >> 8) & 255;
                int blue = (a) & 255;
                if (alpha != 0) {
                    TextCharacter c = new TextCharacter(' ', new TextColor.RGB(red, green, blue), new TextColor.RGB(red, green, blue));
                    image.setCharacterAt(x, y, c);
                }
            }
        }
        return image;
    }
    public TextImage getImage(String name) {
        return images.get(name);
    }
}

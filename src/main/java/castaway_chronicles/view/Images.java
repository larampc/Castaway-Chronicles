package castaway_chronicles.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class Images {
    private final HashMap<String, BufferedImage> images = new HashMap<>();
    private final String type;
    public Images(List<String> images_to_load, String type) throws URISyntaxException, IOException {
        this.type = type;
        for (String name: images_to_load) {
            images.put(name, loadpngimage(name));
        }
    }
    private BufferedImage loadpngimage(String filename) throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("Images/" + type + "/" + filename + ".png");
        assert resource != null;
        File fontFile = new File(resource.toURI());
        return ImageIO.read(fontFile);
    }
    public BufferedImage getImage(String name) {
        return images.get(name);
    }
}

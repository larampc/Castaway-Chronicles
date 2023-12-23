package castaway_chronicles.gui;

import castaway_chronicles.model.Position;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
    private final BufferedImage sprite;
    public Sprite(File fontFile) throws IOException {sprite = ImageIO.read(fontFile);}
    public void drawSprite(Position position, TextGraphics graphics) {
        for (int x = 0; x < getSprite().getWidth(); x++){
            for (int y = 0; y < getSprite().getHeight(); y++){
                int a = getSprite().getRGB(x, y);
                int alpha = (a >> 24) & 0xff;
                int red = (a >> 16) & 255;
                int green = (a >> 8) & 255;
                int blue = a & 255;
                if (alpha != 0) {
                    TextCharacter c = new TextCharacter(' ', new TextColor.RGB(red, green, blue), new TextColor.RGB(red, green, blue));
                    graphics.setCharacter(position.getX() + x, position.getY() + y, c);
                }
            }
        }
    }
    public int getWidth() {return sprite.getWidth();}
    public BufferedImage getSprite() {return sprite;}
}

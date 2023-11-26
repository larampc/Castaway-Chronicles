package castaway_chronicles.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TextPrinter {
    private final String s;
    private final Screen screen;
    private TerminalPosition position;
    private final TerminalPosition startPosition;
    private final int maxsize;
    private final int spacing = 10;
    private final int space = 2;
    private int writeTime = 0;
    public TextPrinter(TerminalPosition startPosition, int maxsize, Screen screen, String s) {
        this.s = s;
        this.screen = screen;
        this.startPosition = startPosition;
        this.maxsize = maxsize;
        this.position = startPosition;
    }
    public void newLine(){
        System.out.println(position.getRow());
        position = new TerminalPosition(startPosition.getColumn(), position.getRow()+spacing);
    }

    public void drawLetter(BufferedImage letter) throws IOException {
        for (int x = position.getColumn(); x < position.getColumn() + letter.getWidth(); x++) {
            for (int y = position.getRow(); y < position.getRow() + letter.getHeight(); y++) {
                int a = letter.getRGB(x - position.getColumn(), y - position.getRow());
                int alpha = (a >> 24) & 0xff;
                int red = (a >> 16) & 255;
                int green = (a >> 8) & 255;
                int blue = (a) & 255;
                if (alpha != 0) {
                    TextCharacter c = new TextCharacter(' ', new TextColor.RGB(red, green, blue), new TextColor.RGB(red, green, blue));
                    screen.setCharacter(x, y, c);
                }
            }
        }
        screen.refresh();
        position = new TerminalPosition(position.getColumn() + letter.getWidth(), position.getRow());
        if (position.getColumn() > startPosition.getColumn()+maxsize) newLine();
    }
    ArrayList<BufferedImage> getLetters(String word) throws IOException, URISyntaxException {
        ArrayList<BufferedImage> buffer = new ArrayList<>();
        for (int l = 0; l < word.length(); l++) {
            char c = word.charAt(l);
            BufferedImage img2;
            if ('?' == c) {
                URL resource = getClass().getClassLoader().getResource("letters/uppercase/question.png");
                assert resource != null;
                img2 = ImageIO.read(new File(resource.toURI()));
            }
            else if ('.' == c) {
                URL resource = getClass().getClassLoader().getResource("letters/uppercase/point.png");
                assert resource != null;
                img2 = ImageIO.read(new File(resource.toURI()));
            }
            else if (Character.isLowerCase(c)) {
                URL resource = getClass().getClassLoader().getResource("letters/lowercase/" + c + ".png");
                assert resource != null;
                img2 = ImageIO.read(new File(resource.toURI()));
            } else {
                URL resource = getClass().getClassLoader().getResource("letters/uppercase/" + c + ".png");
                assert resource != null;
                img2 = ImageIO.read(new File(resource.toURI()));
            }
            buffer.add(img2);
        }
        return buffer;
    }
    public void print() throws IOException, InterruptedException, URISyntaxException {
        String[] arrOfStr = s.split(" ");
        for (String a : arrOfStr) {
            ArrayList<BufferedImage> arr = getLetters(a);
            int wordsize = 0;
            for(BufferedImage b : arr) wordsize += b.getWidth();
            if (position.getColumn() + wordsize > startPosition.getColumn()+maxsize) newLine();
            for(BufferedImage b : arr) drawLetter(b);
            TimeUnit.MILLISECONDS.sleep(writeTime);
            position = new TerminalPosition(position.getColumn() + space, position.getRow());
        }
    }
    public void setWriteTime(int writeTime) {this.writeTime = writeTime;}
}

package castaway_chronicles.gui;

import castaway_chronicles.model.Position;
import com.googlecode.lanterna.graphics.TextImage;

import java.io.IOException;

public interface GUI {
    //method to get next action
    void drawImage(Position position, TextImage image);
    void drawText(Position position, String text);
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
}

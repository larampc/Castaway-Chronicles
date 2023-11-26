package castaway_chronicles.gui;

import castaway_chronicles.model.Position;
import com.googlecode.lanterna.graphics.TextImage;

import java.io.IOException;
import java.net.URISyntaxException;

public interface GUI {
    //method to get next action
    void drawImage(Position position, TextImage image);
    void drawText(Position startPosition, int maxsize, String text) throws IOException, InterruptedException, URISyntaxException;
    void drawLine(Position position);
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
}

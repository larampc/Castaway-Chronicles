package castaway_chronicles.gui;

import castaway_chronicles.model.Position;

import java.io.IOException;
import java.net.URISyntaxException;

public interface GUI {
    //method to get next action
    void drawImage(Position position, String name);
    void drawText(Position startPosition, int maxsize, String text, boolean underlined) throws IOException, InterruptedException, URISyntaxException;
    void drawLine(Position position, int size);
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
    void resizeTerminal() throws IOException;
    boolean isBigger();
    Action getNextAction();
}

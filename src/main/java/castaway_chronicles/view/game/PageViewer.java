package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;

import java.io.IOException;
import java.net.URISyntaxException;

public interface PageViewer<T> {
    void draw(T model, GUI gui) throws IOException, URISyntaxException, InterruptedException;
}

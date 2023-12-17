package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ScreenViewer<T> {
    void draw(T model, GUI gui) throws IOException, URISyntaxException, InterruptedException;
}

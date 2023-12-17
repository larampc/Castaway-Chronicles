package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Viewer<T> {
    private final T model;

    protected Viewer(T model) {
        this.model = model;
    }
    public T getModel() {
        return model;
    }
    public void draw(GUI gui) throws IOException, URISyntaxException, InterruptedException {
        gui.clear();
        drawScreen(gui);
        gui.refresh();
    }
    public abstract void drawScreen(GUI gui) throws IOException, InterruptedException, URISyntaxException;
}

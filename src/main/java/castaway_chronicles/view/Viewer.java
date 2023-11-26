package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Viewer<T> {
    private final Images images;
    private final T model;

    protected Viewer(T model, Images images) {
        this.model = model;
        this.images = images;
    }
    public T getModel() {return model;}
    public void draw(GUI gui) throws IOException, URISyntaxException, InterruptedException {
        gui.clear();
        drawElements(gui);
        gui.refresh();
    }
    public Images getImages() {return images;}
    protected abstract void drawElements(GUI gui) throws IOException, InterruptedException, URISyntaxException;
}

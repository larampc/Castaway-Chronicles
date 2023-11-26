package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

public abstract class Viewer<T> {
    private final HashMap<String, Images> all_images;
    private final T model;

    protected Viewer(T model, HashMap<String, Images> images) {
        this.model = model;
        this.all_images = images;
    }
    public T getModel() {return model;}
    public void draw(GUI gui) throws IOException, URISyntaxException, InterruptedException {
        gui.clear();
        drawElements(gui);
        gui.refresh();
    }
    public HashMap<String, Images> getImages() {return all_images;}
    protected abstract void drawElements(GUI gui) throws IOException, InterruptedException, URISyntaxException;
}

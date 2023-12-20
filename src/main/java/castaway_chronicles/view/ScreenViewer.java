package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Element;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public abstract class ScreenViewer<T> {
    public <E extends Element> void drawElements(GUI gui, List<E> elementList) {
        for(E element: elementList) {
            drawElement(gui, element);
        }
    }
    public void drawElement(GUI gui, Element element) {
        gui.drawImage(element.getPosition(), element.getName());
    }
    public abstract void draw(T model, GUI gui) throws IOException, URISyntaxException, InterruptedException;
}

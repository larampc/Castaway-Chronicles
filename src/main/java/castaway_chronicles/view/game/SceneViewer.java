package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.elements.Element;

import java.util.List;

public abstract class SceneViewer {
    public <E extends Element> void drawElements(GUI gui, List<E> elementList) {
        for(E element: elementList) {
            drawElement(gui, element);
        }
    }
    public void drawElement(GUI gui, Element element) {
        gui.drawImage(element.getPosition(), element.getName());
    }
}

package castaway_chronicles.view.game;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.elements.Element;

public class ImageViewer {
    public void draw(GUI gui, Element element, Images images) {
        gui.drawImage(element.getPosition(), images.getImage(element.getName()));
    }
}

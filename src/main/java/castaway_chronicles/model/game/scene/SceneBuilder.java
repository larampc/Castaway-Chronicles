package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;

import java.util.List;

public abstract class SceneBuilder {
    //create different builders?
    public Scene createScene(String type) {
        Scene scene = SceneFactory.getScene(type, getBackground(), getInteractables(), getVisibleInteractables());
        if(scene instanceof Location && hasMainChar()) ((Location) scene).enteredLocation();
        return scene;
    }
    protected abstract Background getBackground();

    protected abstract List<Interactable> getInteractables();
    protected abstract List<Interactable> getVisibleInteractables();

    protected abstract boolean hasMainChar();
}

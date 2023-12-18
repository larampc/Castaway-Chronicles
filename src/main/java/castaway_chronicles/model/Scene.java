package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Scene {
    private final HashMap<String, Interactable> interactables;
    private final Background background;
    private final HashMap<String, Interactable> visibleInteractables;

    public Scene(Background background, HashMap<String, Interactable> interactables, HashMap<String, Interactable> visibleInteractables) {
        this.interactables = interactables;
        this.background = background;
        this.visibleInteractables = visibleInteractables;
    }
    public List<Interactable> getInteractables() {return new ArrayList<>(interactables.values());}
    public Background getBackground() {return background;}

    public List<Interactable> getVisibleInteractables() {
        return new ArrayList<>(visibleInteractables.values());
    }
    public Interactable getInteractable(String name) {return interactables.get(name);}
    public void setVisible(String name){
        visibleInteractables.put(name, interactables.get(name));
    }
    public void setInvisible(String name){
        visibleInteractables.remove(name);
    }
}

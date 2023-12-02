package castaway_chronicles.states;

import castaway_chronicles.controller.Controller;
import castaway_chronicles.controller.game.BackpackController;
import castaway_chronicles.controller.game.LocationController;
import castaway_chronicles.controller.game.MapController;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.Map;
import castaway_chronicles.model.game.scene.Scene;
import castaway_chronicles.view.Viewer;
import castaway_chronicles.view.game.BackpackViewer;
import castaway_chronicles.view.game.LocationViewer;
import castaway_chronicles.view.game.MapViewer;

public class GameState extends State<Scene>{
    public GameState(Scene model) {
        super(model);
    }

    @Override
    protected Viewer<Scene> getViewer() {
        if (getModel() instanceof Location) return new LocationViewer((Location) getModel());
        if (getModel() instanceof Map) return new MapViewer((Map) getModel());
        if (getModel() instanceof Backpack) return new BackpackViewer((Backpack) getModel());
        return null;
    }

    @Override
    protected Controller<Scene> getController() {
        if (getModel() instanceof Location) return new LocationController((Location) getModel());
        if (getModel() instanceof Map) return new MapController((Map) getModel());
        if (getModel() instanceof Backpack) return new BackpackController((Backpack) getModel());
        return null;
    }
}

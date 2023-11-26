package castaway_chronicles.model.game;

import castaway_chronicles.model.game.elements.MainChar;
import castaway_chronicles.model.game.scene.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Map map;
    private final Backpack backpack;
    private final List<Location> locations = new ArrayList<>();
    private final MainChar mainchar;

    public Game() throws IOException {
        SceneLoader mapBuilder = new SceneLoader("Map","Map");
        this.map = (Map) mapBuilder.createScene();
        SceneLoader backpackBuilder = new SceneLoader("Backpack","Backpack");
        this.backpack = (Backpack) backpackBuilder.createScene();
        for (Integer i = 1; i <= 2; i++) {
            SceneLoader locationsBuilder = new SceneLoader("Scene"+i.toString(),"Location");
            this.locations.add((Location) locationsBuilder.createScene());
        }
        this.mainchar = new MainChar(120,120, 10, 10, "MainChar");
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public MainChar getMainchar() {
        return mainchar;
    }

    public Map getMap() {
        return map;
    }

    public List<Location> getLocations() {
        return locations;
    }
}

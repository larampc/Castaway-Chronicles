package castaway_chronicles.model.game;

import castaway_chronicles.model.game.scene.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Game {
    private final Map map;
    private final Backpack backpack;
    private final HashMap<String, Location> locations = new HashMap<>();
    private String currentLocation;
    private SCENE currentScene;

    public Game() throws IOException {
        SceneLoader mapBuilder = new SceneLoader("Map", "Map");
        this.map = (Map) mapBuilder.createScene();
        SceneLoader backpackBuilder = new SceneLoader("Backpack", "Backpack");
        this.backpack = (Backpack) backpackBuilder.createScene();
        URL resource = getClass().getClassLoader().getResource("Scenes/Location/Locations.txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        for (String line; (line = br.readLine()) != null; ){
            String[] s = line.split(" ",-1);
            if (s.length == 2) {currentLocation = s[0];}
            SceneLoader locationsBuilder = new SceneLoader(s[0], "Location");
            this.locations.put(s[0], (Location) locationsBuilder.createScene());
        }
        currentScene = SCENE.LOCATION;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public Map getMap() {
        return map;
    }
    public Location getLocation(String name) {
        return locations.get(name);
    }
    public String getCurrentLocation() {
        return currentLocation;
    }
    public SCENE getScene() {
        return currentScene;
    }
    public void setCurrentScene(String scene) {
        currentScene = SCENE.valueOf(scene);
    }
    public void setCurrentLocation(String name) {
        currentLocation = name;
    }
    public enum SCENE{BACKPACK, MAP, LOCATION}
}

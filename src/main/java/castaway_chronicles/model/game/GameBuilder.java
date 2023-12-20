package castaway_chronicles.model.game;

import castaway_chronicles.ResourceManager;
import castaway_chronicles.model.game.scene.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GameBuilder {
    public Game createGame(boolean saved) throws IOException {
        Game game = new Game();
        String dir = saved? "Scenes_saved":"Scenes";
        game.setMap(createMap(dir));
        game.setBackpack(createBackpack(dir));
        game.setLocations(createLocations(dir));
        game.setCurrentScene(Game.SCENE.LOCATION);
        game.setCurrentLocation(getCurrentLocation(dir));
        game.setPauseMenu(createPauseMenu());
        return game;
    }

    private String getCurrentLocation(String dir) {
        ResourceManager resourceManager = ResourceManager.getInstance();
        resourceManager.setPath(dir + "/Locations.txt");
        List<String> lines = resourceManager.readCurrentTimeResourceFile();
        for (String line : lines){
            String[] s = line.split(" ",-1);
            if (s.length == 2) { return s[0];}
        }
        return "";
    }

    public Map createMap(String dir) throws IOException {
        SceneLoader mapBuilder = new SceneLoader(dir, "Map", "Map");
        return (Map) mapBuilder.createScene();
    }
    public Backpack createBackpack(String dir) throws IOException {
        SceneLoader backpackBuilder = new SceneLoader(dir, "Backpack", "Backpack");
        return (Backpack) backpackBuilder.createScene();
    }
    public HashMap<String, Location> createLocations(String dir) throws IOException {
        HashMap<String, Location> locations = new HashMap<>();
        ResourceManager resourceManager = ResourceManager.getInstance();
        resourceManager.setPath(dir + "/Locations.txt");
        List<String> lines = resourceManager.readCurrentTimeResourceFile();
        for (String line : lines ){
            String[] s = line.split(" ",-1);
            SceneLoader locationsBuilder = new SceneLoader(dir, s[0], "Location");
            locations.put(s[0], (Location) locationsBuilder.createScene());
        }
        return locations;
    }
    public PauseMenu createPauseMenu() {
        return new PauseMenu();
    }
}

package castaway_chronicles.model.game;

import castaway_chronicles.model.game.scene.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class GameBuilder {
    public Game createGame() throws IOException {
        Game game = new Game();
        game.setMap(createMap());
        game.setBackpack(createBackpack());
        game.setLocations(createLocations());
        game.setCurrentScene("LOCATION");
        game.setCurrentLocation(getCurrentLocation());
        game.setPauseMenu(createPauseMenu());
        return game;
    }

    private String getCurrentLocation() throws IOException {
        URL resource = getClass().getClassLoader().getResource("Scenes/Location/Locations.txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        for (String line; (line = br.readLine()) != null; ){
            String[] s = line.split(" ",-1);
            if (s.length == 2) { return s[0];}
        }
        return "";
    }

    public Map createMap() throws IOException {
        SceneLoader mapBuilder = new SceneLoader("Map", "Map");
        return (Map) mapBuilder.createScene();
    }
    public Backpack createBackpack() throws IOException {
        SceneLoader backpackBuilder = new SceneLoader("Backpack", "Backpack");
        return (Backpack) backpackBuilder.createScene();
    }
    public HashMap<String, Location> createLocations() throws IOException {
        HashMap<String, Location> locations = new HashMap<>();
        URL resource = getClass().getClassLoader().getResource("Scenes/Location/Locations.txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        for (String line; (line = br.readLine()) != null; ){
            String[] s = line.split(" ",-1);
            SceneLoader locationsBuilder = new SceneLoader(s[0], "Location");
            locations.put(s[0], (Location) locationsBuilder.createScene());
        }
        return locations;
    }
    public PauseMenu createPauseMenu() {
        return new PauseMenu();
    }
}

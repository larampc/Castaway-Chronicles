package castaway_chronicles.model.game;

import castaway_chronicles.model.game.scene.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashMap;

public class GameBuilder {
    public Game createGame(boolean saved) throws IOException {
        Game game = new Game();
        String dir = saved? "Scenes_saved":"Scenes";
        game.setMap(createMap(dir));
        game.setBackpack(createBackpack(dir));
        game.setLocations(createLocations(dir));
        game.setCurrentScene("LOCATION");
        game.setCurrentLocation(getCurrentLocation(dir));
        game.setPauseMenu(createPauseMenu());
        return game;
    }

    private String getCurrentLocation(String dir) throws IOException {
        File f = new File(Paths.get("").toAbsolutePath() + "/src/main/resources/" + dir + "/Locations.txt");
        BufferedReader br = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8));
        for (String line; (line = br.readLine()) != null; ){
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
        File f = new File(Paths.get("").toAbsolutePath() + "/src/main/resources/" + dir + "/Locations.txt");
        BufferedReader br = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8));
        for (String line; (line = br.readLine()) != null; ){
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

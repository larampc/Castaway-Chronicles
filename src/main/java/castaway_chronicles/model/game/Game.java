package castaway_chronicles.model.game;

import castaway_chronicles.model.game.elements.InteractableWithText;
import castaway_chronicles.model.game.scene.*;

import java.util.HashMap;

public class Game {
    private Map map;
    private Backpack backpack;
    private HashMap<String, Location> locations = new HashMap<>();
    private PauseMenu pauseMenu;
    private Location currentLocation;
    private SCENE currentScene;
    private final TextDisplay textDisplay = new TextDisplay();

    public Game() {}
    public Backpack getBackpack() {
        return backpack;
    }
    public PauseMenu getPauseMenu() {return pauseMenu;}
    public Map getMap() {
        return map;
    }

    public HashMap<String, Location> getLocations() {
        return locations;
    }

    public Location getLocation(String name) {
        return locations.get(name);
    }
    public Location getCurrentLocation() {
        return currentLocation;
    }
    public void setMap(Map map) {
        this.map = map;
    }
    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }
    public void setLocations(HashMap<String, Location> locations) {
        this.locations = locations;
    }
    public void setPauseMenu(PauseMenu pauseMenu) {
        this.pauseMenu = pauseMenu;
    }
    public SCENE getScene() {
        return currentScene;
    }
    public void setCurrentScene(SCENE scene) {
        currentScene = scene;
    }
    public void setCurrentLocation(String name) {
        currentLocation = getLocation(name);
    }
    public TextDisplay getTextDisplay() {return textDisplay;}
    public void setTextDisplay(InteractableWithText element) {
        textDisplay.activateTextBox(element);
    }
    public enum SCENE{BACKPACK, MAP, LOCATION, PAUSE}
}

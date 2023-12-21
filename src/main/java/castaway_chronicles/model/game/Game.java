package castaway_chronicles.model.game;

import castaway_chronicles.model.InteractableWithText;
import castaway_chronicles.model.game.scene.*;

import java.util.HashMap;

public class Game {
    private final Map map;
    private final Backpack backpack;
    private final HashMap<String, Location> locations;
    private final PauseMenu pauseMenu;
    private Location currentLocation;
    private SCENE currentScene;
    private final TextBox textBox = new TextBox();

    public Game(Map map, Backpack backpack, HashMap<String, Location> locations, PauseMenu pauseMenu, String currentLocation) {
        this.map = map;
        this.backpack = backpack;
        this.pauseMenu = pauseMenu;
        this.locations = locations;
        this.currentLocation = locations.get(currentLocation);
        this.currentScene = SCENE.LOCATION;
    }
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
    public SCENE getScene() {
        return currentScene;
    }
    public void setCurrentScene(SCENE scene) {
        currentScene = scene;
    }
    public void setCurrentLocation(String name) {
        currentLocation = getLocation(name);
    }
    public TextBox getTextBox() {return textBox;}
    public void setTextBox(InteractableWithText interactable) {
        textBox.activateTextBox(interactable);
    }
    public enum SCENE{BACKPACK, MAP, LOCATION, PAUSE}
}

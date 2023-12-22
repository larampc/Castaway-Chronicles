package castaway_chronicles.controller.game;

import castaway_chronicles.ResourceManager;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.Interactable;
import castaway_chronicles.model.game.gameElements.MainChar;
import castaway_chronicles.model.game.gameElements.NPC;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.Map;

public class GameSaver {
    private final Game game;
    private final ResourceManager resourceManager = ResourceManager.getInstance();

    public GameSaver(Game game) {
        this.game = game;
        getResourceManager().createResourceDir("Scenes_saved");
    }
    public void emptySave() {
        getResourceManager().deleteResourceFileContent("Scenes_saved");
    }
    public void saveGame() {
        emptySave();
        saveBackpack();
        saveLocations();
        saveMap();
    }
    public void saveBackpack() {
        Background bg = game.getBackpack().getBackground();
        getResourceManager().writeToFile("Scenes_saved/Backpack.txt", "B " + bg.getName() + " " + bg.getPosition().getX() + " " + bg.getPosition().getY() + " " + bg.getWidth() + " " + bg.getHeight() + "\n");
        for (Interactable i: game.getBackpack().getInteractables()) {
            String visible = game.getBackpack().getVisibleInteractables().contains(i)? " V":"";
            getResourceManager().writeToFile("Scenes_saved/Backpack.txt","I BItem "+ i.getName() + " " + i.getPosition().getX() + " " + i.getPosition().getY() + " " + i.getWidth() + " " + i.getHeight() + visible + "\n");
        }
    }

    public void saveLocations() {
        for (String l: game.getLocations().keySet()) {
            getResourceManager().writeToFile("Scenes_saved/Locations.txt",l);
            if (game.getLocation(l) == game.getCurrentLocation()) {
                getResourceManager().writeToFile("Scenes_saved/Locations.txt"," S");
            }
            getResourceManager().writeToFile("Scenes_saved/Locations.txt","\n");
            saveLocation(l, game.getLocation(l));
        }
    }

    public void saveLocation(String name, Location location) {
        Background bg = location.getBackground();
        String loopable = bg.isLoopable()?" L":"";
        getResourceManager().writeToFile("Scenes_saved/" + name + ".txt",
                "B " + bg.getName() + " " + bg.getPosition().getX() + " " + bg.getPosition().getY() +
                        " " + bg.getWidth() + " " + bg.getHeight() + loopable + "\n");
        for (Interactable i: location.getInteractables()) {
            String npcState = i instanceof NPC ? " " +((NPC) i).getState() : "";
            String visible = location.getVisibleInteractables().contains(i)? " V":"";
            getResourceManager().writeToFile("Scenes_saved/" + name + ".txt",
                    "I "+ i.getClass().getSimpleName() + " " + i.getName() + " " + i.getPosition().getX() + " "
                            + i.getPosition().getY() + " " + i.getWidth() + " " + i.getHeight() + npcState + visible + "\n");
        }
        if (location.getMainChar() != null) {
            MainChar mc = location.getMainChar();
            getResourceManager().writeToFile("Scenes_saved/" + name + ".txt",
                    "M " + mc.getPosition().getX() + " " + mc.getPosition().getY() + " "
                            + mc.getWidth() + " " + mc.getHeight() + "\n");
        }
    }
    public void saveMap() {
        Map map = game.getMap();
        Background bg = map.getBackground();
        getResourceManager().writeToFile("Scenes_saved/Map.txt",
                "B " + bg.getName() + " " + bg.getPosition().getX() + " " + bg.getPosition().getY() + " " + bg.getWidth() + " " + bg.getHeight() + "\n");
        for (Interactable i: map.getInteractables()) {
            String visible = map.getVisibleInteractables().contains(i)? " V":"";
            getResourceManager().writeToFile("Scenes_saved/Map.txt",
                    "I "+ i.getClass().getSimpleName() + " " + i.getName() + " " + i.getPosition().getX() + " "
                            + i.getPosition().getY() + " " + i.getWidth() + " " + i.getHeight() + visible + "\n");
        }
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }
}

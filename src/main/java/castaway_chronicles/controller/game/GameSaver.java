package castaway_chronicles.controller.game;

import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.MainChar;
import castaway_chronicles.model.game.elements.NPC;
import castaway_chronicles.model.game.scene.Location;
import castaway_chronicles.model.game.scene.Map;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameSaver {
    private final Game game;
    private final File toSave;
    public GameSaver(Game game) {
        this.game = game;
        this.toSave = new File(Paths.get("").toAbsolutePath()+"/src/main/resources/Scenes_saved");
        toSave.mkdirs();
    }
    public void emptySave() {
        File[] allContents = toSave.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                file.delete();
            }
        }
    }
    public void saveGame() throws IOException {
        emptySave();
        saveBackpack();
        saveLocations();
        saveMap();
    }
    public void saveBackpack() throws IOException {
        File backpack = new File(toSave.getAbsolutePath()+"/Backpack.txt");
        Writer writer = Files.newBufferedWriter(Paths.get(backpack.getAbsolutePath()));
        Background bg = game.getBackpack().getBackground();
        writer.write("B " + bg.getName() + " " + bg.getPosition().getX() + " " + bg.getPosition().getY() + " " + bg.getWidth() + " " + bg.getHeight() + "\n");
        for (Interactable i: game.getBackpack().getInteractables()) {
            String visible = game.getBackpack().getVisibleInteractables().contains(i)? " V":"";
            writer.write("I BItem "+ i.getName() + " " + i.getPosition().getX() + " " + i.getPosition().getY() + " " + i.getWidth() + " " + i.getHeight() + visible + "\n");
        }
        writer.close();
    }

    public void saveLocations() throws IOException {
        File locations = new File(toSave.getAbsolutePath()+"/Locations.txt");
        Writer writer = Files.newBufferedWriter(Paths.get(locations.getAbsolutePath()));
        for (String l: game.getLocations().keySet()) {
            writer.write(l);
            if (game.getLocation(l) == game.getCurrentLocation()) {
                writer.write(" S");
            }
            writer.write("\n");
            saveLocation(l, game.getLocation(l));
        }
        writer.close();
    }

    public void saveLocation(String name, Location location) throws IOException {
        File file = new File(toSave.getAbsolutePath()+"/" + name + ".txt");
        Writer writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()));
        Background bg = location.getBackground();
        String loopable = bg.isIsloopable()?" L":"";
        writer.write("B " + bg.getName() + " " + bg.getPosition().getX() + " " + bg.getPosition().getY() + " " + bg.getWidth() + " " + bg.getHeight() + loopable + "\n");
        for (Interactable i: location.getInteractables()) {
            String npcState = i instanceof NPC ? " " +((NPC) i).getState() : "";
            String visible = location.getVisibleInteractables().contains(i)? " V":"";
            writer.write("I "+ i.getClass().getSimpleName() + " " + i.getName() + " " + i.getPosition().getX() + " " + i.getPosition().getY() + " " + i.getWidth() + " " + i.getHeight() + npcState + visible + "\n");
        }
        if (location.getMainChar() != null) {
            MainChar mc = location.getMainChar();
            writer.write("M " + mc.getPosition().getX() + " " + mc.getPosition().getY() + " " + mc.getWidth() + " " + mc.getHeight() + "\n");
        }
        writer.close();
    }
    public void saveMap() throws IOException {
        File backpack = new File(toSave.getAbsolutePath()+"/Map.txt");
        Writer writer = Files.newBufferedWriter(Paths.get(backpack.getAbsolutePath()));
        Map map = game.getMap();
        Background bg = map.getBackground();
        writer.write("B " + bg.getName() + " " + bg.getPosition().getX() + " " + bg.getPosition().getY() + " " + bg.getWidth() + " " + bg.getHeight() + "\n");
        for (Interactable i: map.getInteractables()) {
            String visible = map.getVisibleInteractables().contains(i)? " V":"";
            writer.write("I "+ i.getClass().getSimpleName() + " " + i.getName() + " " + i.getPosition().getX() + " " + i.getPosition().getY() + " " + i.getWidth() + " " + i.getHeight() + visible + "\n");
        }
        writer.close();}
}

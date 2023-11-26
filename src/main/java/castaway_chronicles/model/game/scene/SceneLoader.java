package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.Item;
import castaway_chronicles.model.game.elements.NPC;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SceneLoader {
    private final List<String> lines;
    private final String type;

    public SceneLoader(String filename, String type) throws IOException {
        URL resource = getClass().getClassLoader().getResource("Scenes/" + type + "/" + filename + ".txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        lines = readLines(br);
        this.type = type;
    }
    public Scene createScene() {
        Scene scene = SceneFactory.getScene(type, getBackground(), getInteractables(), getVisibleInteractables());
        if(scene instanceof Location && hasMainChar()) ((Location) scene).enteredLocation();
        return scene;
    }
    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }

    protected Background getBackground() {
        for (String line : lines) {
            if (line.charAt(0) == 'B') {
                String[] s = line.split(" ", -1);
                if(s.length < 5) return null;
                int x = Integer.parseInt(s[2]), y = Integer.parseInt(s[3]), w = Integer.parseInt(s[4]), h = Integer.parseInt(s[5]);
                return new Background(x, y, w, h, s[1]);
            }
        }
        return null;
    }

    protected List<Interactable> getInteractables() {
        List<Interactable> interactables = new ArrayList<>();
        for (String line : lines) {
            if (line.charAt(0) == 'I') {
                String[] s = line.split(" ",-1);
                String name = s[2];
                String type = s[1];
                int x = Integer.parseInt(s[3]), y = Integer.parseInt(s[4]), w = Integer.parseInt(s[5]), h = Integer.parseInt(s[6]);
                //FACTORY?
                if (type.equalsIgnoreCase("NPC")) interactables.add(new NPC(x, y, w, h, name));
                if (type.equalsIgnoreCase("ITEM")) interactables.add(new Item(x, y, w, h, name));
            }
        }
        return interactables;
    }

    protected List<Interactable> getVisibleInteractables() {
        List<Interactable> visibleInteractables = new ArrayList<>();
        for (String line : lines) {
            if (line.charAt(0) == 'I' && line.charAt(line.length() - 1) == 'V') {
                String[] s = line.split(" ", -1);
                String name = s[2];
                String type = s[1];
                int x = Integer.parseInt(s[3]), y = Integer.parseInt(s[4]), w = Integer.parseInt(s[5]), h = Integer.parseInt(s[6]);
                //FACTORY?
                if (type.equalsIgnoreCase("NPC")) visibleInteractables.add(new NPC(x, y, w, h, name));
                if (type.equalsIgnoreCase("ITEM")) visibleInteractables.add(new Item(x, y, w, h, name));
            }
        }
        return visibleInteractables;
    }

    protected boolean hasMainChar() {
        for(String line : lines){
            if(line.equals("M")) return true;
        }
        return false;
    }

}

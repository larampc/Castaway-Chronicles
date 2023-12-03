package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SceneLoader {
    private final List<String> lines;
    private final HashMap<String, Interactable> interactables = new HashMap<>();
    private final HashMap<String, Interactable> visibleInteractables = new HashMap<>();
    private final String type;

    public SceneLoader(String filename, String type) throws IOException {
        URL resource = getClass().getClassLoader().getResource("Scenes/" + type + "/" + filename + ".txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        lines = readLines(br);
        this.type = type;
        getInteractables();
    }
    public Scene createScene() {
        Scene scene = SceneFactory.getScene(type, getBackground(), interactables, visibleInteractables, getMainChar());
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
                if(s.length < 6) return null;
                int x = Integer.parseInt(s[2]), y = Integer.parseInt(s[3]), w = Integer.parseInt(s[4]), h = Integer.parseInt(s[5]);
                return new Background(x, y, w, h, s[1]);
            }
        }
        return null;
    }

    protected void getInteractables() throws IOException {
        for (String line : lines) {
            if (line.charAt(0) == 'I') {
                String[] s = line.split(" ",-1);
                String name = s[2];
                String type = s[1];
                int x = Integer.parseInt(s[3]), y = Integer.parseInt(s[4]), w = Integer.parseInt(s[5]), h = Integer.parseInt(s[6]);
                Interactable interactable = InteractableFactory.getInteractable(type,x,y,w,h,name);
                if (s.length == 8) {
                    visibleInteractables.put(name,interactable);
                }
                interactables.put(name,interactable);
            }
        }
        if (type.equalsIgnoreCase("Location")) getIcons();
    }


    protected MainChar getMainChar() {
        if (type.equalsIgnoreCase( "Location")) {
            for(String line : lines){
                if (line.charAt(0) == 'M') {
                    String[] s = line.split(" ",-1);
                    int x = Integer.parseInt(s[1]), y = Integer.parseInt(s[2]), w = Integer.parseInt(s[3]), h = Integer.parseInt(s[4]);
                    //FACTORY?
                    return new MainChar(x,y,w,h, "standing_right");
                }
            }
        }
        return null;
    }
    protected void getIcons() throws IOException {
        URL resource = getClass().getClassLoader().getResource("Scenes/Location/Icons.txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        List<String> icons = readLines(br);
        for (String line : icons) {
            String[] s = line.split(" ",-1);
            String name = s[0];
            int x = Integer.parseInt(s[1]), y = Integer.parseInt(s[2]), w = Integer.parseInt(s[3]), h = Integer.parseInt(s[4]);
            visibleInteractables.put(name, new Icon(x, y, w,h,name));
            interactables.put(name, new Icon(x,y,w,h,name));
        }
    }
}

package castaway_chronicles.model.game.scene;

import castaway_chronicles.ResourceManager;
import castaway_chronicles.model.Scene;
import castaway_chronicles.model.game.elements.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class SceneLoader {
    private final List<String> lines;
    private final HashMap<String, Interactable> interactables = new HashMap<>();
    private final HashMap<String, Interactable> visibleInteractables = new HashMap<>();
    private final String type;

    public SceneLoader(String dir, String filename, String type) throws IOException {
        ResourceManager resourceManager = ResourceManager.getInstance();
        lines = resourceManager.readCurrentTimeResourceFile(dir + "/" + filename + ".txt");
        //SETTER PARA LINES PARA TESTE
        this.type = type;
        getInteractables();
    }
    public Scene createScene() {
        return SceneFactory.getScene(type, getBackground(), interactables, visibleInteractables, getMainChar());
    }

    protected Background getBackground() {
        for (String line : lines) {
            if (line.charAt(0) == 'B') {
                String[] s = line.split(" ", -1);
                if(s.length < 6) return null;
                int x = Integer.parseInt(s[2]), y = Integer.parseInt(s[3]), w = Integer.parseInt(s[4]), h = Integer.parseInt(s[5]);
                boolean loopable = s.length == 7;
                return new Background(x, y, w, h, s[1], loopable);
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
                int x = Integer.parseInt(s[3]), y = Integer.parseInt(s[4]), w = Integer.parseInt(s[5]), h = Integer.parseInt(s[6]), state = 0;
                if (type.equalsIgnoreCase("npc")) state = Integer.parseInt(s[7]);
                Interactable interactable = InteractableFactory.getInteractable(type,x,y,w,h,name,state);
                if (line.charAt(line.length()-1)=='V') {
                    visibleInteractables.put(name,interactable);
                }
                interactables.put(name,interactable);
            }
        }
    }


    protected MainChar getMainChar() {
        if (type.equalsIgnoreCase( "Location")) {
            for(String line : lines){
                if (line.charAt(0) == 'M') {
                    String[] s = line.split(" ",-1);
                    int x = Integer.parseInt(s[1]), y = Integer.parseInt(s[2]), w = Integer.parseInt(s[3]), h = Integer.parseInt(s[4]);
                    return new MainChar(x,y,w,h, "standing_right");
                }
            }
        }
        return null;
    }
}

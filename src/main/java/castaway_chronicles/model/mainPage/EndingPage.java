package castaway_chronicles.model.mainPage;

import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.Interactable;
import castaway_chronicles.ResourceManager;
import castaway_chronicles.model.Scene;

import java.util.HashMap;
import java.util.List;

public class EndingPage extends Scene {
    private final HashMap<String, Integer> questionItem;
    public EndingPage(Background background, HashMap<String, Interactable> interactables, HashMap<String, Interactable> visibleInteractables, HashMap<String, Integer> questionItem) {
        super(background, interactables, visibleInteractables);
        this.questionItem = questionItem;
        updateAchievedEndings();
    }

    public void updateAchievedEndings() {
        ResourceManager resourceManager = ResourceManager.getInstance();
        if (resourceManager.notExistsCurrentTimeResourceFile("achieved_endings.txt")) return;
        List<String> lines = resourceManager.readCurrentTimeResourceFile("achieved_endings.txt");
        for (String line : lines ) {
            setInvisible("question" + questionItem.get(line));
            setVisible(line);
        }
    }
    public void reset() {
        for (Interactable interactable: getInteractables()) {
            if (interactable.getName().contains("question")) {
                setVisible(interactable.getName());
            }
            else setInvisible(interactable.getName());
        }
        setVisible("reset");
    }
}

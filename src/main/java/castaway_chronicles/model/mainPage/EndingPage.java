package castaway_chronicles.model.mainPage;

import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.Scene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashMap;

public class EndingPage extends Scene {
    private final HashMap<String, Integer> questionItem;
    public EndingPage(Background background, HashMap<String, Interactable> interactables, HashMap<String, Interactable> visibleInteractables, HashMap<String, Integer> questionItem) throws IOException {
        super(background, interactables, visibleInteractables);
        this.questionItem = questionItem;
        updateAchievedEndings();
    }

    public void updateAchievedEndings() throws IOException {
        File endingsFile = new File(Paths.get("").toAbsolutePath()+"/src/main/resources/achieved_endings.txt");
        if (!endingsFile.exists()) return;
        BufferedReader br = new BufferedReader(new FileReader(endingsFile, StandardCharsets.UTF_8));
        for (String line; (line = br.readLine()) != null; ) {
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

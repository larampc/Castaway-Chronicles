package castaway_chronicles.model.mainPage;

import castaway_chronicles.ResourceManager;
import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.Interactable;

import java.util.HashMap;
import java.util.List;

public class EndingPageBuilder {
    public static EndingPage createEndingPage() {
        Background background = new Background(0, 0, 200, 150, "EndingsMenu", false);
        HashMap<String, Interactable> endings = new HashMap<>();
        HashMap<String, Interactable> visibleEndings = new HashMap<>();
        HashMap<String, Integer> questionItem = new HashMap<>();
        ResourceManager resourceManager = ResourceManager.getInstance();
        List<String> lines = resourceManager.readCurrentTimeResourceFile("endings.txt");
        Integer count = 0;
        for (String line : lines){
            String[] s = line.split(" ", -1);
            if (!s[0].contains("question")) questionItem.put(s[0], count);
            if (s.length == 6) visibleEndings.put(s[0], new EndingItem(Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]), s[0]));
            endings.put(s[0], new EndingItem(Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]), s[0]));
            count++;
        }
        return new EndingPage(background, endings, visibleEndings, questionItem);
    }
}

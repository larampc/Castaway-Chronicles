package castaway_chronicles.model.mainPage;

import castaway_chronicles.model.game.gameElements.Background;
import castaway_chronicles.model.Interactable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class EndingPageBuilder {
    public EndingPage createEndingPage() throws IOException {
        Background background = new Background(0, 0, 200, 150, "EndingsMenu", false);
        HashMap<String, Interactable> endings = new HashMap<>();
        HashMap<String, Interactable> visibleEndings = new HashMap<>();
        HashMap<String, Integer> questionItem = new HashMap<>();
        URL resource = getClass().getClassLoader().getResource("endings.txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        Integer count = 0;
        for (String line; (line = br.readLine()) != null; ){
            String[] s = line.split(" ", -1);
            if (!s[0].contains("question")) questionItem.put(s[0], count);
            if (s.length == 6) visibleEndings.put(s[0], new EndingItem(Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]), s[0]));
            endings.put(s[0], new EndingItem(Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]), s[0]));
            count++;
        }
        return new EndingPage(background, endings, visibleEndings, questionItem);
    }
}

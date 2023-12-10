package castaway_chronicles.model.menu;

import castaway_chronicles.model.game.elements.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EndingPage {
    private final List<EndingItem> visibleEndings = new ArrayList<>();
    private final List<EndingItem> endings = new ArrayList<>();

    public EndingPage() throws IOException {
        URL resource = getClass().getClassLoader().getResource("endings.txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        for (String line; (line = br.readLine()) != null; ) {
            String[] s = line.split(" ", -1);
            if (s.length == 6) visibleEndings.add(new EndingItem(Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]), s[0]));
            else endings.add(new EndingItem(Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]), s[0]));
        }
        File endings = new File(Paths.get("").toAbsolutePath()+"/src/main/resources/achieved_endings.txt");
        br = new BufferedReader(new FileReader(endings, StandardCharsets.UTF_8));
        for (String line; (line = br.readLine()) != null; ) {
            int i = index(line);
            visibleEndings.remove(i);
            visibleEndings.add(i, getElement(line));
        }
    }
    private int index(String name) {
        int i = 0;
        for (Element e: endings) {
            if (e.getName().equals(name)) return i;
            i++;
        }
        return i;
    }
    private EndingItem getElement(String name) {
        for (EndingItem e: endings) {
            if (e.getName().equals(name)) return e;
        }
        return null;
    }
    public List<EndingItem> getVisibleEndings() {return visibleEndings;}
}

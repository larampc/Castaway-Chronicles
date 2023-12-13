package castaway_chronicles.model.game.elements;

import castaway_chronicles.model.SelectionPanel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ItemBackpack extends Interactable {
    private SelectionPanel itemOptions;
    private HashMap<String, String> optionCommand = new HashMap<>();
    private List<String> defaultAnswers = new ArrayList<>();
    private String description;
    public ItemBackpack(int x, int y, int w, int h, String name) throws IOException {
        super(x, y, w, h, name);
        getInfo();
    }
    public void getInfo() throws IOException {
        String[] s1 = getName().split("_",-1);
        URL resource = getClass().getClassLoader().getResource("BackpackItems/" + s1[0] + ".txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        List<String> lines = br.lines().collect(Collectors.toList());
        List<String> entries = new ArrayList<>();
        description = lines.get(0);
        for (int i = 1; i < lines.size(); i+=2) {
            String[] s = lines.get(i).split(" - ",-1);
            optionCommand.put(s[0], s[1]);
            defaultAnswers.add(lines.get(i+1));
            entries.add(s[0]);
        }
        itemOptions = new SelectionPanel(entries);
    }
    public String getAnswer() {
        return defaultAnswers.get(itemOptions.getCurrentEntry());
    }
    public SelectionPanel getItemOptions() {
        return itemOptions;
    }
    public String getCommand() {
        return optionCommand.get(itemOptions.getEntry(itemOptions.getCurrentEntry()));
    }
    public String getDescription() {return description;}
    public void setNameBackpack(String name) throws IOException {
        setName(name);
        getInfo();
    }
    public List<String> getEffects() throws IOException {
        String[] s = getName().split("_",-1);
        URL resource = getClass().getClassLoader().getResource("BackpackItems/" + s[0] + "_" + itemOptions.getEntry(itemOptions.getCurrentEntry()).replaceAll(" ", "").toLowerCase() + "_effects.txt");
        if (resource == null) return new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        return br.lines().collect(Collectors.toList());
    }
}

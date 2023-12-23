package castaway_chronicles.model.game.gameElements;

import castaway_chronicles.model.InteractableWithText;
import castaway_chronicles.model.SelectionPanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BackpackItem extends InteractableWithText {
    private final HashMap<String, String> optionCommand = new HashMap<>();
    private List<String> defaultAnswers;
    private String description;
    private boolean inHand = false;
    public BackpackItem(int x, int y, int w, int h, String name) {
        super(x, y, w, h, name);
        getInfo();
    }
    public void getInfo() {
        String[] s1 = getName().split("_",-1);
        List<String> lines = getResourceManager().readStaticResourceFile("BackpackItems/" + s1[0] + ".txt");
        List<String> entries = new ArrayList<>();
        description = lines.get(0);
        defaultAnswers = new ArrayList<>();
        for (int i = 1; i < lines.size(); i+=2) {
            String[] s = lines.get(i).split(" - ",-1);
            optionCommand.put(s[0], s[1]);
            defaultAnswers.add(lines.get(i+1));
            entries.add(s[0]);
        }
        setChoices(new SelectionPanel(entries));
    }
    public String getCommand() {return optionCommand.get(getChoices().getEntry(getChoices().getCurrentEntry()));}
    public void setNameBackpack(String name) {
        setName(name);
        getInfo();
    }
    @Override
    public List<String> getEffects() {
        String[] s = getName().split("_",-1);
        String path = "BackpackItems/" + s[0] + "_" +
                getChoices().getEntry(getChoices().getCurrentEntry()).replaceAll(" ", "").toLowerCase() +
                "_effects.txt";
        if (!getResourceManager().existsStaticResourceFile(path)) return Collections.emptyList();
        return getResourceManager().readStaticResourceFile(path);
    }
    @Override
    public String getText() {
        if (inHand) return defaultAnswers.get(getChoices().getCurrentEntry());
        return description;
    }
    public void setInHand(boolean inHand) {this.inHand = inHand;}

}

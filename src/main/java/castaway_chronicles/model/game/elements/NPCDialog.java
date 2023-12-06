package castaway_chronicles.model.game.elements;

import castaway_chronicles.model.SelectionPanel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NPCDialog {
    private final String name;
    private int line;
    private List<String> dialog;
    private SelectionPanel choices;
    private List<Integer> nextStates;
    private List<String> effects;
    public NPCDialog(int state, String name) throws IOException {
        this.name = name;
        init(state);
    }
    protected void init(int state) throws IOException {
        URL resource = getClass().getClassLoader().getResource("Dialog/" + name + state + ".txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        List<String> lines = br.lines().collect(Collectors.toList());
        line = 0;
        nextStates = new ArrayList<>();
        dialog = new ArrayList<>();
        int count = 0;
        for (String s: lines) {
            if (s.charAt(0) == '-') {
                count = Character.digit(s.charAt(1), 10);
                break;
            }
            dialog.add(s);
        }
        List<String> choices = new ArrayList<>();
        for (int i= dialog.size()+1; i < dialog.size() + 1 + count; i++) {
            choices.add(lines.get(i));
            nextStates.add(Integer.parseInt(lines.get(i+count)));
        }
        this.choices = new SelectionPanel(choices);
        readEffects(state);
    }
    public SelectionPanel getChoices() {
        return choices;
    }
    public void goToStateChoice() throws IOException {
        init(nextStates.get(choices.getCurrentEntry()));
    }
    public void goToState(int i) throws IOException {
        init(i);
    }
    public int getLine() { return line;}
    public void nextLine() {
        line++;
    }
    public int getMax() {return dialog.size()-1;}
    public void readEffects(int state) throws IOException {
        URL resource = getClass().getClassLoader().getResource("Dialog/effect_" + name + state + ".txt");
        effects = new ArrayList<>();
        if (resource!=null) {
            BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
            this.effects = br.lines().collect(Collectors.toList());
        }
    }
    public List<String> getEffects() {
        return effects;
    }
    public String getCurrentLine() {return dialog.get(line);}
}

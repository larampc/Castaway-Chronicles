package castaway_chronicles.model.game.gameElements;

import castaway_chronicles.model.InteractableWithText;
import castaway_chronicles.model.SelectionPanel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NPC extends InteractableWithText {
    private int state;
    private int line;
    private List<String> dialog;
    private List<Integer> nextStates;
    private List<String> effects;
    public NPC(int x, int y, int w, int h, String name, int initialState) throws IOException {
        super(x, y, w, h, name);
        this.state = initialState;
        init();
    }
    protected void init() throws IOException {
        URL resource = getClass().getClassLoader().getResource("Dialog/" + getName() + "/" + getName() + state + ".txt");
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
        setChoices(new SelectionPanel(choices));
        readEffects(state);
    }

    @Override
    public String getText() {
        return dialog.get(line);
    }

    public List<Integer> getNextStates() {
        return nextStates;
    }

    public void goToStateChoice() throws IOException {
        state = nextStates.get(getChoices().getCurrentEntry());
        init();
    }
    public void goToState(int state) throws IOException {
        this.state = state;
        init();
    }
    public boolean dialogEnded() { return line == dialog.size()-1;}
    public void nextLine() {
        line++;
    }
    public void readEffects(int state) throws IOException {
        URL resource = getClass().getClassLoader().getResource("Dialog/" + getName() + "/effect_" + getName() + state + ".txt");
        effects = new ArrayList<>();
        if (resource!=null) {
            BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
            this.effects = br.lines().collect(Collectors.toList());
        }
    }
    @Override
    public List<String> getEffects() {
        return effects;
    }
    public int getState() {return state;}
}

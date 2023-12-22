package castaway_chronicles.model.game.gameElements;

import castaway_chronicles.model.InteractableWithText;
import castaway_chronicles.ResourceManager;
import castaway_chronicles.model.SelectionPanel;

import java.util.ArrayList;
import java.util.List;

public class NPC extends InteractableWithText {
    private int state;
    private int line;
    private List<String> dialog;
    private List<Integer> nextStates;
    private List<String> effects;
    public NPC(int x, int y, int w, int h, String name, int initialState) {
        super(x, y, w, h, name);
        this.state = initialState;
        init();
    }
    protected void init() {
        ResourceManager resourceManager = ResourceManager.getInstance();
        List<String> lines = resourceManager
                .readStaticResourceFile("Dialog/" + getName() + "/" + getName() + state + ".txt");
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

    public void goToStateChoice() {
        state = nextStates.get(getChoices().getCurrentEntry());
        init();
    }
    public void goToState(int state) {
        this.state = state;
        init();
    }
    public boolean dialogEnded() { return line == dialog.size()-1;}
    public void nextLine() {
        line++;
    }
    public void readEffects(int state) {
        ResourceManager resourceManager = ResourceManager.getInstance();
        String path = "Dialog/" + getName() + "/effect_" + getName() + state + ".txt";
        if(resourceManager.existsStaticResourceFile(path)){
            effects = resourceManager.readStaticResourceFile(path);
        }
        else effects = new ArrayList<>();
    }
    @Override
    public List<String> getEffects() {
        return effects;
    }
    public int getState() {return state;}
}

package castaway_chronicles.model.game.elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NPCDialog {
    private int state;
    private int line;
    private int max;
    private int choices;
    private final String name;
    private List<String> lines;
    public NPCDialog(int state, int line, String name) throws IOException {
        this.state = state;
        this.line = line;
        this.name = name;
        this.choices = 0;
        setMaxChoices();
    }
    protected void setMaxChoices() throws IOException {
        URL resource = getClass().getClassLoader().getResource("Dialog/" + name + state + ".txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        lines = br.lines().collect(Collectors.toList());
        for (int i= 0; i < lines.size();i++) {
            if (lines.get(i).charAt(0) == '-') {
                max = i-1;
                choices = Character.digit(lines.get(i).charAt(1), 10);
            }
        }
        if (choices == 0) max = lines.size() -1;
    }
    public int getLine() { return line;}
    public int getFile() { return state;}
    public void setState(int state) throws IOException { this.state = state; this.line = 0; this.choices = 0; setMaxChoices();}
    public void nextLine() {
        if (this.line == max+choices+1) {
            this.line = this.line + 1 - choices;
        }
        else ++line;
    }
    public void previousLine() { if (this.line == max+2) {
        this.line = this.line + choices-1;
    }
    else --line;}
    public int getMax() {return max;}
    public int getChoices() {return choices;}
    public void goToStateChoice() throws IOException {
        setState(Character.digit(lines.get(line+choices).charAt(0), 10));
    }
    public void goToChoices() {
        line = max + 2;
    }
    public List<String> getEffects() throws IOException {
        List<String> effects = new ArrayList<>();
        URL resource = getClass().getClassLoader().getResource("Dialog/effect_" + name + state + ".txt");
        if (resource!=null) {
            BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
            effects = br.lines().collect(Collectors.toList());
        }
        return effects;
    }
}

package castaway_chronicles.model.game.elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class DialogState {
    private int state;
    private int line;
    private int max;
    private String name;
    public DialogState(int state,int line, String name) throws IOException {
        this.state = state;
        this.line = line;
        this.name = name;
        setMax();
    }
    protected void setMax() throws IOException {
        URL resource = getClass().getClassLoader().getResource("Dialog/" + name + state + ".txt");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8));
        List<String> lines = br.lines().collect(Collectors.toList());
        System.out.println(lines.size());
        this.max = lines.size();
    }
    public int getLine() { return line;}
    public int getFile() { return state;}
    public void setFile(int state) throws IOException { this.state = state; this.line = 0; setMax();}
    public void nextLine() { ++this.line;}
    public int getMax() {return max;}
}

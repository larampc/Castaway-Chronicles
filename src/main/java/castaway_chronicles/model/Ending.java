package castaway_chronicles.model;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class Ending {
    private final String name;
    private int current = 1;
    private int max = 0;
    public Ending(String name) throws URISyntaxException {this.name = name;setMax();}
    public void setMax() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("Endings/"+name);
        assert resource != null;
        File fontFile = new File(resource.toURI());
        max = Objects.requireNonNull(fontFile.list()).length;
    }
    public int getMax() {return max;}
    public int getCurrent() {
        return current;
    }

    public void setNext() {
        ++this.current;
    }
    public String getCurrentFrame() {
        return String.format("%04d", current);
//        return name + current;
    }
    public String getName() {return name;}
}

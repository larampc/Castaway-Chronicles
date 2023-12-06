package castaway_chronicles.model.game.scene;

public class Ending {
    private String name;
    private int current = 0;
    public Ending(String name) {this.name = name;}

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
    public String getCurrentFrame() {
        return name + current;
    }
    public String getName() {return name;}
}

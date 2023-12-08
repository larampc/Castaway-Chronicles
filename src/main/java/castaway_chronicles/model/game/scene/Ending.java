package castaway_chronicles.model.game.scene;

public class Ending {
    private String name;
    private int current = 1;
    public Ending(String name) {this.name = name;}

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
    public String getCurrentFrame() {
        return String.format("%04d", current);
//        return name + current;
    }
    public String getName() {return name;}
}

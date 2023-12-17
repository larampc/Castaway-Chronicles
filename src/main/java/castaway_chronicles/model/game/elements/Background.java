package castaway_chronicles.model.game.elements;

public class Background extends Element {
    private final boolean loopable;
    public Background(int x, int y, int w, int h, String name, boolean loopable) {
        super(x, y, w, h, name);
        this.loopable = loopable;
    }
    public boolean isLoopable() {return loopable;}
}

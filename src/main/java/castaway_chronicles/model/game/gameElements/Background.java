package castaway_chronicles.model.game.gameElements;

import castaway_chronicles.model.Element;

public class Background extends Element {
    private final boolean loopable;
    public Background(int x, int y, int w, int h, String name, boolean loopable) {
        super(x, y, w, h, name);
        this.loopable = loopable;
    }
    public boolean isLoopable() {return loopable;}
}

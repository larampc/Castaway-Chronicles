package castaway_chronicles.model.game.elements;

public class Background extends Element {
    private boolean isloopable;
    public Background(int x, int y, int w, int h, String name, boolean isloopable) {
        super(x, y, w, h, name);
        this.isloopable = isloopable;
    }
    public boolean isIsloopable() {return isloopable;}
}

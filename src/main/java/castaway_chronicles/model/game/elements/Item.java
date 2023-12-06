package castaway_chronicles.model.game.elements;

public class Item extends Interactable {
    private String type;
    private String action;
    public Item(int x, int y, int w, int h, String name) {
        super(x, y, w, h, name);
    }
}

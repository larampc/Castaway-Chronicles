package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.ItemBackpack;

public class BackpackSelection {
    private boolean isSelection;
    private ItemBackpack item;
    public BackpackSelection() {
        isSelection = false;
    }
    public boolean isSelection() {
        return isSelection;
    }
    public ItemBackpack getItem() {
        return item;
    }
    public void activate(ItemBackpack item) {
        isSelection = true;
        this.item = item;
    }
    public void deactivate() {
        isSelection = false;
        this.item  = null;
    }
}

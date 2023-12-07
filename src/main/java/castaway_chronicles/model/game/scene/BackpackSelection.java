package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.ItemBackpack;

public class BackpackSelection {
    private boolean isSelection;
    private boolean isDescription;
    private ItemBackpack item;
    public BackpackSelection() {
        isSelection = false;
        isDescription = false;
    }
    public boolean isSelection() {
        return isSelection;
    }
    public boolean isDescription() {
        return isDescription;
    }
    public ItemBackpack getItem() {
        return item;
    }
    public void activateSelection() {
        isDescription = false;
        isSelection = true;
    }
    public void deactivate() {
        isSelection = false;
        isDescription = false;
    }
    public void activateDescription(ItemBackpack item) {
        isDescription = true;
        this.item = item;
    }
}

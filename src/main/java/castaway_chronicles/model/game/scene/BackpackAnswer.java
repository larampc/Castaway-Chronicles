package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.game.elements.ItemBackpack;

public class BackpackAnswer {
    private boolean isActive;
    private ItemBackpack item;
    public BackpackAnswer() {
        isActive = false;
    }
    public boolean isActive() {return isActive;}
    public void deactivate() {isActive = false; item = null;}
    public void activate(ItemBackpack item) {this.item = item; isActive = true;}
    public ItemBackpack getItem() {return item;}
}

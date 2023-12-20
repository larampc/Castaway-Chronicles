package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.Background;

public class Menu  {
    private final Background background;
    private SelectionPanel selectionPanel;
    public Menu() {
        background = new Background(0, 0, 200, 150, "Menu", false);
    }
    public SelectionPanel getSelectionPanel() {
        return selectionPanel;
    }
    public Background getBackground() {
        return background;
    }
    public void setSelectionPanel(SelectionPanel selectionPanel) {
        this.selectionPanel = selectionPanel;
    }
}

package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.SelectionPanel;
import castaway_chronicles.model.game.elements.Background;

import java.util.Arrays;

public class PauseMenu {
    private final Background background;
    private final SelectionPanel selectionPanel;
    public PauseMenu() {
        selectionPanel = new SelectionPanel(Arrays.asList("Resume", "Save", "Menu", "Exit"));
        background = new Background(0, 0, 200, 150, "Menu", false);
    }

    public boolean isSelectedExit() {
        return selectionPanel.isSelected(3);
    }
    public boolean isSelectedResume() {
        return selectionPanel.isSelected(0);
    }
    public boolean isSelectedSave() {return selectionPanel.isSelected(1);}
    public boolean isSelectedMenu() {return selectionPanel.isSelected(2);}
    public SelectionPanel getSelectionPanel() {
        return selectionPanel;
    }
    public Background getBackground() {
        return background;
    }
}

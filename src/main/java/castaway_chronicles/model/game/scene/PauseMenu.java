package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.Menu;
import castaway_chronicles.model.SelectionPanel;

import java.util.Arrays;

public class PauseMenu extends Menu {
    public PauseMenu() {
        super();
        setSelectionPanel(new SelectionPanel(Arrays.asList("Resume", "Save", "Menu", "Exit")));
    }
    public boolean isSelectedExit() {
        return getSelectionPanel().isSelected(3);
    }
    public boolean isSelectedResume() {
        return getSelectionPanel().isSelected(0);
    }
    public boolean isSelectedSave() {return getSelectionPanel().isSelected(1);}
    public boolean isSelectedMenu() {return getSelectionPanel().isSelected(2);}
}

package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.SelectionPanel;

import java.util.Arrays;

public class PauseMenu extends SelectionPanel {

    public PauseMenu() {
        super(Arrays.asList("Resume", "Save", "Exit"));
    }

    public boolean isSelectedExit() {
        return isSelected(2);
    }

    public boolean isSelectedResume() {
        return isSelected(0);
    }

    public boolean isSelectedSave() {return isSelected(1);}

}

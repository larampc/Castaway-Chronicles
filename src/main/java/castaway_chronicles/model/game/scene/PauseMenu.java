package castaway_chronicles.model.game.scene;

import castaway_chronicles.model.SelectionPanel;

import java.util.Arrays;

public class PauseMenu extends SelectionPanel {

    public PauseMenu() {
        super(Arrays.asList("Resume", "Exit"));
    }

    public boolean isSelectedExit() {
        return isSelected(1);
    }

    public boolean isSelectedResume() {
        return isSelected(0);
    }

}

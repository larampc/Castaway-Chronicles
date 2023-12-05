package castaway_chronicles.model.menu;

import castaway_chronicles.model.SelectionPanel;

import java.util.Arrays;

public class MainMenu extends SelectionPanel {

    public MainMenu() {
        super(Arrays.asList("Start", "Exit"));
    }
    public boolean isSelectedExit() {
        return isSelected(1);
    }

    public boolean isSelectedStart() {
        return isSelected(0);
    }
}

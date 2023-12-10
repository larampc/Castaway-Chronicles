package castaway_chronicles.model.menu;

import castaway_chronicles.model.SelectionPanel;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class MainMenu extends SelectionPanel {

    public MainMenu() {
        super(Arrays.asList("Start", "Continue", "Exit"));
    }
    public boolean isSelectedExit() {
        return isSelected(2);
    }
    public boolean isSelectedStart() {
        return isSelected(0);
    }
    public boolean isSelectedContinue() {
        return isSelected(1);
    }
    public boolean canContinue() {
        Path n = Paths.get("");
        File f = new File(n.toAbsolutePath() + "/src/main/resources/Scenes_saved");
        if (!f.exists()) return false;
        return f.list().length > 0;
    }
}

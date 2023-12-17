package castaway_chronicles.model.mainPage;

import castaway_chronicles.model.SelectionPanel;
import castaway_chronicles.model.game.elements.Background;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

public class MainMenu {
    private final Background background;
    private final SelectionPanel selectionPanel;
    public MainMenu() {
        selectionPanel = new SelectionPanel(Arrays.asList("Start", "Exit", "Continue", "Endings"));
        this.background = new Background(0, 0, 200, 150, "Menu", false);
    }
    public boolean isSelectedExit() {
        return selectionPanel.isSelected(1);
    }
    public boolean isSelectedStart() {
        return selectionPanel.isSelected(0);
    }
    public boolean isSelectedContinue() {
        return selectionPanel.isSelected(2);
    }
    public boolean canContinue() {
        File f = new File(Paths.get("").toAbsolutePath() + "/src/main/resources/Scenes_saved");
        if (!f.exists()) return false;
        return f.list().length > 0;
    }
    public boolean isSelectedEndings() {return selectionPanel.isSelected(3);}
    public SelectionPanel getSelectionPanel() {
        return selectionPanel;
    }
    public Background getBackground() {
        return background;
    }
}

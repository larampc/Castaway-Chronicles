package castaway_chronicles.model.mainPage;

import castaway_chronicles.ResourceManager;
import castaway_chronicles.model.Menu;
import castaway_chronicles.model.SelectionPanel;

import java.util.Arrays;

public class MainMenu extends Menu {
    public MainMenu() {
        super();
        setSelectionPanel(new SelectionPanel(Arrays.asList("Start", "Exit", "Continue", "Endings")));
    }
    public boolean isSelectedExit() {
        return getSelectionPanel().isSelected(1);
    }
    public boolean isSelectedStart() {
        return getSelectionPanel().isSelected(0);
    }
    public boolean isSelectedContinue() {
        return getSelectionPanel().isSelected(2);
    }
    public boolean isSelectedEndings() {return getSelectionPanel().isSelected(3);}
    public boolean canContinue() {
        ResourceManager resourceManager = ResourceManager.getInstance();
        resourceManager.setPath("Scenes_saved");
        if(resourceManager.notExistsCurrentTimeResourceFile()) return false;
        return resourceManager.countFiles() > 0;
    }
}

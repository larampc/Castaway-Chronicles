package castaway_chronicles.model.game.scene;

import java.util.Arrays;
import java.util.List;

public class PauseMenu {
    private final List<String> entries;
    private int currentEntry = 0;

    public PauseMenu() {
        this.entries = Arrays.asList("Resume", "Exit");
    }

    public void nextEntry() {
        currentEntry++;
        if (currentEntry > this.entries.size() - 1)
            currentEntry = 0;
    }

    public void previousEntry() {
        currentEntry--;
        if (currentEntry < 0)
            currentEntry = this.entries.size() - 1;
    }

    public String getEntry(int i) {
        return entries.get(i);
    }
    public int getCurrentEntry() {return currentEntry;}
    public List<String> getEntries() {return entries;}
    public boolean isSelected(int i) {
        return currentEntry == i;
    }

    public boolean isSelectedExit() {
        return isSelected(1);
    }

    public boolean isSelectedResume() {
        return isSelected(0);
    }

    public int getNumberEntries() {
        return this.entries.size();
    }
}

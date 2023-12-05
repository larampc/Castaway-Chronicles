package castaway_chronicles.model;

import java.util.List;

public class SelectionPanel {
    private List<String> entries;
    private int currentEntry;
    public SelectionPanel(List<String> entries) {
        this.entries = entries;
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

    public int getNumberEntries() {
        return this.entries.size();
    }
}

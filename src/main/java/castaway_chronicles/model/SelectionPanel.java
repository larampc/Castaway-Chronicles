package castaway_chronicles.model;

import java.util.List;

public class SelectionPanel {
    private final List<String> entries;
    private int currentEntry = 0;
    public SelectionPanel(List<String> entries) {this.entries = entries;}
    public void nextEntry() {
        currentEntry++;
        if (currentEntry > this.entries.size() - 1) currentEntry = 0;
    }
    public void previousEntry() {
        currentEntry--;
        if (currentEntry < 0) currentEntry = this.entries.size() - 1;
    }
    public String getEntry(int entry) {
        try {return entries.get(entry);}
        catch (IndexOutOfBoundsException e) {return "";}
    }
    public int getCurrentEntry() {return currentEntry;}
    public List<String> getEntries() {return entries;}
    public boolean isSelected(int entry) {return currentEntry == entry;}
    public int getNumberEntries() {return this.entries.size();}
}

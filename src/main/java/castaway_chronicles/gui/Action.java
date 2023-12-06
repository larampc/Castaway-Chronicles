package castaway_chronicles.gui;

public abstract class Action {
    private final String type;
    public Action(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}

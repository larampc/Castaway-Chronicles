package castaway_chronicles.gui;

public abstract class Action {
    private final ACTION type;
    public Action(ACTION type) {
        this.type = type;
    }
    public ACTION getType() {
        return type;
    }
    public enum ACTION {UP, DOWN, LEFT, RIGHT, SELECT, ESCAPE, CLICK, NONE}
}

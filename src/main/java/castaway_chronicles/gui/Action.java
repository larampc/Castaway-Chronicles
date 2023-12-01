package castaway_chronicles.gui;

public abstract class Action<T> {
    private INFO type;
    public Action(String type) {
        this.type = INFO.valueOf(type);
    }
    public abstract T getInfo();

    public INFO getType() {
        return type;
    }

    enum INFO {CLICK, KEY}
}

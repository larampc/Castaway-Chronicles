package castaway_chronicles.gui;

import castaway_chronicles.model.Position;

public class ClickAction extends Action{
    private final Position position;
    public ClickAction(String type, Position position) {
        super(type);
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }
}

package castaway_chronicles.gui;

import castaway_chronicles.model.Position;

public class ClickAction extends Action<Position>{
    private Position position;
    public ClickAction(String type, Position position) {
        super(type);
        this.position = position;
    }
    @Override
    public Position getInfo() {
        return position;
    }
}

package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.Icon;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.Location;

import java.io.IOException;

public class MoveCommand implements Command{
    private Location location;
    private int offset;
    public MoveCommand(Location location, Position position) {
        this.location = location;
        this.offset = location.getMainChar().getPosition().getX() - position.getX() +20;
    }
    @Override
    public void execute() throws IOException {
        if (location.getBackground().getPosition().getX()+offset <= 0 && location.getBackground().getPosition().getX()+offset>=-location.getBackground().getWidth()+200) {
            location.getBackground().setPosition(location.getBackground().getPosition().getRight(offset));
            for (Interactable e : location.getInteractables()) {
                if (!(e instanceof Icon)) {
                    e.setPosition(e.getPosition().getRight(offset));
                }
            }
        }
    }
}

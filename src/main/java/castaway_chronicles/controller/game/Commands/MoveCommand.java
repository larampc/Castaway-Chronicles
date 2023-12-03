package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.game.elements.Icon;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.Location;

import java.io.IOException;

public class MoveCommand implements Command{
    private Location location;
    private int offset;
    public MoveCommand(Location location, int x) {
        this.location = location;
        this.offset = x;
    }
    @Override
    public void execute() throws IOException {
        if (location.getBackground().getPosition().getX()+offset <= 0 && location.getBackground().getPosition().getX()+offset>=-location.getBackground().getWidth()+200) {
            int next = (Character.digit(location.getMainChar().getName().charAt(4),10) % 4) + 1 ;
            location.getMainChar().setName("walk" + next + ((offset < 0) ? "_right" : "_left"));
            location.getBackground().setPosition(location.getBackground().getPosition().getRight(offset));
            for (Interactable e : location.getInteractables()) {
                if (!(e instanceof Icon)) {
                    e.setPosition(e.getPosition().getRight(offset));
                }
            }
        }
    }
}

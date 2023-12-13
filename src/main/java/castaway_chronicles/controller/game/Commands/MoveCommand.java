package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Icon;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.Location;

import java.io.IOException;

public class MoveCommand implements Command{
    private final Location location;
    private int offset;
    public MoveCommand(Location location, int x) {
        this.location = location;
        this.offset = x;
    }
    @Override
    public void execute() throws IOException {
        int next = (Character.digit(location.getMainChar().getName().charAt(4),10) % 4) + 1 ;
        location.getMainChar().setName("walk" + next + ((offset < 0) ? "_right" : "_left"));
        if(location.getBackground().isIsloopable()){
            if (location.getBackground().getPosition().getX() + offset > 0) {
                offset = 200 - location.getBackground().getWidth() + offset;
                location.getBackground().setPosition(new Position(offset,0));
            }
            else if (location.getBackground().getPosition().getX() + offset < 200 - location.getBackground().getWidth()) {
                location.getBackground().setPosition(new Position(offset,0));
                offset = offset - (200 - location.getBackground().getWidth());
            }
            else location.getBackground().setPosition(location.getBackground().getPosition().getRight(offset));
        }
        else location.getBackground().setPosition(location.getBackground().getPosition().getRight(offset));
        for (Interactable e : location.getInteractables()) {
            if (!(e instanceof Icon && (e.getName().equalsIgnoreCase("Map_icon") || e.getName().equalsIgnoreCase("Backpack_icon")))) {
                e.setPosition(e.getPosition().getRight(offset));
            }
        }
    }
}

package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.game.scene.Location;

public class StartTalkCommand implements Command {
    private final Location location;
    private final String name;
    public StartTalkCommand(Location location, String name) {
        this.location = location;
        this.name = name;
    }
    @Override
    public void execute() {
        location.setTextDisplay(name);
    }
}

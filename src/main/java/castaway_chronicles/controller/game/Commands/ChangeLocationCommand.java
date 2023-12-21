package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.game.Game;

public class ChangeLocationCommand implements Command {
    private final Game game;
    private final String name;
    public ChangeLocationCommand(Game game, String name) {
        this.game = game;
        this.name = name;
    }
    @Override
    public void execute() {
        game.setCurrentLocation(name);
    }
}

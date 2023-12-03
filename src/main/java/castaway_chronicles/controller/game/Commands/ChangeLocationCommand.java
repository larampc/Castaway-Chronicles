package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.game.Game;

public class ChangeLocationCommand implements Command {
    private Game game;
    private String name;
    public ChangeLocationCommand(Game game, String name) {
        this.game = game;
        this.name = name;
    }

    @Override
    public void execute() {
        game.setCurrentLocation(name);
    }
}

package castaway_chronicles.controller.Commands;

import castaway_chronicles.model.game.Game;

public class PickUpCommand implements Command {
    private final Game game;
    private final String name;
    public PickUpCommand(Game game, String name) {
        this.game = game;
        this.name = name;
    }
    @Override
    public void execute() {
        game.getCurrentLocation().setInvisible(name);
        game.getBackpack().setVisible(name+"_backpack");
    }
}

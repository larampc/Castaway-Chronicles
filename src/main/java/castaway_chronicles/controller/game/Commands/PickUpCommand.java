package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.game.Game;

public class PickUpCommand implements Command {
    private Game game;
    private String name;
    public PickUpCommand(Game game, String name) {
        this.game = game;
        this.name = name;
    }
    @Override
    public void execute() {
        game.getLocation(game.getCurrentLocation()).setInvisible(name);
        game.getBackpack().setVisible(name);
    }
}

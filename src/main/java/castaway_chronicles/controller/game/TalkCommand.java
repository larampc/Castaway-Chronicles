package castaway_chronicles.controller.game;

import castaway_chronicles.model.game.Game;

public class TalkCommand implements Command {
    private Game game;
    private String name;
    public TalkCommand(Game game, String name) {
        this.game = game;
        this.name = name;
    }
    @Override
    public void execute() {
        game.getLocation(game.getCurrentLocation()).activateDialog(name);
    }
}

package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.game.Game;

public class ChangeSceneCommand implements Command {
    private Game game;
    private String name;
    public ChangeSceneCommand(Game game, String name) {
        this.game = game;
        this.name = name;
    }
    @Override
    public void execute() {
        game.setCurrentScene(name);
    }
}

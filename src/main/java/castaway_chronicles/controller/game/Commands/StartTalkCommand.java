package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.InteractableWithText;

public class StartTalkCommand implements Command {
    private final Game game;
    private final String name;
    public StartTalkCommand(Game game, String name) {
        this.game = game;
        this.name = name;
    }
    @Override
    public void execute() {
        game.setTextDisplay((InteractableWithText) game.getCurrentLocation().getInteractable(name));
    }
}

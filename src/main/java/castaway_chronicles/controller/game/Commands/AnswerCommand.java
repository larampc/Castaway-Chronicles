package castaway_chronicles.controller.game.Commands;

import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.NPC;

import java.io.IOException;

public class AnswerCommand implements Command {
    private final Game game;
    public AnswerCommand(Game game) {
        this.game = game;
    }
    @Override
    public void execute() throws IOException {
        ((NPC) game.getTextDisplay().getInteractable()).goToStateChoice();
        game.getTextDisplay().setActiveChoice(false);
    }
}

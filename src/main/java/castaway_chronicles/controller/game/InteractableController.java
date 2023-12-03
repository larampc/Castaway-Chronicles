package castaway_chronicles.controller.game;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.PickUpCommand;
import castaway_chronicles.gui.Action;
import castaway_chronicles.gui.ClickAction;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.Item;
import castaway_chronicles.model.game.elements.NPC;

import java.io.IOException;

public class InteractableController {
    private Game model;
    private Interactable interactable;
    public InteractableController(Game model, Interactable interactable) {
        this.model = model;
        this.interactable = interactable;
    }

    public void step(Application application, Action action) throws IOException {
        CommandInvoker invoker = new CommandInvoker();
        if (interactable instanceof Item) {
            if (interactable.contains(((ClickAction)action).getPosition())) {
                PickUpCommand pickup = new PickUpCommand(model, interactable.getName());
                invoker.setCommand(pickup);
            }
        }
        if (interactable instanceof NPC) {
            new NPCController(model, (NPC) interactable).step(application, action);
        }
    }
}

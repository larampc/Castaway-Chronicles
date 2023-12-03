package castaway_chronicles.controller.game;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.TalkCommand;
import castaway_chronicles.gui.Action;
import castaway_chronicles.gui.ClickAction;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.scene.Location;

import java.io.IOException;

public class LocationController {
    private Game model;
    private Location location;
    public LocationController(Game model) {
        this.model = model;
        this.location = model.getLocation(model.getCurrentLocation());
    }

    public void step(Application application, Action action) throws IOException {
        CommandInvoker invoker = new CommandInvoker();
        if (location.getDialogState().isChoice()) {
            if (action.getType().equalsIgnoreCase("down")) {
                location.getDialogState().getNPCDialog().getState().nextLine();
            }
            if (action.getType().equalsIgnoreCase("up")) {
                location.getDialogState().getNPCDialog().getState().previousLine();
            }
        }
        else if (location.getDialogState().isDialog()) {
            if (action.getType().equalsIgnoreCase("select")) {
                TalkCommand talk = new TalkCommand(location);
                invoker.setCommand(talk);
            }
        }
        else if (action instanceof ClickAction) {
            for (Interactable e: location.getVisibleInteractables()) {
                new InteractableController(model, e).step(application, action);
            }
        }
        invoker.execute();
    }

}

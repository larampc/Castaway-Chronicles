package castaway_chronicles.controller.game;

import castaway_chronicles.Application;
import castaway_chronicles.gui.Action;
import castaway_chronicles.gui.ClickAction;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.game.elements.Item;
import castaway_chronicles.model.game.elements.NPC;

import java.io.IOException;

public class LocationController{
//    private InteractableController interactableController;
    private Game model;
    public LocationController(Game model) {
        this.model = model;
    }

    public void step(Application application, Action action) throws IOException {

        if (model.getLocation(model.getCurrentLocation()).isDialog()) {
            if (action.getType().equalsIgnoreCase("select")) {
                if (model.getLocation(model.getCurrentLocation()).getNPCDialog().getState().getLine() == model.getLocation(model.getCurrentLocation()).getNPCDialog().getState().getMax()-1) {
                    model.getLocation(model.getCurrentLocation()).leaveDialog();
                }
                else model.getLocation(model.getCurrentLocation()).getNPCDialog().getState().nextLine();
            }
        }
        else if (action instanceof ClickAction) {
            for (Interactable e: model.getLocation(model.getCurrentLocation()).getVisibleInteractables()) {
                if (e instanceof Item) {
                    if (e.contains(((ClickAction)action).getPosition())) {
                        PickUpCommand pickup = new PickUpCommand(model, e.getName());
                        ControllerInvoker invoker = new ControllerInvoker(pickup);
                        invoker.execute();
                    }
                }
                if (e instanceof NPC) {
                    if (e.contains(((ClickAction)action).getPosition())) {
                        TalkCommand talk = new TalkCommand(model, e.getName());
                        ControllerInvoker invoker = new ControllerInvoker(talk);
                        invoker.execute();
                    }
                }
            }
        }


    }

}

package castaway_chronicles.controller.game;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.StartTalkCommand;
import castaway_chronicles.gui.Action;
import castaway_chronicles.gui.ClickAction;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.NPC;

import java.io.IOException;

public class NPCController {
    private Game model;
    private NPC npc;
    public NPCController(Game model, NPC npc) {
        this.model = model;
        this.npc = npc;
    }
    public void step(Application application, Action action) throws IOException {
        CommandInvoker invoker = new CommandInvoker();
        if (npc.contains(((ClickAction)action).getPosition())) {
            StartTalkCommand talk = new StartTalkCommand(model.getLocation(model.getCurrentLocation()), npc.getName());
            invoker.setCommand(talk);
        }
        invoker.execute();
    }
}

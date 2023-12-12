package castaway_chronicles.controller.game;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Controller;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.GenericCommandInvoker;
import castaway_chronicles.controller.game.ControllerStates.*;
import castaway_chronicles.gui.Action;
import castaway_chronicles.gui.ClickAction;
import castaway_chronicles.model.game.Game;

import java.io.IOException;
import java.net.URISyntaxException;

public class GameController extends Controller<Game> {
    private final ControllerState locationController;
    private final ControllerState backpackController;
    private final ControllerState mapController;
    private final ControllerState dialogController;
    private final ControllerState handController;
    private final ControllerState pauseController;
    private final ControllerState walkingController;
    private ControllerState current;
    private ControllerState previous;
    private final ControllerState narratorController;
    private final GameSaver gameSaver;

    private GenericCommandInvoker commandInvoker;
    public GameController(Game model) {
        super(model);
        gameSaver = new GameSaver(model);
        locationController = new LocationController(this);
        backpackController = new BackpackController(this);
        mapController = new MapController(this);
        dialogController = new DialogController(this);
        handController = new HandController(this);
        pauseController = new PauseController(this, gameSaver);
        walkingController = new WalkingController(this);
        narratorController = new NarratorController(this);
        current = locationController;
        commandInvoker = new CommandInvoker();
    }

    @Override
    public void step(Application application, Action action, long time) throws IOException, InterruptedException, URISyntaxException {
        current.none(time);
        if (action.getType().equalsIgnoreCase("UP")) current.keyUp();
        if (action.getType().equalsIgnoreCase("DOWN")) current.keyDown();
        if (action.getType().equalsIgnoreCase("LEFT")) current.keyLeft();
        if (action.getType().equalsIgnoreCase("RIGHT")) current.keyRight();
        if (action.getType().equalsIgnoreCase("SELECT")) current.select(application);
        if (action.getType().equalsIgnoreCase("ESCAPE")) current.escape();
        if (action.getType().equalsIgnoreCase("CLICK")) current.click(((ClickAction)action).getPosition(), application);
    }
    public void setControllerState(ControllerState controllerState) {
        this.previous = this.current;
        this.current = controllerState;
    }

    public ControllerState getPauseController() {
        return pauseController;
    }

    public ControllerState getLocationController() {
        return locationController;
    }
    public ControllerState getBackpackController() {
        return backpackController;
    }

    public ControllerState getMapController() {
        return mapController;
    }

    public ControllerState getDialogController() {
        return dialogController;
    }

    public ControllerState getHandController() {
        return handController;
    }

    public ControllerState getWalkingController() {
        return walkingController;
    }
    public ControllerState getNarratorController() {return narratorController;}
    public ControllerState getCurrent() {
        return current;
    }
    public ControllerState getPrevious() {
        return previous;
    }

    public GenericCommandInvoker getCommandInvoker() {return commandInvoker;}
    public void setCommandInvoker(GenericCommandInvoker commandInvoker) {this.commandInvoker = commandInvoker;}

}

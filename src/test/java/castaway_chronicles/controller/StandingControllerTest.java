package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Commands.ChangeLocationCommand;
import castaway_chronicles.controller.Commands.CommandInvoker;
import castaway_chronicles.controller.Commands.PickUpCommand;
import castaway_chronicles.controller.Commands.StartTalkCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.controller.game.locationControllers.StandingController;
import castaway_chronicles.controller.game.locationControllers.WalkingController;
import castaway_chronicles.model.Interactable;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.*;
import castaway_chronicles.model.game.scene.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class StandingControllerTest {
    private StandingController standingController;
    private GameController gameControllerMock;
    private Application applicationMock;
    private CommandInvoker commandInvokerMock;
    private MainChar mainCharMock;
    private Position positionMock;
    private Position positionMainCharMock;
    private ControllerState controllerStateMock;
    private WalkingController walkingControllerMock;
    private Game gameMock;
    private Location locationMock;

    @BeforeEach
    void init() {
        controllerStateMock = Mockito.mock(ControllerState.class);
        gameMock = Mockito.mock(Game.class);
        gameControllerMock = Mockito.mock(GameController.class);
        commandInvokerMock = Mockito.mock(CommandInvoker.class);
        applicationMock = Mockito.mock(Application.class);
        locationMock = Mockito.mock(Location.class);
        standingController = new StandingController(gameControllerMock);
        mainCharMock = Mockito.mock(MainChar.class);
        positionMock = Mockito.mock(Position.class);
        positionMainCharMock = Mockito.mock(Position.class);
        walkingControllerMock = Mockito.mock(WalkingController.class);
        Mockito.when(gameControllerMock.getModel()).thenReturn(gameMock);
        Mockito.when(gameControllerMock.getCommandInvoker()).thenReturn(commandInvokerMock);
        Mockito.when(gameControllerMock.getLocationController()).thenReturn(controllerStateMock);
        Mockito.when(gameControllerMock.getPauseController()).thenReturn(controllerStateMock);
        Mockito.when(gameControllerMock.getWalkingController()).thenReturn(walkingControllerMock);
        Mockito.when(gameControllerMock.getDialogController()).thenReturn(controllerStateMock);
        Mockito.when(gameControllerMock.getMapController()).thenReturn(controllerStateMock);
        Mockito.when(gameControllerMock.getBackpackController()).thenReturn(controllerStateMock);
        Mockito.when(gameMock.getCurrentLocation()).thenReturn(locationMock);
        Mockito.when(mainCharMock.getPosition()).thenReturn(positionMainCharMock);
    }

    @Test
    void key() {
        standingController.key(KeyEvent.VK_DOWN, applicationMock);
        Mockito.verify(gameMock, Mockito.never()).setCurrentScene(Game.SCENE.PAUSE);
        Mockito.verify(gameControllerMock, Mockito.never()).setControllerState(controllerStateMock);

        standingController.key(KeyEvent.VK_ESCAPE, applicationMock);
        Mockito.verify(gameMock).setCurrentScene(Game.SCENE.PAUSE);
        Mockito.verify(gameControllerMock, Mockito.times(2)).setControllerState(controllerStateMock);
    }

    @Test
    void click() throws IOException, URISyntaxException, InterruptedException {
        Interactable interactableMock = Mockito.mock(Interactable.class);
        StandingController standingControllerMock = Mockito.mock(StandingController.class);

        Mockito.when(locationMock.getMainChar()).thenReturn(null);
        Mockito.when(interactableMock.contains(positionMock)).thenReturn(false);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(interactableMock));

        standingController.click(positionMock, applicationMock);

        Mockito.verify(gameControllerMock, Mockito.never()).setControllerState(walkingControllerMock);


        Mockito.when(gameControllerMock.getCurrent()).thenReturn(controllerStateMock);
        Mockito.when(locationMock.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(interactableMock.contains(positionMock)).thenReturn(false);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(interactableMock));

        standingController.click(positionMock, applicationMock);

        Mockito.verify(gameControllerMock, Mockito.never()).setControllerState(walkingControllerMock);


        Mockito.when(walkingControllerMock.setToWalk(positionMock)).thenReturn(false);
        Mockito.when(gameControllerMock.getCurrent()).thenReturn(standingControllerMock);
        Mockito.when(locationMock.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(interactableMock.contains(positionMock)).thenReturn(false);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(interactableMock));

        standingController.click(positionMock, applicationMock);

        Mockito.verify(gameControllerMock).setControllerState(walkingControllerMock);


        Mockito.when(positionMainCharMock.getX()).thenReturn(0);
        Mockito.when(positionMock.getX()).thenReturn(0);
        Mockito.when(walkingControllerMock.setToWalk(positionMock)).thenReturn(true);
        Mockito.when(gameControllerMock.getCurrent()).thenReturn(standingControllerMock);
        Mockito.when(locationMock.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(interactableMock.contains(positionMock)).thenReturn(false);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(interactableMock));

        standingController.click(positionMock, applicationMock);

        Mockito.verify(mainCharMock).setName("walk1_left");


        Mockito.when(positionMainCharMock.getX()).thenReturn(0);
        Mockito.when(positionMock.getX()).thenReturn(1);
        Mockito.when(walkingControllerMock.setToWalk(positionMock)).thenReturn(true);
        Mockito.when(gameControllerMock.getCurrent()).thenReturn(standingControllerMock);
        Mockito.when(locationMock.getMainChar()).thenReturn(mainCharMock);
        Mockito.when(interactableMock.contains(positionMock)).thenReturn(false);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(interactableMock));

        standingController.click(positionMock, applicationMock);

        Mockito.verify(gameControllerMock, Mockito.times(3)).setControllerState(walkingControllerMock);
        Mockito.verify(mainCharMock).setName("walk1_right");


        Mockito.when(locationMock.getMainChar()).thenReturn(null);
        Mockito.when(interactableMock.contains(positionMock)).thenReturn(true);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(interactableMock));

        standingController.click(positionMock, applicationMock);

        Mockito.verify(gameControllerMock, Mockito.times(3)).setControllerState(walkingControllerMock);
        Mockito.verify(mainCharMock, Mockito.times(2)).setName(Mockito.anyString());


        Item itemMock = Mockito.mock(Item.class);
        Mockito.when(locationMock.getMainChar()).thenReturn(null);
        Mockito.when(itemMock.contains(positionMock)).thenReturn(true);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(itemMock));

        standingController.click(positionMock, applicationMock);
        standingController.none(0);

        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(PickUpCommand.class));
        Mockito.verify(commandInvokerMock).execute();
        Mockito.verify(gameControllerMock, Mockito.times(3)).setControllerState(walkingControllerMock);
        Mockito.verify(mainCharMock, Mockito.times(2)).setName(Mockito.anyString());


        NPC npcMock = Mockito.mock(NPC.class);
        Mockito.when(locationMock.getMainChar()).thenReturn(null);
        Mockito.when(npcMock.contains(positionMock)).thenReturn(true);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(npcMock));

        standingController.click(positionMock, applicationMock);
        standingController.none(0);

        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(StartTalkCommand.class));
        Mockito.verify(commandInvokerMock, Mockito.times(2)).execute();
        Mockito.verify(gameControllerMock, Mockito.times(3)).setControllerState(walkingControllerMock);
        Mockito.verify(gameControllerMock).setControllerState(controllerStateMock);
        Mockito.verify(mainCharMock, Mockito.times(2)).setName(Mockito.anyString());


        Icon iconMock = Mockito.mock(Icon.class);
        Mockito.when(iconMock.getName()).thenReturn("test_test");
        Mockito.when(locationMock.getMainChar()).thenReturn(null);
        Mockito.when(iconMock.contains(positionMock)).thenReturn(true);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(iconMock));

        standingController.click(positionMock, applicationMock);
        standingController.none(0);

        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(ChangeLocationCommand.class));
        Mockito.verify(commandInvokerMock, Mockito.times(3)).execute();
        Mockito.verify(gameControllerMock, Mockito.times(3)).setControllerState(walkingControllerMock);
        Mockito.verify(gameControllerMock).setControllerState(controllerStateMock);
        Mockito.verify(mainCharMock, Mockito.times(2)).setName(Mockito.anyString());

        Mockito.when(iconMock.getName()).thenReturn("Map_test");
        Mockito.when(locationMock.getMainChar()).thenReturn(null);
        Mockito.when(iconMock.contains(positionMock)).thenReturn(true);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(iconMock));

        standingController.click(positionMock, applicationMock);
        standingController.setLastCommandNull();

        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(ChangeLocationCommand.class));
        Mockito.verify(gameMock).setCurrentScene(Game.SCENE.MAP);
        Mockito.verify(commandInvokerMock, Mockito.times(3)).execute();
        Mockito.verify(gameControllerMock, Mockito.times(3)).setControllerState(walkingControllerMock);
        Mockito.verify(gameControllerMock, Mockito.times(2)).setControllerState(controllerStateMock);
        Mockito.verify(mainCharMock, Mockito.times(2)).setName(Mockito.anyString());

        Mockito.when(iconMock.getName()).thenReturn("Backpack_test");
        Mockito.when(locationMock.getMainChar()).thenReturn(null);
        Mockito.when(iconMock.contains(positionMock)).thenReturn(true);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(iconMock));

        standingController.click(positionMock, applicationMock);
        standingController.none(0);

        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(ChangeLocationCommand.class));
        Mockito.verify(gameMock).setCurrentScene(Game.SCENE.BACKPACK);
        Mockito.verify(commandInvokerMock, Mockito.times(3)).execute();
        Mockito.verify(gameControllerMock, Mockito.times(3)).setControllerState(walkingControllerMock);
        Mockito.verify(gameControllerMock, Mockito.times(3)).setControllerState(controllerStateMock);
        Mockito.verify(mainCharMock, Mockito.times(2)).setName(Mockito.anyString());
    }
}

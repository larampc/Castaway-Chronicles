package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Commands.ChangeLocationCommand;
import castaway_chronicles.controller.Commands.CommandInvoker;
import castaway_chronicles.controller.game.scenes.MapController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.Icon;
import castaway_chronicles.model.game.gameElements.Item;
import castaway_chronicles.model.game.scene.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class MapControllerTest {
    private Game gameMock;
    private Application applicationMock;
    private GameController gameControlleMock;
    private MapController mapController;
    @BeforeEach
    public void init() {
        applicationMock = Mockito.mock(Application.class);
        gameControlleMock = Mockito.mock(GameController.class);
        gameMock = Mockito.mock(Game.class);
        Mockito.when(gameControlleMock.getModel()).thenReturn(gameMock);

        mapController = new MapController(gameControlleMock);
    }

    @Test
    public void key() throws IOException, URISyntaxException, InterruptedException {
        ControllerState controllerStateMock = Mockito.mock(ControllerState.class);
        Mockito.when(gameControlleMock.getLocationController()).thenReturn(controllerStateMock);

        mapController.key(KeyEvent.VK_DOWN, applicationMock);
        Mockito.verify(gameMock, Mockito.never()).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControlleMock, Mockito.never()).setControllerState(controllerStateMock);

        mapController.key(KeyEvent.VK_ESCAPE, applicationMock);
        Mockito.verify(gameMock).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControlleMock).setControllerState(controllerStateMock);
    }

    @Test
    public void click() throws IOException, URISyntaxException, InterruptedException {
        ControllerState controllerStateMock = Mockito.mock(ControllerState.class);
        Mockito.when(gameControlleMock.getLocationController()).thenReturn(controllerStateMock);
        Position positionMock = Mockito.mock(Position.class);
        CommandInvoker commandInvokerMock = Mockito.mock(CommandInvoker.class);
        Mockito.when(gameControlleMock.getCommandInvoker()).thenReturn(commandInvokerMock);
        Map mapMock = Mockito.mock(Map.class);
        Mockito.when(gameMock.getMap()).thenReturn(mapMock);
        Icon iconMock = Mockito.mock(Icon.class);
        Item itemMock = Mockito.mock(Item.class);
        Mockito.when(mapMock.getVisibleInteractables()).thenReturn(List.of(iconMock, itemMock));
        Mockito.when(iconMock.getName()).thenReturn("icon_test");
        Mockito.when(itemMock.getName()).thenReturn("item_test");

        Mockito.when(iconMock.contains(positionMock)).thenReturn(false);
        Mockito.when(itemMock.contains(positionMock)).thenReturn(false);
        mapController.click(positionMock, applicationMock);
        Mockito.verify(commandInvokerMock, Mockito.never()).setCommand(Mockito.any());
        Mockito.verify(commandInvokerMock, Mockito.never()).execute();
        Mockito.verify(gameMock, Mockito.never()).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControlleMock, Mockito.never()).setControllerState(controllerStateMock);
        Mockito.verify(mapMock, Mockito.never()).setInvisible(Mockito.any());
        Mockito.verify(mapMock, Mockito.never()).setVisible(Mockito.any());

        Mockito.when(iconMock.contains(positionMock)).thenReturn(false);
        Mockito.when(itemMock.contains(positionMock)).thenReturn(true);
        mapController.click(positionMock, applicationMock);
        Mockito.verify(commandInvokerMock, Mockito.never()).setCommand(Mockito.any());
        Mockito.verify(commandInvokerMock, Mockito.never()).execute();
        Mockito.verify(gameMock, Mockito.never()).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControlleMock, Mockito.never()).setControllerState(controllerStateMock);
        Mockito.verify(mapMock).setInvisible("item_test");
        Mockito.verify(mapMock).setVisible("item_icon");

        Mockito.when(iconMock.contains(positionMock)).thenReturn(true);
        Mockito.when(itemMock.contains(positionMock)).thenReturn(false);
        mapController.click(positionMock, applicationMock);
        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(ChangeLocationCommand.class));
        Mockito.verify(commandInvokerMock).execute();
        Mockito.verify(gameMock).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControlleMock).setControllerState(controllerStateMock);
        Mockito.verify(mapMock).setInvisible("item_test");
        Mockito.verify(mapMock).setVisible("item_icon");
    }
}
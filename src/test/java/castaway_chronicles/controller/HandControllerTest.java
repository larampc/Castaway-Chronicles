package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.locationControllers.HandController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.BackpackItem;
import castaway_chronicles.model.game.gameElements.NPC;
import castaway_chronicles.model.game.scene.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HandControllerTest {

    private HandController handController;
    private GameController gameController;
    private Game game;
    private Application applicationMock;
    private CommandInvoker commandInvokerMock;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        gameController = new GameController(game);
        commandInvokerMock = Mockito.mock(CommandInvoker.class);
        gameController.setCommandInvoker(commandInvokerMock);
        handController = (HandController) gameController.getHandController();
        gameController.setControllerState(handController);
        applicationMock = Mockito.mock(Application.class);
    }

    @Test
    void testClickWithEmptyToGive() throws IOException, InterruptedException, URISyntaxException {
        BackpackItem backpackItem = Mockito.mock(BackpackItem.class);

        TextBox textBox = mock(TextBox.class);
        Backpack backpackMock = mock(Backpack.class);
        when(game.getTextDisplay()).thenReturn(textBox);
        Mockito.when(textBox.getInteractable()).thenReturn(backpackItem);

        Mockito.when(game.getBackpack()).thenReturn(backpackMock);

        Location locationMock = mock(Location.class);

        HashMap<String,Location> locations = new HashMap<>();
        locations.put("StartLocation",locationMock);

        Mockito.when(game.getLocations()).thenReturn(locations);
        Mockito.when(game.getCurrentLocation()).thenReturn(locationMock);

        handController.click(new Position(0, 0), applicationMock);

        Mockito.verify(backpackItem).setInHand(true);
        Mockito.verify(textBox).activateTextBox();
        Mockito.verify(game).setCurrentScene(Game.SCENE.LOCATION);
        assertEquals(gameController.getNarratorController(), gameController.getCurrent());
    }

    @Test
    void testNPCClickWithToGive() throws IOException, InterruptedException, URISyntaxException {
        BackpackItem backpackItemMock = Mockito.mock(BackpackItem.class);
        TextBox textDisplayMock = mock(TextBox.class);
        Location locationMock = mock(Location.class);
        NPC npcMock = mock(NPC.class);
        Position positionMock = Mockito.mock(Position.class);
        CommandInvoker commandInvokerMock = Mockito.mock(CommandInvoker.class);

        handController.setToGive("NPC_NAME");
        when(game.getCurrentLocation()).thenReturn(locationMock);
        when(locationMock.getVisibleInteractables()).thenReturn(List.of(npcMock));
        when(npcMock.contains(positionMock)).thenReturn(true);
        when(npcMock.getName()).thenReturn("NPC_NAME");
        when(game.getTextDisplay()).thenReturn(textDisplayMock);
        when(textDisplayMock.getInteractable()).thenReturn(backpackItemMock);
        when(backpackItemMock.getEffects()).thenReturn(new ArrayList<>());
        Mockito.when(game.getCurrentLocation()).thenReturn(locationMock);
        HashMap<String,Location> locations = new HashMap<>();
        locations.put("StartLocation",locationMock);
        when(game.getLocations()).thenReturn(locations);
        when(textDisplayMock.isActiveTextBox()).thenReturn(true);
        gameController.setCommandInvoker(commandInvokerMock);

        handController.click(positionMock, applicationMock);

        verify(commandInvokerMock).setCommand(any(HandleEffectsCommand.class));
        verify(commandInvokerMock).execute();
        verify(game).setCurrentScene(Game.SCENE.LOCATION);
        assertEquals(gameController.getDialogController(), gameController.getCurrent());

        when(textDisplayMock.isActiveTextBox()).thenReturn(false);
        handController.click(positionMock, applicationMock);

        verify(commandInvokerMock,times(2)).setCommand(any(HandleEffectsCommand.class));
        verify(commandInvokerMock,times(2)).execute();
        verify(game,times(2)).setCurrentScene(Game.SCENE.LOCATION);
        assertEquals(gameController.getLocationController(), gameController.getCurrent());
    }


    @Test
    void testEmptyClickWithToGive() throws IOException, InterruptedException, URISyntaxException {
        BackpackItem backpackItemMock = Mockito.mock(BackpackItem.class);

        TextBox textBox = mock(TextBox.class);
        when(textBox.getInteractable()).thenReturn(backpackItemMock);

        Backpack backpackMock = mock(Backpack.class);
        when(game.getTextDisplay()).thenReturn(textBox);

        Mockito.when(game.getBackpack()).thenReturn(backpackMock);

        Location locationMock = mock(Location.class);

        HashMap<String,Location> locations = new HashMap<>();
        locations.put("StartLocation",locationMock);

        Mockito.when(game.getLocations()).thenReturn(locations);
        Mockito.when(game.getCurrentLocation()).thenReturn(locationMock);

        NPC npcMock = mock(NPC.class);
        when(npcMock.getName()).thenReturn("NPC_NAME");
        Position position = Mockito.mock(Position.class);
        when(npcMock.contains(position)).thenReturn(false);

        when(locationMock.getVisibleInteractables()).thenReturn(List.of(npcMock));

        handController.setToGive("NPC_NAME");
        handController.click(position, applicationMock);

        Mockito.verify(textBox,times(1)).activateTextBox();
        Mockito.verify(game).setCurrentScene(Game.SCENE.LOCATION);
        assertEquals(gameController.getNarratorController(), gameController.getCurrent());
    }


    @Test
    public void escape() throws IOException, URISyntaxException, InterruptedException {
        handController.key(KeyEvent.VK_ESCAPE, applicationMock);
        Mockito.verify(game).setCurrentScene(Game.SCENE.BACKPACK);
        assertEquals(gameController.getBackpackController(),gameController.getCurrent());
        Mockito.verify(commandInvokerMock).execute();
    }
}

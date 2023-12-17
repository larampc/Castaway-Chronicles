package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.ChangeSceneCommand;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.ControllerStates.HandController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.ItemBackpack;
import castaway_chronicles.model.game.elements.NPC;
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
        game = new Game();
        gameController = new GameController(game);
        commandInvokerMock = Mockito.mock(CommandInvoker.class);
        gameController.setCommandInvoker(commandInvokerMock);
        handController = (HandController) gameController.getHandController();
        gameController.setControllerState(handController);
        applicationMock = Mockito.mock(Application.class);
    }

    @Test
    void testClickWithEmptyToGive() throws IOException, InterruptedException, URISyntaxException {
        ItemBackpack itemBackpack = Mockito.mock(ItemBackpack.class);

        TextDisplay backpackSelectionMock = mock(TextDisplay.class);
        when(backpackSelectionMock.getElement()).thenReturn(itemBackpack);

        Backpack backpackMock = mock(Backpack.class);
        when(backpackMock.getTextDisplay()).thenReturn(backpackSelectionMock);

        game.setBackpack(backpackMock);


        Location locationMock = mock(Location.class);
        TextDisplay backpackAnswer = mock(TextDisplay.class);
        when(locationMock.getTextDisplay()).thenReturn(backpackAnswer);

        HashMap<String,Location> locations = new HashMap<>();
        locations.put("StartLocation",locationMock);

        game.setLocations(locations);
        game.setCurrentLocation("StartLocation");

        handController.click(new Position(0, 0), applicationMock);

        Mockito.verify(backpackAnswer,times(1)).activateTextBox(itemBackpack);

        assertEquals(Game.SCENE.LOCATION, game.getScene());
        assertEquals(gameController.getNarratorController(), gameController.getCurrent());
    }

    @Test
    void testNPCClickWithToGive() throws IOException, InterruptedException, URISyntaxException {
        ItemBackpack itemBackpackMock = Mockito.mock(ItemBackpack.class);
        when(itemBackpackMock.getEffects()).thenReturn(new ArrayList<>());

        TextDisplay backpackSelectionMock = mock(TextDisplay.class);
        when(backpackSelectionMock.getElement()).thenReturn(itemBackpackMock);

        Backpack backpackMock = mock(Backpack.class);
        when(backpackMock.getTextDisplay()).thenReturn(backpackSelectionMock);

        game.setBackpack(backpackMock);

        Location locationMock = mock(Location.class);
        TextDisplay backpackAnswer = mock(TextDisplay.class);
        when(locationMock.getTextDisplay()).thenReturn(backpackAnswer);

        HashMap<String,Location> locations = new HashMap<>();
        locations.put("StartLocation",locationMock);

        game.setLocations(locations);
        game.setCurrentLocation("StartLocation");

        NPC npcMock = mock(NPC.class);
        when(npcMock.getName()).thenReturn("NPC_NAME");
        when(npcMock.contains(new Position(0, 0))).thenReturn(true);

        when(locationMock.getVisibleInteractables()).thenReturn(List.of(npcMock));

        CommandInvoker commandInvokerMock = Mockito.mock(CommandInvoker.class);
        gameController.setCommandInvoker(commandInvokerMock);

        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        when(locationMock.getTextDisplay()).thenReturn(textDisplayMock);
        when(textDisplayMock.isActiveTextBox()).thenReturn(true);

        handController.setToGive("NPC_NAME");
        handController.click(new Position(0, 0), applicationMock);

        verify(commandInvokerMock).setCommand(any(HandleEffectsCommand.class));
        verify(commandInvokerMock, times(1)).execute();
        assertEquals(Game.SCENE.LOCATION, game.getScene());
        assertEquals(gameController.getDialogController(), gameController.getCurrent());
    }


    @Test
    void testEmptyClickWithToGive() throws IOException, InterruptedException, URISyntaxException {
        ItemBackpack itemBackpackMock = Mockito.mock(ItemBackpack.class);

        TextDisplay backpackSelectionMock = mock(TextDisplay.class);
        when(backpackSelectionMock.getElement()).thenReturn(itemBackpackMock);

        Backpack backpackMock = mock(Backpack.class);
        when(backpackMock.getTextDisplay()).thenReturn(backpackSelectionMock);

        game.setBackpack(backpackMock);

        Location locationMock = mock(Location.class);
        TextDisplay backpackAnswer = mock(TextDisplay.class);
        when(locationMock.getTextDisplay()).thenReturn(backpackAnswer);

        HashMap<String,Location> locations = new HashMap<>();
        locations.put("StartLocation",locationMock);

        game.setLocations(locations);
        game.setCurrentLocation("StartLocation");

        NPC npcMock = mock(NPC.class);
        when(npcMock.getName()).thenReturn("NPC_NAME");
        when(npcMock.contains(new Position(0, 0))).thenReturn(true);

        when(locationMock.getVisibleInteractables()).thenReturn(List.of(npcMock));

        handController.setToGive("NPC_NAME");
        handController.click(new Position(5, 5), applicationMock);

        Mockito.verify(backpackAnswer,times(1)).activateTextBox(itemBackpackMock);
        assertEquals(Game.SCENE.LOCATION, game.getScene());
        assertEquals(gameController.getNarratorController(), gameController.getCurrent());
    }


    @Test
    public void escape() throws IOException, URISyntaxException, InterruptedException {
        handController.key(KeyEvent.VK_ESCAPE, applicationMock);
        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(ChangeSceneCommand.class)); //new ChangeSceneCommand(gameController.getModel(), "BACKPACK")
        assertEquals(gameController.getBackpackController(),gameController.getCurrent());
        Mockito.verify(commandInvokerMock).execute();
    }
}

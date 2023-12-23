package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Commands.CommandInvoker;
import castaway_chronicles.controller.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.locationControllers.HandController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.controller.game.locationControllers.NarratorController;
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

public class HandControllerTest {

    private HandController handController;
    private GameController gameControllerMock;
    private Game game;
    private Application applicationMock;
    private CommandInvoker commandInvokerMock;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        gameControllerMock = Mockito.mock(GameController.class);
        commandInvokerMock = Mockito.mock(CommandInvoker.class);
        Mockito.when(gameControllerMock.getModel()).thenReturn(game);
        Mockito.when(gameControllerMock.getCommandInvoker()).thenReturn(commandInvokerMock);
        handController = new HandController(gameControllerMock);
        applicationMock = Mockito.mock(Application.class);
    }
    @Test
    void testClickWithEmptyToGive() throws IOException, InterruptedException, URISyntaxException {
        BackpackItem backpackItem = Mockito.mock(BackpackItem.class);
        TextBox textBox = Mockito.mock(TextBox.class);
        NarratorController narratorControllerMock = Mockito.mock(NarratorController.class);

        Mockito.when(game.getTextBox()).thenReturn(textBox);
        Mockito.when(textBox.getInteractable()).thenReturn(backpackItem);
        Position positionMock = Mockito.mock(Position.class);
        Mockito.when(positionMock.getX()).thenReturn(0);
        Mockito.when(positionMock.getY()).thenReturn(0);
        Mockito.when(gameControllerMock.getNarratorController()).thenReturn(narratorControllerMock);

        handController.click(positionMock, applicationMock);

        Mockito.verify(backpackItem).setInHand(true);
        Mockito.verify(textBox).activateTextBox();
        Mockito.verify(game).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControllerMock).setControllerState(narratorControllerMock);
    }

    @Test
    void testNPCClickWithToGive() throws IOException, InterruptedException, URISyntaxException {
        BackpackItem backpackItemMock = Mockito.mock(BackpackItem.class);
        TextBox textDisplayMock = Mockito.mock(TextBox.class);
        Location locationMock = Mockito.mock(Location.class);
        NPC npcMock = Mockito.mock(NPC.class);
        Position positionMock = Mockito.mock(Position.class);

        handController.setToGive("NPC_NAME");
        Mockito.when(game.getCurrentLocation()).thenReturn(locationMock);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(npcMock));
        Mockito.when(npcMock.contains(positionMock)).thenReturn(true);
        Mockito.when(npcMock.getName()).thenReturn("NPC_NAME");
        Mockito.when(game.getTextBox()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.getInteractable()).thenReturn(backpackItemMock);
        Mockito.when(backpackItemMock.getEffects()).thenReturn(new ArrayList<>());
        Mockito.when(game.getCurrentLocation()).thenReturn(locationMock);
        HashMap<String,Location> locations = new HashMap<>();
        locations.put("StartLocation",locationMock);
        Mockito.when(game.getLocations()).thenReturn(locations);
        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(true);

        handController.click(positionMock, applicationMock);

        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(HandleEffectsCommand.class));
        Mockito.verify(commandInvokerMock).execute();
        Mockito.verify(game).setCurrentScene(Game.SCENE.LOCATION);
        assertEquals(gameControllerMock.getDialogController(), gameControllerMock.getCurrent());

        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(false);
        handController.click(positionMock, applicationMock);

        Mockito.verify(commandInvokerMock,Mockito.times(2)).setCommand(Mockito.any(HandleEffectsCommand.class));
        Mockito.verify(commandInvokerMock,Mockito.times(2)).execute();
        Mockito.verify(game,Mockito.times(2)).setCurrentScene(Game.SCENE.LOCATION);
        assertEquals(gameControllerMock.getLocationController(), gameControllerMock.getCurrent());
    }


    @Test
    void testEmptyClickWithToGive() throws IOException, InterruptedException, URISyntaxException {
        BackpackItem backpackItemMock = Mockito.mock(BackpackItem.class);

        TextBox textBox = Mockito.mock(TextBox.class);
        Mockito.when(textBox.getInteractable()).thenReturn(backpackItemMock);

        Backpack backpackMock = Mockito.mock(Backpack.class);
        Mockito.when(game.getTextBox()).thenReturn(textBox);

        Mockito.when(game.getBackpack()).thenReturn(backpackMock);

        Location locationMock = Mockito.mock(Location.class);

        HashMap<String,Location> locations = new HashMap<>();
        locations.put("StartLocation",locationMock);

        Mockito.when(game.getLocations()).thenReturn(locations);
        Mockito.when(game.getCurrentLocation()).thenReturn(locationMock);

        NPC npcMock = Mockito.mock(NPC.class);
        Mockito.when(npcMock.getName()).thenReturn("NPC_NAME");
        Position position = Mockito.mock(Position.class);
        Mockito.when(npcMock.contains(position)).thenReturn(false);

        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(npcMock));

        handController.setToGive("NPC_NAME");
        handController.click(position, applicationMock);

        Mockito.verify(textBox).activateTextBox();
        Mockito.verify(game).setCurrentScene(Game.SCENE.LOCATION);
        assertEquals(gameControllerMock.getNarratorController(), gameControllerMock.getCurrent());
    }


    @Test
    public void escape() throws IOException, URISyntaxException, InterruptedException {
        handController.key(KeyEvent.VK_ESCAPE, applicationMock);
        Mockito.verify(game).setCurrentScene(Game.SCENE.BACKPACK);
        assertEquals(gameControllerMock.getBackpackController(), gameControllerMock.getCurrent());
        Mockito.verify(commandInvokerMock).execute();
    }
}

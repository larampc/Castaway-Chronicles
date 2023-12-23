package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Commands.CommandInvoker;
import castaway_chronicles.controller.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.locationControllers.DialogController;
import castaway_chronicles.controller.game.locationControllers.HandController;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.controller.game.locationControllers.NarratorController;
import castaway_chronicles.controller.game.locationControllers.StandingController;
import castaway_chronicles.controller.game.scenes.BackpackController;
import castaway_chronicles.model.Interactable;
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
import java.util.List;

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
        StandingController standingControllerMock = Mockito.mock(StandingController.class);
        DialogController dialogControllerMock = Mockito.mock(DialogController.class);

        handController.setToGive("NPC_NAME");
        Mockito.when(game.getCurrentLocation()).thenReturn(locationMock);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(npcMock));
        Mockito.when(npcMock.contains(positionMock)).thenReturn(true);
        Mockito.when(npcMock.getName()).thenReturn("NPC_NAME");
        Mockito.when(game.getTextBox()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.getInteractable()).thenReturn(backpackItemMock);
        Mockito.when(backpackItemMock.getEffects()).thenReturn(new ArrayList<>());
        Mockito.when(game.getCurrentLocation()).thenReturn(locationMock);
        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(true);
        Mockito.when(gameControllerMock.getStandingController()).thenReturn(standingControllerMock);
        Mockito.when(gameControllerMock.getDialogController()).thenReturn(dialogControllerMock);

        handController.click(positionMock, applicationMock);

        Mockito.verify(gameControllerMock).getCommandInvoker();
        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(HandleEffectsCommand.class));
        Mockito.verify(commandInvokerMock).execute();
        Mockito.verify(game).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControllerMock).setControllerState(dialogControllerMock);

        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(false);
        handController.click(positionMock, applicationMock);

        Mockito.verify(gameControllerMock,Mockito.times(2)).getCommandInvoker();
        Mockito.verify(commandInvokerMock,Mockito.times(2)).setCommand(Mockito.any(HandleEffectsCommand.class));
        Mockito.verify(commandInvokerMock,Mockito.times(2)).execute();
        Mockito.verify(game,Mockito.times(2)).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControllerMock).setControllerState(standingControllerMock);
    }
    @Test
    void testNotNPCClick() throws IOException, InterruptedException, URISyntaxException {
        BackpackItem backpackItemMock = Mockito.mock(BackpackItem.class);
        TextBox textDisplayMock = Mockito.mock(TextBox.class);
        Location locationMock = Mockito.mock(Location.class);
        Interactable interactableMock = Mockito.mock(Interactable.class);
        Position positionMock = Mockito.mock(Position.class);
        StandingController standingControllerMock = Mockito.mock(StandingController.class);
        DialogController dialogControllerMock = Mockito.mock(DialogController.class);

        handController.setToGive("NPC_NAME");
        Mockito.when(game.getCurrentLocation()).thenReturn(locationMock);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(interactableMock));
        Mockito.when(interactableMock.contains(positionMock)).thenReturn(true);
        Mockito.when(interactableMock.getName()).thenReturn("NPC_NAME");
        Mockito.when(game.getTextBox()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.getInteractable()).thenReturn(backpackItemMock);
        Mockito.when(backpackItemMock.getEffects()).thenReturn(new ArrayList<>());
        Mockito.when(game.getCurrentLocation()).thenReturn(locationMock);
        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(true);
        Mockito.when(gameControllerMock.getStandingController()).thenReturn(standingControllerMock);
        Mockito.when(gameControllerMock.getDialogController()).thenReturn(dialogControllerMock);

        handController.click(positionMock, applicationMock);

        Mockito.verify(gameControllerMock, Mockito.never()).getCommandInvoker();
    }
    @Test
    void testNPCNotInClick() throws IOException, InterruptedException, URISyntaxException {
        BackpackItem backpackItemMock = Mockito.mock(BackpackItem.class);
        TextBox textDisplayMock = Mockito.mock(TextBox.class);
        Location locationMock = Mockito.mock(Location.class);
        Interactable interactableMock = Mockito.mock(Interactable.class);
        Position positionMock = Mockito.mock(Position.class);
        StandingController standingControllerMock = Mockito.mock(StandingController.class);
        DialogController dialogControllerMock = Mockito.mock(DialogController.class);

        handController.setToGive("NPC_NAME");
        Mockito.when(game.getCurrentLocation()).thenReturn(locationMock);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(interactableMock));
        Mockito.when(interactableMock.contains(positionMock)).thenReturn(false);
        Mockito.when(interactableMock.getName()).thenReturn("NPC_NAME");
        Mockito.when(game.getTextBox()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.getInteractable()).thenReturn(backpackItemMock);
        Mockito.when(backpackItemMock.getEffects()).thenReturn(new ArrayList<>());
        Mockito.when(game.getCurrentLocation()).thenReturn(locationMock);
        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(true);
        Mockito.when(gameControllerMock.getStandingController()).thenReturn(standingControllerMock);
        Mockito.when(gameControllerMock.getDialogController()).thenReturn(dialogControllerMock);

        handController.click(positionMock, applicationMock);

        Mockito.verify(gameControllerMock, Mockito.never()).getCommandInvoker();
    }

    @Test
    void testNotTargetNPCClick() throws IOException, InterruptedException, URISyntaxException {
        BackpackItem backpackItemMock = Mockito.mock(BackpackItem.class);
        TextBox textDisplayMock = Mockito.mock(TextBox.class);
        Location locationMock = Mockito.mock(Location.class);
        Interactable interactableMock = Mockito.mock(Interactable.class);
        Position positionMock = Mockito.mock(Position.class);
        StandingController standingControllerMock = Mockito.mock(StandingController.class);
        DialogController dialogControllerMock = Mockito.mock(DialogController.class);

        handController.setToGive("NPC_NAME");
        Mockito.when(game.getCurrentLocation()).thenReturn(locationMock);
        Mockito.when(locationMock.getVisibleInteractables()).thenReturn(List.of(interactableMock));
        Mockito.when(interactableMock.contains(positionMock)).thenReturn(false);
        Mockito.when(interactableMock.getName()).thenReturn("NPC_NAME2");
        Mockito.when(game.getTextBox()).thenReturn(textDisplayMock);
        Mockito.when(textDisplayMock.getInteractable()).thenReturn(backpackItemMock);
        Mockito.when(backpackItemMock.getEffects()).thenReturn(new ArrayList<>());
        Mockito.when(game.getCurrentLocation()).thenReturn(locationMock);
        Mockito.when(textDisplayMock.isActiveTextBox()).thenReturn(true);
        Mockito.when(gameControllerMock.getStandingController()).thenReturn(standingControllerMock);
        Mockito.when(gameControllerMock.getDialogController()).thenReturn(dialogControllerMock);

        handController.click(positionMock, applicationMock);

        Mockito.verify(gameControllerMock, Mockito.never()).getCommandInvoker();
    }
    @Test
    public void escape() throws IOException, URISyntaxException, InterruptedException {
        BackpackController backpackControllerMock = Mockito.mock(BackpackController.class);
        Mockito.when(gameControllerMock.getBackpackController()).thenReturn(backpackControllerMock);

        handController.key(KeyEvent.VK_ESCAPE, applicationMock);

        Mockito.verify(game).setCurrentScene(Game.SCENE.BACKPACK);
        Mockito.verify(gameControllerMock).setControllerState(backpackControllerMock);
        Mockito.verify(commandInvokerMock).execute();
    }
}

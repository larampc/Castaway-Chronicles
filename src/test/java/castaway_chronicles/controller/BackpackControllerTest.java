package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Commands.CommandInvoker;
import castaway_chronicles.controller.Commands.GetSideOptionCommand;
import castaway_chronicles.controller.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.controller.game.locationControllers.HandController;
import castaway_chronicles.controller.game.scenes.BackpackController;
import castaway_chronicles.model.InteractableWithText;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.SelectionPanel;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.BackpackItem;
import castaway_chronicles.model.game.scene.Backpack;
import castaway_chronicles.model.game.scene.TextBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class BackpackControllerTest {
    private Application applicationMock;
    private Game gameMock;
    private TextBox testBoxMock;
    private GameController gameControllerMock;
    private BackpackController backpackController;

    @BeforeEach
    void setUp(){
        applicationMock = Mockito.mock(Application.class);
        gameMock = Mockito.mock(Game.class);
        testBoxMock = Mockito.mock(TextBox.class);
        Mockito.when(gameMock.getTextBox()).thenReturn(testBoxMock);
        gameControllerMock = Mockito.mock(GameController.class);
        Mockito.when(gameControllerMock.getModel()).thenReturn(gameMock);
        backpackController = new BackpackController(gameControllerMock);
    }

    @Test
    void keyUp() throws IOException, URISyntaxException, InterruptedException {
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);
        InteractableWithText interactableWithTextMock = Mockito.mock(InteractableWithText.class);
        Mockito.when(testBoxMock.getInteractable()).thenReturn(interactableWithTextMock);
        Mockito.when(interactableWithTextMock.getChoices()).thenReturn(selectionPanelMock);

        Mockito.when(testBoxMock.isActiveChoice()).thenReturn(false);
        backpackController.key(KeyEvent.VK_UP, applicationMock);
        Mockito.verify(selectionPanelMock, Mockito.never()).previousEntry();

        Mockito.when(testBoxMock.isActiveChoice()).thenReturn(true);
        backpackController.key(KeyEvent.VK_UP, applicationMock);
        Mockito.verify(selectionPanelMock).previousEntry();
    }

    @Test
    void keyDown() throws IOException, URISyntaxException, InterruptedException {
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);
        InteractableWithText interactableWithTextMock = Mockito.mock(InteractableWithText.class);
        Mockito.when(testBoxMock.getInteractable()).thenReturn(interactableWithTextMock);
        Mockito.when(interactableWithTextMock.getChoices()).thenReturn(selectionPanelMock);

        Mockito.when(testBoxMock.isActiveChoice()).thenReturn(false);
        backpackController.key(KeyEvent.VK_DOWN, applicationMock);
        Mockito.verify(selectionPanelMock, Mockito.never()).nextEntry();

        Mockito.when(testBoxMock.isActiveChoice()).thenReturn(true);
        backpackController.key(KeyEvent.VK_DOWN, applicationMock);
        Mockito.verify(selectionPanelMock).nextEntry();
    }

    @Test
    void keySide() throws IOException, URISyntaxException, InterruptedException {
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);
        InteractableWithText interactableWithTextMock = Mockito.mock(InteractableWithText.class);
        Mockito.when(testBoxMock.getInteractable()).thenReturn(interactableWithTextMock);
        Mockito.when(interactableWithTextMock.getChoices()).thenReturn(selectionPanelMock);
        CommandInvoker commandInvoker = Mockito.mock(CommandInvoker.class);
        Mockito.when(gameControllerMock.getCommandInvoker()).thenReturn(commandInvoker);

        backpackController.key(KeyEvent.VK_RIGHT, applicationMock);
        Mockito.verify(commandInvoker).setCommand(Mockito.any(GetSideOptionCommand.class));
        Mockito.verify(commandInvoker).execute();

        backpackController.key(KeyEvent.VK_LEFT, applicationMock);
        Mockito.verify(commandInvoker, Mockito.times(2)).setCommand(Mockito.any(GetSideOptionCommand.class));
        Mockito.verify(commandInvoker, Mockito.times(2)).execute();
    }

    @Test
    void click() throws IOException {
        Position positionMock = Mockito.mock(Position.class);
        Backpack backpackMock = Mockito.mock(Backpack.class);
        Mockito.when(gameMock.getBackpack()).thenReturn(backpackMock);
        BackpackItem backpackItemMock = Mockito.mock(BackpackItem.class);
        Mockito.when(backpackMock.getVisibleInteractables()).thenReturn(List.of(backpackItemMock));

        Mockito.when(backpackItemMock.contains(positionMock)).thenReturn(false);
        backpackController.click(positionMock, applicationMock);
        Mockito.verify(backpackItemMock, Mockito.never()).setInHand(false);
        Mockito.verify(testBoxMock, Mockito.never()).activateTextBox(backpackItemMock);

        Mockito.when(backpackItemMock.contains(positionMock)).thenReturn(true);
        backpackController.click(positionMock, applicationMock);
        Mockito.verify(backpackItemMock).setInHand(false);
        Mockito.verify(testBoxMock).activateTextBox(backpackItemMock);
    }

    @Test
    void escape() throws IOException, URISyntaxException, InterruptedException {
        ControllerState controllerStateMock = Mockito.mock(ControllerState.class);
        Mockito.when(gameControllerMock.getStandingController()).thenReturn(controllerStateMock);

        Mockito.when(testBoxMock.isActiveChoice()).thenReturn(true);
        Mockito.when(testBoxMock.isActiveTextBox()).thenReturn(true);
        backpackController.key(KeyEvent.VK_ESCAPE, applicationMock);
        Mockito.verify(gameMock, Mockito.never()).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControllerMock, Mockito.never()).setControllerState(controllerStateMock);
        Mockito.verify(testBoxMock).closeTextBox();

        Mockito.when(testBoxMock.isActiveChoice()).thenReturn(false);
        Mockito.when(testBoxMock.isActiveTextBox()).thenReturn(true);
        backpackController.key(KeyEvent.VK_ESCAPE, applicationMock);
        Mockito.verify(gameMock, Mockito.never()).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControllerMock, Mockito.never()).setControllerState(controllerStateMock);
        Mockito.verify(testBoxMock, Mockito.times(2)).closeTextBox();

        Mockito.when(testBoxMock.isActiveChoice()).thenReturn(true);
        Mockito.when(testBoxMock.isActiveTextBox()).thenReturn(false);
        backpackController.key(KeyEvent.VK_ESCAPE, applicationMock);
        Mockito.verify(gameMock, Mockito.never()).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControllerMock, Mockito.never()).setControllerState(controllerStateMock);
        Mockito.verify(testBoxMock, Mockito.times(3)).closeTextBox();

        Mockito.when(testBoxMock.isActiveChoice()).thenReturn(false);
        Mockito.when(testBoxMock.isActiveTextBox()).thenReturn(false);
        backpackController.key(KeyEvent.VK_ESCAPE, applicationMock);
        Mockito.verify(gameMock).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControllerMock).setControllerState(controllerStateMock);
        Mockito.verify(testBoxMock, Mockito.times(3)).closeTextBox();
    }

    @Test
    void select() throws IOException, URISyntaxException, InterruptedException {
        CommandInvoker commandInvokerMock = Mockito.mock(CommandInvoker.class);
        HandController handControllerMock = Mockito.mock(HandController.class);
        BackpackItem backpackItemMock = Mockito.mock(BackpackItem.class);
        ControllerState controllerStateMock = Mockito.mock(ControllerState.class);
        Mockito.when(gameControllerMock.getStandingController()).thenReturn(controllerStateMock);
        Mockito.when(testBoxMock.getInteractable()).thenReturn(backpackItemMock);
        Mockito.when(gameControllerMock.getHandController()).thenReturn(handControllerMock);
        Mockito.when(gameControllerMock.getCommandInvoker()).thenReturn(commandInvokerMock);
        Mockito.when(gameControllerMock.getNarratorController()).thenReturn(controllerStateMock);


        Mockito.when(testBoxMock.isActiveTextBox()).thenReturn(false);
        Mockito.when(testBoxMock.isActiveChoice()).thenReturn(true);
        Mockito.when(backpackItemMock.getCommand()).thenReturn("use test");

        backpackController.key(KeyEvent.VK_ENTER, applicationMock);

        Mockito.verify(testBoxMock, Mockito.never()).setActiveChoice(true);
        Mockito.verify(testBoxMock).closeTextBox();
        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(HandleEffectsCommand.class));
        Mockito.verify(commandInvokerMock).execute();
        Mockito.verify(gameMock).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControllerMock).setControllerState(controllerStateMock);
        Mockito.verify(handControllerMock, Mockito.never()).setToGive("test");
        Mockito.verify(gameControllerMock, Mockito.never()).setControllerState(handControllerMock);

        Mockito.when(testBoxMock.isActiveTextBox()).thenReturn(false);
        Mockito.when(testBoxMock.isActiveChoice()).thenReturn(true);
        Mockito.when(backpackItemMock.getCommand()).thenReturn("give test");

        backpackController.key(KeyEvent.VK_ENTER, applicationMock);

        Mockito.verify(testBoxMock, Mockito.never()).setActiveChoice(true);
        Mockito.verify(testBoxMock, Mockito.times(2)).closeTextBox();
        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(HandleEffectsCommand.class));
        Mockito.verify(commandInvokerMock).execute();
        Mockito.verify(gameMock, Mockito.times(2)).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControllerMock).setControllerState(controllerStateMock);
        Mockito.verify(handControllerMock).setToGive("test");
        Mockito.verify(gameControllerMock).setControllerState(handControllerMock);

        Mockito.when(testBoxMock.isActiveTextBox()).thenReturn(false);
        Mockito.when(testBoxMock.isActiveChoice()).thenReturn(true);
        Mockito.when(backpackItemMock.getCommand()).thenReturn("not test");

        backpackController.key(KeyEvent.VK_ENTER, applicationMock);

        Mockito.verify(testBoxMock, Mockito.never()).setActiveChoice(true);
        Mockito.verify(testBoxMock, Mockito.times(3)).closeTextBox();
        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(HandleEffectsCommand.class));
        Mockito.verify(commandInvokerMock).execute();
        Mockito.verify(gameMock, Mockito.times(2)).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControllerMock).setControllerState(controllerStateMock);
        Mockito.verify(handControllerMock).setToGive("test");
        Mockito.verify(gameControllerMock).setControllerState(handControllerMock);

        Mockito.when(testBoxMock.isActiveTextBox()).thenReturn(false);
        Mockito.when(testBoxMock.isActiveChoice()).thenReturn(true);
        Mockito.when(backpackItemMock.getCommand()).thenReturn("give");

        backpackController.key(KeyEvent.VK_ENTER, applicationMock);

        Mockito.verify(testBoxMock, Mockito.never()).setActiveChoice(true);
        Mockito.verify(testBoxMock, Mockito.times(4)).closeTextBox();
        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(HandleEffectsCommand.class));
        Mockito.verify(commandInvokerMock).execute();
        Mockito.verify(gameMock, Mockito.times(3)).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControllerMock).setControllerState(controllerStateMock);
        Mockito.verify(handControllerMock).setToGive("");
        Mockito.verify(gameControllerMock, Mockito.times(2)).setControllerState(handControllerMock);

        Mockito.when(testBoxMock.isActiveTextBox()).thenReturn(false);
        Mockito.when(testBoxMock.isActiveChoice()).thenReturn(true);
        Mockito.when(backpackItemMock.getCommand()).thenReturn("use");

        backpackController.key(KeyEvent.VK_ENTER, applicationMock);

        Mockito.verify(testBoxMock, Mockito.never()).setActiveChoice(true);
        Mockito.verify(testBoxMock, Mockito.times(5)).closeTextBox();
        Mockito.verify(testBoxMock).activateTextBox();
        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(HandleEffectsCommand.class));
        Mockito.verify(commandInvokerMock).execute();
        Mockito.verify(gameMock, Mockito.times(4)).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControllerMock, Mockito.times(2)).setControllerState(controllerStateMock);
        Mockito.verify(handControllerMock).setToGive("");
        Mockito.verify(backpackItemMock).setInHand(true);
        Mockito.verify(gameControllerMock, Mockito.times(2)).setControllerState(handControllerMock);

        Mockito.when(testBoxMock.isActiveTextBox()).thenReturn(false);
        Mockito.when(testBoxMock.isActiveChoice()).thenReturn(false);
        Mockito.when(backpackItemMock.getCommand()).thenReturn("not test");

        backpackController.key(KeyEvent.VK_ENTER, applicationMock);

        Mockito.verify(testBoxMock, Mockito.never()).setActiveChoice(true);
        Mockito.verify(testBoxMock, Mockito.times(5)).closeTextBox();
        Mockito.verify(testBoxMock).activateTextBox();
        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(HandleEffectsCommand.class));
        Mockito.verify(commandInvokerMock).execute();
        Mockito.verify(gameMock, Mockito.times(4)).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControllerMock, Mockito.times(2)).setControllerState(controllerStateMock);
        Mockito.verify(handControllerMock).setToGive("");
        Mockito.verify(gameControllerMock, Mockito.times(2)).setControllerState(handControllerMock);

        Mockito.when(testBoxMock.isActiveTextBox()).thenReturn(true);
        Mockito.when(testBoxMock.isActiveChoice()).thenReturn(false);
        Mockito.when(backpackItemMock.getCommand()).thenReturn("not test");

        backpackController.key(KeyEvent.VK_ENTER, applicationMock);

        Mockito.verify(testBoxMock).setActiveChoice(true);
        Mockito.verify(testBoxMock, Mockito.times(5)).closeTextBox();
        Mockito.verify(testBoxMock).activateTextBox();
        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(HandleEffectsCommand.class));
        Mockito.verify(commandInvokerMock).execute();
        Mockito.verify(gameMock, Mockito.times(4)).setCurrentScene(Game.SCENE.LOCATION);
        Mockito.verify(gameControllerMock, Mockito.times(2)).setControllerState(controllerStateMock);
        Mockito.verify(handControllerMock).setToGive("");
        Mockito.verify(gameControllerMock, Mockito.times(2)).setControllerState(handControllerMock);
    }
}

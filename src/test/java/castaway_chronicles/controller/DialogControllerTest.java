package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Commands.AnswerCommand;
import castaway_chronicles.controller.Commands.CommandInvoker;
import castaway_chronicles.controller.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.Commands.TalkCommand;
import castaway_chronicles.controller.game.locationControllers.DialogController;
import castaway_chronicles.controller.game.GameController;

import castaway_chronicles.model.SelectionPanel;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.gameElements.NPC;
import castaway_chronicles.model.game.scene.TextBox;
import castaway_chronicles.model.game.scene.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.VK_ENTER;

public class DialogControllerTest {

    private DialogController dialogController;
    private GameController gameController;
    private Game game;
    private CommandInvoker commandInvokerMock;
    private ControllerState controllerStateMock;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        controllerStateMock = Mockito.mock(ControllerState.class);
        gameController = Mockito.mock(GameController.class);
        Mockito.when(gameController.getModel()).thenReturn(game);
        dialogController = new DialogController(gameController);
        commandInvokerMock = Mockito.mock(CommandInvoker.class);
        Mockito.when(gameController.getCommandInvoker()).thenReturn(commandInvokerMock);
        Mockito.when(gameController.getStandingController()).thenReturn(controllerStateMock);
    }

    @Test
    void testSelectWhenActiveChoice() throws IOException, InterruptedException, URISyntaxException {
        Location currentLocationMock = Mockito.mock(Location.class);
        TextBox textBoxMock = Mockito.mock(TextBox.class);
        NPC npcMock = Mockito.mock(NPC.class);

        Mockito.when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        Mockito.when(game.getTextBox()).thenReturn(textBoxMock);
        Mockito.when(textBoxMock.isActiveChoice()).thenReturn(true);
        Mockito.when(textBoxMock.isActiveTextBox()).thenReturn(true);
        Mockito.when(textBoxMock.getInteractable()).thenReturn(npcMock);
        Mockito.when(npcMock.getEffects()).thenReturn(new ArrayList<>());

        dialogController.key(VK_ENTER, Mockito.mock(Application.class));

        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(AnswerCommand.class));
        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(HandleEffectsCommand.class));
        Mockito.verify(commandInvokerMock, Mockito.times(2)).execute();
    }

    @Test
    void testSelectWhenDialogNotActiveChoice() throws IOException, InterruptedException, URISyntaxException {
        Location currentLocationMock = Mockito.mock(Location.class);
        TextBox textBoxMock = Mockito.mock(TextBox.class);

        Mockito.when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        Mockito.when(game.getTextBox()).thenReturn(textBoxMock);
        Mockito.when(textBoxMock.isActiveChoice()).thenReturn(false);
        Mockito.when(textBoxMock.isActiveTextBox()).thenReturn(true);

        dialogController.select(Mockito.mock(Application.class));

        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(TalkCommand.class));
        Mockito.verify(commandInvokerMock).execute();
        Mockito.verify(gameController, Mockito.never()).setControllerState(controllerStateMock);
    }

    @Test
    void testSelectWhenNotActiveTextBox() throws IOException, InterruptedException, URISyntaxException {
        Location currentLocationMock = Mockito.mock(Location.class);
        TextBox textBoxMock = Mockito.mock(TextBox.class);
        NPC npcMock = Mockito.mock(NPC.class);

        Mockito.when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        Mockito.when(game.getTextBox()).thenReturn(textBoxMock);
        Mockito.when(textBoxMock.isActiveChoice()).thenReturn(false);
        Mockito.when(textBoxMock.isActiveTextBox()).thenReturn(false, true);
        Mockito.when(textBoxMock.getInteractable()).thenReturn(npcMock);
        Mockito.when(npcMock.getEffects()).thenReturn(new ArrayList<>());

        dialogController.select(Mockito.mock(Application.class));

        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(TalkCommand.class));
        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(HandleEffectsCommand.class));
        Mockito.verify(commandInvokerMock, Mockito.times(2)).execute();
        Mockito.verify(gameController, Mockito.never()).setControllerState(controllerStateMock);

        Mockito.when(textBoxMock.isActiveTextBox()).thenReturn(false);

        dialogController.select(Mockito.mock(Application.class));

        Mockito.verify(commandInvokerMock, Mockito.times(2)).setCommand(Mockito.any(TalkCommand.class));
        Mockito.verify(commandInvokerMock, Mockito.times(2)).setCommand(Mockito.any(HandleEffectsCommand.class));
        Mockito.verify(commandInvokerMock, Mockito.times(4)).execute();
        Mockito.verify(gameController).setControllerState(controllerStateMock);
    }

    @Test
    void keyUp() throws IOException, URISyntaxException, InterruptedException {
        Location currentLocationMock = Mockito.mock(Location.class);
        TextBox textBoxMock = Mockito.mock(TextBox.class);
        NPC npcMock = Mockito.mock(NPC.class);
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);

        Mockito.when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        Mockito.when(game.getTextBox()).thenReturn(textBoxMock);
        Mockito.when(textBoxMock.isActiveChoice()).thenReturn(true);
        Mockito.when(textBoxMock.isActiveTextBox()).thenReturn(true);
        Mockito.when(textBoxMock.getInteractable()).thenReturn(npcMock);
        Mockito.when(npcMock.getChoices()).thenReturn(selectionPanelMock);

        dialogController.key(KeyEvent.VK_UP, Mockito.mock(Application.class));

        Mockito.verify(selectionPanelMock).previousEntry();
    }
    @Test
    void keyDown() throws IOException, URISyntaxException, InterruptedException {
        Location currentLocationMock = Mockito.mock(Location.class);
        TextBox textBoxMock = Mockito.mock(TextBox.class);
        NPC npcMock = Mockito.mock(NPC.class);
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);

        Mockito.when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        Mockito.when(game.getTextBox()).thenReturn(textBoxMock);
        Mockito.when(textBoxMock.isActiveChoice()).thenReturn(true);
        Mockito.when(textBoxMock.isActiveTextBox()).thenReturn(true);
        Mockito.when(textBoxMock.getInteractable()).thenReturn(npcMock);
        Mockito.when(npcMock.getChoices()).thenReturn(selectionPanelMock);

        dialogController.key(KeyEvent.VK_DOWN, Mockito.mock(Application.class));

        Mockito.verify(selectionPanelMock).nextEntry();
    }
}

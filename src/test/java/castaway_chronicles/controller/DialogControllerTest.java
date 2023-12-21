package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.AnswerCommand;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.Commands.TalkCommand;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class DialogControllerTest {

    private DialogController dialogController;
    private GameController gameController;
    private Game game;
    private CommandInvoker commandInvokerMock;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        gameController = Mockito.mock(GameController.class);
        Mockito.when(gameController.getModel()).thenReturn(game);
        dialogController = new DialogController(gameController);
        commandInvokerMock = Mockito.mock(CommandInvoker.class);
        when(gameController.getCommandInvoker()).thenReturn(commandInvokerMock);
    }

    @Test
    void testSelectWhenActiveChoice() throws IOException, InterruptedException, URISyntaxException {
        Location currentLocationMock = Mockito.mock(Location.class);
        TextBox textBoxMock = Mockito.mock(TextBox.class);
        NPC npcMock = Mockito.mock(NPC.class);

        when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        when(game.getTextBox()).thenReturn(textBoxMock);
        when(textBoxMock.isActiveChoice()).thenReturn(true);
        when(textBoxMock.isActiveTextBox()).thenReturn(true);
        when(textBoxMock.getInteractable()).thenReturn(npcMock);
        when(npcMock.getEffects()).thenReturn(new ArrayList<>());

        dialogController.key(VK_ENTER, Mockito.mock(Application.class));

        verify(commandInvokerMock).setCommand(any(AnswerCommand.class));
        verify(commandInvokerMock).setCommand(any(HandleEffectsCommand.class));
        verify(commandInvokerMock, times(2)).execute();
    }

    @Test
    void testSelectWhenDialogNotActiveChoice() throws IOException, InterruptedException, URISyntaxException {
        Location currentLocationMock = Mockito.mock(Location.class);
        TextBox textBoxMock = Mockito.mock(TextBox.class);

        when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        when(game.getTextBox()).thenReturn(textBoxMock);
        when(textBoxMock.isActiveChoice()).thenReturn(false);
        when(textBoxMock.isActiveTextBox()).thenReturn(true);

        dialogController.select(mock(Application.class));

        verify(commandInvokerMock).setCommand(any(TalkCommand.class));
        verify(commandInvokerMock, times(1)).execute();
        verify(gameController, times(0)).setControllerState(gameController.getLocationController());
    }

    @Test
    void testSelectWhenNotActiveTextBox() throws IOException, InterruptedException, URISyntaxException {
        Location currentLocationMock = Mockito.mock(Location.class);
        TextBox textBoxMock = Mockito.mock(TextBox.class);
        NPC npcMock = Mockito.mock(NPC.class);

        when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        when(game.getTextBox()).thenReturn(textBoxMock);
        when(textBoxMock.isActiveChoice()).thenReturn(false);
        when(textBoxMock.isActiveTextBox()).thenReturn(false);
        when(textBoxMock.getInteractable()).thenReturn(npcMock);
        when(npcMock.getEffects()).thenReturn(new ArrayList<>());

        dialogController.select(mock(Application.class));

        verify(commandInvokerMock).setCommand(any(TalkCommand.class));
        verify(commandInvokerMock).setCommand(any(HandleEffectsCommand.class));
        verify(commandInvokerMock, times(2)).execute();
        verify(gameController).setControllerState(gameController.getLocationController());
    }

    @Test
    void keyUp() throws IOException, URISyntaxException, InterruptedException {
        Location currentLocationMock = Mockito.mock(Location.class);
        TextBox textBoxMock = Mockito.mock(TextBox.class);
        NPC npcMock = Mockito.mock(NPC.class);
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);

        when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        when(game.getTextBox()).thenReturn(textBoxMock);
        when(textBoxMock.isActiveChoice()).thenReturn(true);
        when(textBoxMock.isActiveTextBox()).thenReturn(true);
        when(textBoxMock.getInteractable()).thenReturn(npcMock);
        when(npcMock.getChoices()).thenReturn(selectionPanelMock);

        dialogController.key(KeyEvent.VK_UP, Mockito.mock(Application.class));

        verify(selectionPanelMock,times(1)).previousEntry();
    }
    @Test
    void keyDown() throws IOException, URISyntaxException, InterruptedException {
        Location currentLocationMock = Mockito.mock(Location.class);
        TextBox textBoxMock = Mockito.mock(TextBox.class);
        NPC npcMock = Mockito.mock(NPC.class);
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);

        when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        when(game.getTextBox()).thenReturn(textBoxMock);
        when(textBoxMock.isActiveChoice()).thenReturn(true);
        when(textBoxMock.isActiveTextBox()).thenReturn(true);
        when(textBoxMock.getInteractable()).thenReturn(npcMock);
        when(npcMock.getChoices()).thenReturn(selectionPanelMock);

        dialogController.key(KeyEvent.VK_DOWN, Mockito.mock(Application.class));

        verify(selectionPanelMock,times(1)).nextEntry();
    }
}

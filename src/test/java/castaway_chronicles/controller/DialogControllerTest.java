package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.AnswerCommand;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.Commands.TalkCommand;
import castaway_chronicles.controller.game.LocationControllers.DialogController;
import castaway_chronicles.controller.game.GameController;

import castaway_chronicles.model.SelectionPanel;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.NPC;
import castaway_chronicles.model.game.scene.TextDisplay;
import castaway_chronicles.model.game.scene.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class DialogControllerTest {

    private DialogController dialogController;
    private GameController gameController;
    private Game game;

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        gameController = new GameController(game);
        dialogController = new DialogController(gameController);
    }

    @Test
    void testSelectWhenActiveChoice() throws IOException, InterruptedException, URISyntaxException {
        CommandInvoker commandInvokerMock = Mockito.mock(CommandInvoker.class);
        gameController.setCommandInvoker(commandInvokerMock);

        Location currentLocationMock = Mockito.mock(Location.class);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        NPC npcMock = Mockito.mock(NPC.class);

        when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        when(currentLocationMock.getTextDisplay()).thenReturn(textDisplayMock);
        when(textDisplayMock.isActiveChoice()).thenReturn(true);
        when(textDisplayMock.isActiveTextBox()).thenReturn(true);
        when(textDisplayMock.getElement()).thenReturn(npcMock);
        when(npcMock.getEffects()).thenReturn(new ArrayList<>());

        dialogController.select(Mockito.mock(Application.class));

        verify(commandInvokerMock).setCommand(any(AnswerCommand.class));
        verify(commandInvokerMock).setCommand(any(HandleEffectsCommand.class));
        verify(commandInvokerMock, times(2)).execute();
    }

    @Test
    void testSelectWhenDialogNotActiveChoice() throws IOException, InterruptedException, URISyntaxException {
        CommandInvoker commandInvokerMock = Mockito.mock(CommandInvoker.class);
        gameController.setCommandInvoker(commandInvokerMock);

        Location currentLocationMock = Mockito.mock(Location.class);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);

        when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        when(currentLocationMock.getTextDisplay()).thenReturn(textDisplayMock);
        when(textDisplayMock.isActiveChoice()).thenReturn(false);
        when(textDisplayMock.isActiveTextBox()).thenReturn(true);

        dialogController.select(mock(Application.class));

        verify(commandInvokerMock).setCommand(any(TalkCommand.class));
        verify(commandInvokerMock, times(1)).execute();
    }

    @Test
    void keyUp() {
        CommandInvoker commandInvokerMock = Mockito.mock(CommandInvoker.class);
        gameController.setCommandInvoker(commandInvokerMock);

        Location currentLocationMock = Mockito.mock(Location.class);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        NPC npcMock = Mockito.mock(NPC.class);
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);

        when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        when(currentLocationMock.getTextDisplay()).thenReturn(textDisplayMock);
        when(textDisplayMock.isActiveChoice()).thenReturn(true);
        when(textDisplayMock.isActiveTextBox()).thenReturn(true);
        when(textDisplayMock.getElement()).thenReturn(npcMock);
        when(npcMock.getChoices()).thenReturn(selectionPanelMock);

        dialogController.keyUp();

        verify(selectionPanelMock,times(1)).previousEntry();
    }
    @Test
    void keyDown() {
        CommandInvoker commandInvokerMock = Mockito.mock(CommandInvoker.class);
        gameController.setCommandInvoker(commandInvokerMock);

        Location currentLocationMock = Mockito.mock(Location.class);
        TextDisplay textDisplayMock = Mockito.mock(TextDisplay.class);
        NPC npcMock = Mockito.mock(NPC.class);
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);

        when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        when(currentLocationMock.getTextDisplay()).thenReturn(textDisplayMock);
        when(textDisplayMock.isActiveChoice()).thenReturn(true);
        when(textDisplayMock.isActiveTextBox()).thenReturn(true);
        when(textDisplayMock.getElement()).thenReturn(npcMock);
        when(npcMock.getChoices()).thenReturn(selectionPanelMock);

        dialogController.keyDown();

        verify(selectionPanelMock,times(1)).nextEntry();
    }

//    @Test
//    void testSelectWhenNotDialog() throws IOException, InterruptedException {
//        CommandInvoker commandInvokerMock = Mockito.mock(CommandInvoker.class);
//        gameController.setCommandInvoker(commandInvokerMock);
//
//        Location currentLocationMock = Mockito.mock(Location.class);
//        DialogState dialogStateMock = Mockito.mock(DialogState.class);
//        NPC npcMock = Mockito.mock(NPC.class);
//        NPCDialog npcDialogMock = Mockito.mock(NPCDialog.class);
//
//        when(game.getCurrentLocation()).thenReturn(currentLocationMock);
//        when(currentLocationMock.getDialogState()).thenReturn(dialogStateMock);
//        when(dialogStateMock.isActiveChoice()).thenReturn(false);
//        when(dialogStateMock.isActiveDialog()).thenReturn(false);
//        when(dialogStateMock.getNPCDialog()).thenReturn(npcMock);
//        when(npcMock.getDialogState()).thenReturn(npcDialogMock);
//        when(npcDialogMock.getEffects()).thenReturn(List.of("Test"));
//
//        dialogController.select(mock(Application.class));
//
//        verify(commandInvokerMock).setCommand(any(TalkCommand.class));
//        verify(commandInvokerMock).setCommand(new HandleEffectsCommand(game, List.of("Test")));
//
//        verify(commandInvokerMock, times(2)).execute();
//    }
}

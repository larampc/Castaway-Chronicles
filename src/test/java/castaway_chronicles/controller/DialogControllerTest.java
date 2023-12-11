package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.game.Commands.AnswerCommand;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.HandleEffectsCommand;
import castaway_chronicles.controller.game.Commands.TalkCommand;
import castaway_chronicles.controller.game.ControllerStates.DialogController;
import castaway_chronicles.controller.game.GameController;

import castaway_chronicles.model.game.Game;
import castaway_chronicles.model.game.elements.Background;
import castaway_chronicles.model.game.elements.MainChar;
import castaway_chronicles.model.game.elements.NPC;
import castaway_chronicles.model.game.elements.NPCDialog;
import castaway_chronicles.model.game.scene.DialogState;
import castaway_chronicles.model.game.scene.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void testSelectWhenActiveChoice() throws IOException, InterruptedException {
        CommandInvoker commandInvokerMock = Mockito.mock(CommandInvoker.class);
        gameController.setCommandInvoker(commandInvokerMock);

        Location currentLocationMock = Mockito.mock(Location.class);
        DialogState dialogStateMock = Mockito.mock(DialogState.class);

        when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        when(currentLocationMock.getDialogState()).thenReturn(dialogStateMock);
        when(dialogStateMock.isActiveChoice()).thenReturn(true);
        when(dialogStateMock.isActiveDialog()).thenReturn(true);

        dialogController.select(Mockito.mock(Application.class));

        verify(commandInvokerMock).setCommand(any(AnswerCommand.class));
        verify(commandInvokerMock).execute();
    }

    @Test
    void testSelectWhenDialogNotActiveChoice() throws IOException, InterruptedException {
        CommandInvoker commandInvokerMock = Mockito.mock(CommandInvoker.class);
        gameController.setCommandInvoker(commandInvokerMock);

        Location currentLocationMock = Mockito.mock(Location.class);
        DialogState dialogStateMock = Mockito.mock(DialogState.class);

        when(game.getCurrentLocation()).thenReturn(currentLocationMock);
        when(currentLocationMock.getDialogState()).thenReturn(dialogStateMock);
        when(dialogStateMock.isActiveChoice()).thenReturn(false);
        when(dialogStateMock.isActiveDialog()).thenReturn(true);

        dialogController.select(mock(Application.class));

        verify(commandInvokerMock).setCommand(any(TalkCommand.class));
        verify(commandInvokerMock, times(1)).execute();
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

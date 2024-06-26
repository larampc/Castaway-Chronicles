package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Commands.CommandInvoker;
import castaway_chronicles.controller.Commands.GetSideOptionCommand;
import castaway_chronicles.controller.mainPage.MainMenuController;
import castaway_chronicles.controller.mainPage.MainPageController;
import castaway_chronicles.model.SelectionPanel;
import castaway_chronicles.model.mainPage.MainMenu;
import castaway_chronicles.model.mainPage.MainPage;
import castaway_chronicles.states.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainMenuControllerTest {
    private Application applicationMock;
    private MainPage mainPageMock;
    private MainMenu mainMenuMock;
    private MainPageController mainPageControllerMock;
    private MainMenuController mainMenuController;

    @BeforeEach
    void setUp(){
        applicationMock = Mockito.mock(Application.class);
        mainPageMock = Mockito.mock(MainPage.class);
        mainMenuMock = Mockito.mock(MainMenu.class);
        Mockito.when(mainPageMock.getMainMenu()).thenReturn(mainMenuMock);
        mainPageControllerMock = Mockito.mock(MainPageController.class);
        Mockito.when(mainPageControllerMock.getModel()).thenReturn(mainPageMock);
        mainMenuController = new MainMenuController(mainPageControllerMock);
    }

    @Test
    void keyUp() throws IOException, URISyntaxException, InterruptedException {
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);
        Mockito.when(mainMenuMock.getSelectionPanel()).thenReturn(selectionPanelMock);

        Mockito.when(mainMenuMock.isSelectedContinue()).thenReturn(false);
        Mockito.when(mainMenuMock.canContinue()).thenReturn(true);
        mainMenuController.key(KeyEvent.VK_UP, applicationMock);
        Mockito.verify(selectionPanelMock).previousEntry();

        Mockito.when(mainMenuMock.isSelectedContinue()).thenReturn(true);
        Mockito.when(mainMenuMock.canContinue()).thenReturn(true);
        mainMenuController.key(KeyEvent.VK_UP, applicationMock);
        Mockito.verify(selectionPanelMock, Mockito.times(2)).previousEntry();

        Mockito.when(mainMenuMock.isSelectedContinue()).thenReturn(false);
        Mockito.when(mainMenuMock.canContinue()).thenReturn(false);
        mainMenuController.key(KeyEvent.VK_UP, applicationMock);
        Mockito.verify(selectionPanelMock, Mockito.times(3)).previousEntry();

        Mockito.when(mainMenuMock.isSelectedContinue()).thenReturn(true);
        Mockito.when(mainMenuMock.canContinue()).thenReturn(false);
        mainMenuController.key(KeyEvent.VK_UP, applicationMock);
        Mockito.verify(selectionPanelMock, Mockito.times(5)).previousEntry();
    }

    @Test
    void keyDown() throws IOException, URISyntaxException, InterruptedException {
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);
        Mockito.when(mainMenuMock.getSelectionPanel()).thenReturn(selectionPanelMock);

        Mockito.when(mainMenuMock.isSelectedContinue()).thenReturn(false);
        Mockito.when(mainMenuMock.canContinue()).thenReturn(true);
        mainMenuController.key(KeyEvent.VK_DOWN, applicationMock);
        Mockito.verify(selectionPanelMock).nextEntry();

        Mockito.when(mainMenuMock.isSelectedContinue()).thenReturn(true);
        Mockito.when(mainMenuMock.canContinue()).thenReturn(true);
        mainMenuController.key(KeyEvent.VK_DOWN, applicationMock);
        Mockito.verify(selectionPanelMock, Mockito.times(2)).nextEntry();

        Mockito.when(mainMenuMock.isSelectedContinue()).thenReturn(false);
        Mockito.when(mainMenuMock.canContinue()).thenReturn(false);
        mainMenuController.key(KeyEvent.VK_DOWN, applicationMock);
        Mockito.verify(selectionPanelMock, Mockito.times(3)).nextEntry();

        Mockito.when(mainMenuMock.isSelectedContinue()).thenReturn(true);
        Mockito.when(mainMenuMock.canContinue()).thenReturn(false);
        mainMenuController.key(KeyEvent.VK_DOWN, applicationMock);
        Mockito.verify(selectionPanelMock, Mockito.times(5)).nextEntry();
    }

    @Test
    void keySide() throws IOException, URISyntaxException, InterruptedException {
        CommandInvoker commandInvokerMock = Mockito.mock(CommandInvoker.class);
        SelectionPanel selectionPanelMock = Mockito.mock(SelectionPanel.class);
        Mockito.when(mainMenuMock.getSelectionPanel()).thenReturn(selectionPanelMock);
        Mockito.when(mainMenuMock.isSelectedContinue()).thenReturn(false);
        Mockito.when(mainMenuMock.canContinue()).thenReturn(true);
        MainMenuController mainMenuController2 = new MainMenuController(mainPageControllerMock){
            @Override
            public CommandInvoker getCommandInvoker() {
                return commandInvokerMock;
            }
        };

        mainMenuController2.key(KeyEvent.VK_RIGHT, applicationMock);
        Mockito.verify(commandInvokerMock).setCommand(Mockito.any(GetSideOptionCommand.class));
        Mockito.verify(commandInvokerMock).execute();
        Mockito.verify(selectionPanelMock, Mockito.never()).previousEntry();

        Mockito.when(mainMenuMock.isSelectedContinue()).thenReturn(true);
        Mockito.when(mainMenuMock.canContinue()).thenReturn(true);
        mainMenuController2.key(KeyEvent.VK_RIGHT, applicationMock);
        Mockito.verify(commandInvokerMock, Mockito.times(2)).setCommand(Mockito.any(GetSideOptionCommand.class));
        Mockito.verify(commandInvokerMock, Mockito.times(2)).execute();
        Mockito.verify(selectionPanelMock, Mockito.never()).previousEntry();

        Mockito.when(mainMenuMock.isSelectedContinue()).thenReturn(false);
        Mockito.when(mainMenuMock.canContinue()).thenReturn(false);
        mainMenuController2.key(KeyEvent.VK_LEFT, applicationMock);
        Mockito.verify(commandInvokerMock, Mockito.times(3)).setCommand(Mockito.any(GetSideOptionCommand.class));
        Mockito.verify(commandInvokerMock, Mockito.times(3)).execute();
        Mockito.verify(selectionPanelMock, Mockito.never()).previousEntry();

        Mockito.when(mainMenuMock.isSelectedContinue()).thenReturn(true);
        Mockito.when(mainMenuMock.canContinue()).thenReturn(false);
        mainMenuController2.key(KeyEvent.VK_LEFT, applicationMock);
        Mockito.verify(commandInvokerMock, Mockito.times(4)).setCommand(Mockito.any(GetSideOptionCommand.class));
        Mockito.verify(commandInvokerMock, Mockito.times(4)).execute();
        Mockito.verify(selectionPanelMock, Mockito.times(2)).previousEntry();

        assertEquals(CommandInvoker.class, mainMenuController.getCommandInvoker().getClass());
    }

    @Test
    void select() throws IOException, URISyntaxException, InterruptedException {
        ControllerState controllerStateMock = Mockito.mock(ControllerState.class);
        Mockito.when(mainPageControllerMock.getEndingPageController()).thenReturn(controllerStateMock);

        Mockito.when(mainMenuMock.canContinue()).thenReturn(false);
        Mockito.when(mainMenuMock.isSelectedExit()).thenReturn(false);
        Mockito.when(mainMenuMock.isSelectedStart()).thenReturn(false);
        Mockito.when(mainMenuMock.isSelectedContinue()).thenReturn(false);
        Mockito.when(mainMenuMock.isSelectedEndings()).thenReturn(false);
        mainMenuController.key(KeyEvent.VK_ENTER, applicationMock);
        Mockito.verify(applicationMock, Mockito.never()).setState(null);
        Mockito.verify(applicationMock, Mockito.never()).setState(Mockito.any(GameState.class));
        Mockito.verify(mainPageMock, Mockito.never()).setCurrent(MainPage.PAGE.ENDINGS);
        Mockito.verify(mainPageControllerMock, Mockito.never()).setCurrent(controllerStateMock);

        Mockito.when(mainMenuMock.canContinue()).thenReturn(true);
        mainMenuController.key(KeyEvent.VK_ENTER, applicationMock);
        Mockito.verify(applicationMock, Mockito.never()).setState(null);
        Mockito.verify(applicationMock, Mockito.never()).setState(Mockito.any(GameState.class));
        Mockito.verify(mainPageMock, Mockito.never()).setCurrent(MainPage.PAGE.ENDINGS);
        Mockito.verify(mainPageControllerMock, Mockito.never()).setCurrent(controllerStateMock);

        Mockito.when(mainMenuMock.canContinue()).thenReturn(false);
        Mockito.when(mainMenuMock.isSelectedContinue()).thenReturn(true);
        mainMenuController.key(KeyEvent.VK_ENTER, applicationMock);
        Mockito.verify(applicationMock, Mockito.never()).setState(null);
        Mockito.verify(applicationMock, Mockito.never()).setState(Mockito.any(GameState.class));
        Mockito.verify(mainPageMock, Mockito.never()).setCurrent(MainPage.PAGE.ENDINGS);
        Mockito.verify(mainPageControllerMock, Mockito.never()).setCurrent(controllerStateMock);

        Mockito.when(mainMenuMock.canContinue()).thenReturn(false);
        Mockito.when(mainMenuMock.isSelectedExit()).thenReturn(true);
        Mockito.when(mainMenuMock.isSelectedContinue()).thenReturn(false);
        mainMenuController.key(KeyEvent.VK_ENTER, applicationMock);
        Mockito.verify(applicationMock).setState(null);
        Mockito.verify(applicationMock, Mockito.never()).setState(Mockito.any(GameState.class));
        Mockito.verify(mainPageMock, Mockito.never()).setCurrent(MainPage.PAGE.ENDINGS);
        Mockito.verify(mainPageControllerMock, Mockito.never()).setCurrent(controllerStateMock);

        Mockito.when(mainMenuMock.isSelectedExit()).thenReturn(false);
        Mockito.when(mainMenuMock.isSelectedStart()).thenReturn(true);
        mainMenuController.key(KeyEvent.VK_ENTER, applicationMock);
        Mockito.verify(applicationMock).setState(null);
        Mockito.verify(applicationMock).setState(Mockito.any(GameState.class));
        Mockito.verify(mainPageMock, Mockito.never()).setCurrent(MainPage.PAGE.ENDINGS);
        Mockito.verify(mainPageControllerMock, Mockito.never()).setCurrent(controllerStateMock);

        Mockito.when(mainMenuMock.isSelectedStart()).thenReturn(false);
        Mockito.when(mainMenuMock.isSelectedEndings()).thenReturn(true);
        mainMenuController.key(KeyEvent.VK_ENTER, applicationMock);
        Mockito.verify(applicationMock).setState(null);
        Mockito.verify(applicationMock).setState(Mockito.any(GameState.class));
        Mockito.verify(mainPageMock).setCurrent(MainPage.PAGE.ENDINGS);
        Mockito.verify(mainPageControllerMock).setCurrent(controllerStateMock);
    }
}

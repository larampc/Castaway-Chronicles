package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.controller.mainPage.EndingPageController;
import castaway_chronicles.controller.mainPage.MainMenuController;
import castaway_chronicles.controller.mainPage.MainPageController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.mainPage.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MainPageControllerTest {
    private Application applicationMock;
    private MainPage mainPageMock;
    private MainPageController mainPageController;

    @BeforeEach
    void setUp(){
        applicationMock = Mockito.mock(Application.class);
        mainPageMock = Mockito.mock(MainPage.class);
        Mockito.when(mainPageMock.getCurrent()).thenReturn(MainPage.PAGE.MENU);
        mainPageController = new MainPageController(mainPageMock);
    }

    @Test
    void MainPageControllerContent() {
        assertEquals(MainMenuController.class, mainPageController.getMainMenuController().getClass());
        assertEquals(EndingPageController.class, mainPageController.getEndingPageController().getClass());
        assertEquals(MainMenuController.class, mainPageController.getCurrent().getClass());
        Mockito.when(mainPageMock.getCurrent()).thenReturn(MainPage.PAGE.ENDINGS);
        mainPageController = new MainPageController(mainPageMock);
        assertEquals(EndingPageController.class, mainPageController.getCurrent().getClass());
    }

    @Test
    void current() {
        ControllerState controllerStateMock = Mockito.mock(ControllerState.class);
        assertNotEquals(controllerStateMock, mainPageController.getCurrent());
        mainPageController.setCurrent(controllerStateMock);
        assertEquals(controllerStateMock, mainPageController.getCurrent());
    }

    @Test
    void step() throws IOException, URISyntaxException, InterruptedException {
        ControllerState controllerStateMock = Mockito.mock(ControllerState.class);
        InputEvent inputEventMock = Mockito.mock(InputEvent.class);
        KeyEvent keyEventMock = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEventMock.getKeyCode()).thenReturn(20);
        MouseEvent mouseEventMock = Mockito.mock(MouseEvent.class);
        Mockito.when(mouseEventMock.getX()).thenReturn(8);
        Mockito.when(mouseEventMock.getY()).thenReturn(16);
        mainPageController.setCurrent(controllerStateMock);
        assertEquals(controllerStateMock, mainPageController.getCurrent());

        mainPageController.step(applicationMock, inputEventMock, 0);
        Mockito.verify(controllerStateMock, Mockito.never()).key(Mockito.anyInt(), Mockito.any());
        Mockito.verify(controllerStateMock, Mockito.never()).click(Mockito.any(), Mockito.any());

        mainPageController.step(applicationMock, keyEventMock, 0);
        Mockito.verify(controllerStateMock).key(20, applicationMock);
        Mockito.verify(controllerStateMock, Mockito.never()).click(Mockito.any(), Mockito.any());

        mainPageController.step(applicationMock, mouseEventMock, 0);
        Mockito.verify(controllerStateMock).key(20, applicationMock);
        Mockito.verify(controllerStateMock).click(new Position(mouseEventMock.getX()/4, mouseEventMock.getY()/4), applicationMock);
    }
}

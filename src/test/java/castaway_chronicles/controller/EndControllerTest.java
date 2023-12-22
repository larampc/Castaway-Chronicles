package castaway_chronicles.controller;

import castaway_chronicles.Application;
import castaway_chronicles.model.Ending;
import castaway_chronicles.model.mainPage.MainPage;
import castaway_chronicles.states.MainPageState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EndControllerTest {
    private Application applicationMock;
    private EndController endController;
    private MainPage mainPageMock;
    private Ending endingMock;
    @BeforeEach
    void setUp() {
        applicationMock = Mockito.mock(Application.class);
        endingMock = Mockito.mock(Ending.class);
        mainPageMock = Mockito.mock(MainPage.class);
        endController = new EndController(endingMock){
            @Override
            protected MainPage getMainPage(){
                return mainPageMock;
            }
        };
    }

    @Test
    void EndControllerContent() {
        assertEquals(endingMock, endController.getModel());
        assertEquals(MainPage.class, new EndController(endingMock).getMainPage().getClass());
    }

    @Test
    void step1() throws IOException {
        InputEvent inputEventMock = Mockito.mock(InputEvent.class);

        Mockito.when(endingMock.getCurrent()).thenReturn(0);
        Mockito.when(endingMock.getMax()).thenReturn(0);
        endController.step(applicationMock, inputEventMock, 200);
        Mockito.verify(endingMock, Mockito.times(0)).setNext();

        Mockito.when(endingMock.getCurrent()).thenReturn(0);
        Mockito.when(endingMock.getMax()).thenReturn(1);
        endController.step(applicationMock, inputEventMock, 200);
        Mockito.verify(endingMock, Mockito.times(0)).setNext();

        Mockito.when(endingMock.getCurrent()).thenReturn(0);
        Mockito.when(endingMock.getMax()).thenReturn(0);
        endController.step(applicationMock, inputEventMock, 201);
        Mockito.verify(endingMock, Mockito.times(0)).setNext();

        Mockito.when(endingMock.getCurrent()).thenReturn(0);
        Mockito.when(endingMock.getMax()).thenReturn(1);
        endController.step(applicationMock, inputEventMock, 201);
        Mockito.verify(endingMock).setNext();

        endController.step(applicationMock, inputEventMock, 300);
        Mockito.verify(endingMock, Mockito.times(1)).setNext();

        endController.step(applicationMock, inputEventMock, 402);
        Mockito.verify(endingMock, Mockito.times(2)).setNext();
    }

    @Test
    void step2() throws IOException {
        KeyEvent inputEventMock = Mockito.mock(KeyEvent.class);
        Mockito.when(mainPageMock.getCurrent()).thenReturn(MainPage.PAGE.MENU);

        Mockito.when(endingMock.getCurrent()).thenReturn(0);
        Mockito.when(endingMock.getMax()).thenReturn(1);
        Mockito.when(inputEventMock.getKeyCode()).thenReturn(KeyEvent.VK_E);
        endController.step(applicationMock, inputEventMock, 0);
        Mockito.verify(mainPageMock, Mockito.times(0)).setCurrent(MainPage.PAGE.ENDINGS);
        Mockito.verify(applicationMock, Mockito.times(0)).setState(Mockito.any(MainPageState.class));

        Mockito.when(endingMock.getCurrent()).thenReturn(0);
        Mockito.when(endingMock.getMax()).thenReturn(0);
        Mockito.when(inputEventMock.getKeyCode()).thenReturn(KeyEvent.VK_E);
        endController.step(applicationMock, inputEventMock, 0);
        Mockito.verify(mainPageMock, Mockito.times(0)).setCurrent(MainPage.PAGE.ENDINGS);
        Mockito.verify(applicationMock, Mockito.times(0)).setState(Mockito.any(MainPageState.class));

        Mockito.when(endingMock.getCurrent()).thenReturn(0);
        Mockito.when(endingMock.getMax()).thenReturn(1);
        Mockito.when(inputEventMock.getKeyCode()).thenReturn(KeyEvent.VK_ENTER);
        endController.step(applicationMock, inputEventMock, 0);
        Mockito.verify(mainPageMock, Mockito.times(0)).setCurrent(MainPage.PAGE.ENDINGS);
        Mockito.verify(applicationMock, Mockito.times(0)).setState(Mockito.any(MainPageState.class));

        Mockito.when(endingMock.getCurrent()).thenReturn(0);
        Mockito.when(endingMock.getMax()).thenReturn(0);
        Mockito.when(inputEventMock.getKeyCode()).thenReturn(KeyEvent.VK_ENTER);
        endController.step(applicationMock, inputEventMock, 0);
        Mockito.verify(mainPageMock).setCurrent(MainPage.PAGE.ENDINGS);
        Mockito.verify(applicationMock).setState(Mockito.any(MainPageState.class));
    }
}


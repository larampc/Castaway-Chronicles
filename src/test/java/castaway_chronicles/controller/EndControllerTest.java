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
    void stepForward() throws IOException {
        Mockito.when(endingMock.getMax()).thenReturn(10);
        Mockito.when(endingMock.getCurrent()).thenReturn(5);
        InputEvent inputEventMock = Mockito.mock(InputEvent.class);

        endController.step(applicationMock, inputEventMock, 300);

        Mockito.verify(endingMock).setNext();
    }
    @Test
    void LastFrame() throws IOException {
        Mockito.when(endingMock.getMax()).thenReturn(10);
        Mockito.when(endingMock.getCurrent()).thenReturn(10);
        InputEvent inputEventMock = Mockito.mock(InputEvent.class);

        endController.step(applicationMock, inputEventMock, 300);

        Mockito.verify(endingMock,Mockito.never()).setNext();

        KeyEvent keyEventMock = Mockito.mock(KeyEvent.class);
        Mockito.when(keyEventMock.getKeyCode()).thenReturn(KeyEvent.VK_ENTER);

        //endController.step(applicationMock, keyEventMock, 600);

        Mockito.verify(mainPageMock).setCurrent(MainPage.PAGE.ENDINGS);
        Mockito.verify(applicationMock).setState(Mockito.any(MainPageState.class));
    }
}

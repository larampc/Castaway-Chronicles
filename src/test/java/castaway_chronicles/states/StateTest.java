package castaway_chronicles.states;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Controller;
import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.view.Viewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.InputEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class StateTest {
    State state;
    Viewer viewerMock;
    Controller controllerMock;
    Game modelMock;

    @BeforeEach
    void init() {
        viewerMock = Mockito.mock(Viewer.class);
        controllerMock = Mockito.mock(Controller.class);
        modelMock = Mockito.mock(Game.class);
        state = new State(modelMock) {
            @Override
            protected Viewer getViewer() {
                return viewerMock;
            }

            @Override
            protected Controller getController() {
                return controllerMock;
            }
        };
    }

    @Test
    void step() throws IOException, URISyntaxException, InterruptedException {
        Application applicationMock = Mockito.mock(Application.class);
        GUI guiMock = Mockito.mock(GUI.class);
        InputEvent inputEventMock = Mockito.mock(InputEvent.class);
        Mockito.when(guiMock.getNextAction()).thenReturn(inputEventMock);
        state.step(applicationMock, guiMock, 0);
        Mockito.verify(guiMock, Mockito.times(1)).getNextAction();
        Mockito.verify(controllerMock, Mockito.times(1)).step(applicationMock, inputEventMock, 0);
        Mockito.verify(viewerMock, Mockito.times(1)).draw(guiMock);
    }
}

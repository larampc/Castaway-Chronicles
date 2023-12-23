package castaway_chronicles.states;

import castaway_chronicles.controller.EndController;
import castaway_chronicles.model.Ending;
import castaway_chronicles.view.EndViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EndStateTest {
    EndState endState;
    Ending modelMock;

    @BeforeEach
    void init() {
        modelMock = Mockito.mock(Ending.class);
        endState = new EndState(modelMock);
    }

    @Test
    void MainPageContent() {
        assertEquals(modelMock, endState.getModel());
        assertEquals(EndViewer.class, endState.getViewer().getClass());
        assertEquals(EndController.class, endState.getController().getClass());
    }
}

package castaway_chronicles.states;

import castaway_chronicles.controller.mainPage.MainPageController;
import castaway_chronicles.model.mainPage.MainPage;
import castaway_chronicles.view.mainPage.MainPageViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageStateTest {
    MainPageState mainPageState;
    MainPage modelMock;

    @BeforeEach
    void init() {
        modelMock = Mockito.mock(MainPage.class);
        Mockito.when(modelMock.getCurrent()).thenReturn(MainPage.PAGE.MENU);
        mainPageState = new MainPageState(modelMock);
    }

    @Test
    void MainPageContent() {
        Mockito.verify(modelMock, Mockito.times(1)).getCurrent();
        assertEquals(modelMock, mainPageState.getModel());
        assertEquals(MainPageViewer.class, mainPageState.getViewer().getClass());
        assertEquals(MainPageController.class, mainPageState.getController().getClass());
    }
}

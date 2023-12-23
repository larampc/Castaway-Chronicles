package castaway_chronicles.states;

import castaway_chronicles.controller.game.GameController;
import castaway_chronicles.model.game.Game;
import castaway_chronicles.view.game.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameStateTest {
    GameState gameState;
    Game modelMock;

    @BeforeEach
    void init() {
        modelMock = Mockito.mock(Game.class);
        gameState = new GameState(modelMock);
    }

    @Test
    void MainPageContent() {
        assertEquals(modelMock, gameState.getModel());
        assertEquals(GameViewer.class, gameState.getViewer().getClass());
        assertEquals(GameController.class, gameState.getController().getClass());
    }
}

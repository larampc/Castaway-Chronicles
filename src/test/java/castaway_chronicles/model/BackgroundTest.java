package castaway_chronicles.model;

import castaway_chronicles.model.game.elements.Background;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BackgroundTest {
    @Test
    void isIsloopable() {
        Background background1 = new Background(0, 0, 10, 10, "test1", true);
        Background background2 = new Background(0, 0, 10, 10, "test2", false);
        assertTrue(background1.isLoopable());
        assertFalse(background2.isLoopable());
    }

    @Test
    void setName() {
        Background background1 = new Background(0, 0, 10, 10, "test1", true);
        assertEquals("test1", background1.getName());
        background1.setName("test3");
        assertEquals("test3", background1.getName());
    }
}

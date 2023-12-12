package castaway_chronicles.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import static org.junit.jupiter.api.Assertions.*;

public class EndingTest {
    Ending end;
    @BeforeEach
    void setUp() throws URISyntaxException {
        end = new Ending("drink");
    }

    @Test
    void getters() {
        assertEquals(26, end.getMax());
        assertEquals(1, end.getCurrent());
        assertEquals("drink", end.getName());
        assertEquals("drink_0001", end.getCurrentFrame());
    }

    @Test
    void setNext() {
        for (int i = 1; i <= 26; i++) {
            assertEquals(i, end.getCurrent());
            end.setNext();
        }
    }
}

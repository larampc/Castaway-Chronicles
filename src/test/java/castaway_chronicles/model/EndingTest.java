package castaway_chronicles.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class EndingTest {
    Ending end;
    File newDir;
    @BeforeEach
    void setUp() {
        newDir = new File("src/main/resources/Endings/endingTest");
        boolean success = newDir.mkdir();
        assertTrue(success);
        try {
            success = new File(newDir.getPath() + "/endingTest_0001").createNewFile();
            assertTrue(success);
            success = new File(newDir.getPath() + "/endingTest_0002").createNewFile();
            assertTrue(success);
            success = new File(newDir.getPath() + "/endingTest_0003").createNewFile();
            assertTrue(success);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        end = new Ending("endingTest");
    }
    @AfterEach
    void tearDown() {
        for(File f : Objects.requireNonNull(newDir.listFiles())){
            boolean delete = f.delete();
            assertTrue(delete);
        }
        boolean delete = newDir.delete();
        assertTrue(delete);
    }

    @Test
    void getters() {
        assertEquals(3, end.getMax());
        assertEquals(1, end.getCurrent());
        assertEquals("endingTest", end.getName());
        assertEquals("endingTest_0001", end.getCurrentFrame());
    }

    @Test
    void setNext() {
        for (int i = 1; i <= 3; i++) {
            assertEquals(i, end.getCurrent());
            end.setNext();
        }
    }
}

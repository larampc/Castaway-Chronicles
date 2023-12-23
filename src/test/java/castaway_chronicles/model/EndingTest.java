package castaway_chronicles.model;

import castaway_chronicles.ResourceManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class EndingTest {
    Ending end;
    ResourceManager resourceManagerMock;
    @BeforeEach
    void setUp() {
        resourceManagerMock = Mockito.mock(ResourceManager.class);
        Mockito.when(resourceManagerMock.countFiles("Endings/endingTest")).thenReturn(3);
        end = new Ending("endingTest"){
            @Override
            public ResourceManager getResourceManager(){
                return resourceManagerMock;
            }
        };
    }
    @Test
    void Ending(){
        Mockito.verify(resourceManagerMock).countFiles("Endings/endingTest");
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

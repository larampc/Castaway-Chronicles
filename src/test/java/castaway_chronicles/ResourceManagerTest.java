package castaway_chronicles;

import org.junit.jupiter.api.*;

import java.io.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ResourceManagerTest {

    private File newFile;
    @BeforeAll
    void init() throws IOException {
//        newFile = new File(Paths.get("src/main/resources/resourceTest").toString());
    }

    @AfterAll
    void tearDown() throws IOException {
//        newFile.delete();
    }

    @Test
    void readCurrentTimeResourceFile() {
        ResourceManager resourceManager = ResourceManager.getInstance();
        resourceManager.setPath("Testing/resourceTest");
        List<String> lines = resourceManager.readCurrentTimeResourceFile();
        assertEquals(List.of("lorem ipsem","testing","resource","manager"),lines);
    }

    @Test
    void failToReadCurrentTimeResourceFile() {
        ResourceManager resourceManager = ResourceManager.getInstance();
        resourceManager.setPath("Testing/resourceTest2");

        Assertions.assertThrows(RuntimeException.class, resourceManager::readCurrentTimeResourceFile);
    }
}

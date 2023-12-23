package castaway_chronicles;

import org.junit.jupiter.api.*;

import java.io.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ResourceManagerTest {
    private File newFile;
    private File newDir;
    private ResourceManager resourceManager;
    @BeforeEach
    void init() {
        resourceManager = ResourceManager.getInstance();
        assertNotNull(resourceManager);
        newFile = new File("src/main/resources/Testing/resourceTest.txt");
        newDir = new File("src/main/resources/Testing/resourceDir");
        try {
            boolean success = newFile.createNewFile();
            assertTrue(success);
            success = newDir.mkdir();
            assertTrue(success);
            success = new File(newDir.getPath() + "/file1.txt").createNewFile();
            assertTrue(success);
            success = new File(newDir.getPath() + "/file2.txt").createNewFile();
            assertTrue(success);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        resourceManager.writeToFile("Testing/resourceTest.txt","lorem ipsem\ntesting\nresource\nmanager\n");
    }

    @AfterEach
    void tearDown() {
        boolean success =  newFile.delete();
        assertTrue(success);
        resourceManager.deleteResourceFileContent("Testing/resourceDir");
        success = newDir.delete();
        assertTrue(success);
    }

    @Test
    void readStaticResourceFile() {
        assertEquals(List.of("lorem ipsem","testing","resource","manager"), resourceManager.readCurrentTimeResourceFile("Testing/staticResource.txt"));
    }
    @Test
    void failToReadStaticResourceFile() {
        assertThrows(Exception.class,()-> resourceManager.readStaticResourceFile("Testing/resourceTest2.txt"));
    }
    @Test
    void existsStaticResourceFile(){
        assertTrue(resourceManager.existsStaticResourceFile("Testing/staticResource.txt"));
        assertFalse(resourceManager.existsStaticResourceFile("Testing/staticResource2.txt"));
    }
    @Test
    void notExistsCurrentTimeResourceFile(){
        assertTrue(resourceManager.notExistsCurrentTimeResourceFile("Testing/resourceTest2.txt"));
        assertFalse(resourceManager.notExistsCurrentTimeResourceFile("Testing/resourceTest.txt"));
    }
    @Test
    void readCurrentTimeResourceFile() {
        assertEquals(List.of("lorem ipsem","testing","resource","manager"),resourceManager.readCurrentTimeResourceFile("Testing/resourceTest.txt"));

        resourceManager.writeToFile("Testing/resourceTest.txt","changed");

        assertEquals(List.of("lorem ipsem","testing","resource","manager","changed"),resourceManager.readCurrentTimeResourceFile("Testing/resourceTest.txt"));
    }
    @Test
    void failToReadCurrentTimeResourceFile() {
        assertThrows(Exception.class,()-> resourceManager.readCurrentTimeResourceFile("Testing/resourceTest2"));
    }
    
    @Test
    void failToWriteToResourceFile() throws IOException {
        File file = resourceManager.getFile("Testing/resourceTest2.png");
        boolean success = file.createNewFile();
        assertTrue(success);
        assertThrows(Exception.class,()->{
            boolean successFullReadOnly = file.setReadOnly();
            assertTrue(successFullReadOnly);
            resourceManager.writeToFile("Testing/resourceTest2.png","test");
        });
        success = file.delete();
        assertTrue(success);
    }
    @Test
    void countFiles() {
        assertEquals(2,resourceManager.countFiles("Testing/testDir"));
    }
}

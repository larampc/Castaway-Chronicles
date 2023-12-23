package castaway_chronicles;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;
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
        try {
            Writer fr = Files.newBufferedWriter(newFile.toPath(), UTF_8);
            fr.write("lorem ipsem\ntesting\nresource\nmanager\n");
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        newFile.delete();
        removeDirContent(newDir);
        newDir.delete();
    }
    private void removeDirContent(File dir){
        if(dir.isFile()) {
            dir.delete();
            return;
        }
        File[] allContents = dir.listFiles();
        if (allContents != null) {
            for (File fileToDelete : allContents) {
                removeDirContent(fileToDelete);
            }
        }
        dir.delete();
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
    void deleteResourceDirContent(){
        File dir = new File(newDir.getPath() ,"anotherDir");
        dir.mkdir();
        try {
            new File(dir.getPath(),"testFile.txt").createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        resourceManager.deleteResourceDirContent("Testing/resourceDir");
        assertEquals(0, Objects.requireNonNull(newDir.listFiles()).length);
    }
    @Test
    void deleteResourceFile(){
        resourceManager.deleteResourceFile("Testing/resourceDir");
        assertFalse(newDir.exists());

        resourceManager.deleteResourceFile("Testing/resourceTest.txt");
        assertFalse(newFile.exists());
    }
    @Test
    void createResourceDir(){
        resourceManager.createResourceDir("Testing/resourceDir/createdDir");
        assertTrue(new File(newDir.getPath() ,"createdDir").exists());
    }
    @Test
    void writeToFile(){
        resourceManager.writeToFile("Testing/resourceTest.txt","testing writing");
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of("src", "main", "resources", "Testing","resourceTest.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(List.of("lorem ipsem","testing","resource","manager","testing writing"), lines);
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
    @Test
    void getFile() {
        assertEquals(newFile,resourceManager.getFile("Testing/resourceTest.txt"));
    }
}

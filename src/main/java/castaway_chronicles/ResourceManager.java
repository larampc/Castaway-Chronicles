package castaway_chronicles;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class ResourceManager {
    private static ResourceManager instance = null;
    public static ResourceManager getInstance() {
        if (instance == null)
            instance = new ResourceManager();
        return instance;
    }
    private String path;
    private Path fullPath;
    private File file;
    public List<String> readStaticResourceFile(){
        URL resource = getClass().getClassLoader().getResource(path);
        try {
            return new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
        } catch (IOException | NullPointerException e){
            throw new RuntimeException("Couldn't read from file: " + path);
        }
    }
    public boolean existsStaticResourceFile(){
        return getClass().getClassLoader().getResource(path) != null;
    }
    public boolean notExistsCurrentTimeResourceFile(){
        return !file.exists();
    }

    public List<String> readCurrentTimeResourceFile(){
        try {
            return Files.readAllLines(fullPath);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read from file: " + path);
        }
    }
    public void deleteResourceFileContent() {
        File[] allContents = file.listFiles();
        if (allContents != null) {
            for (File fileToDelete : allContents) {
                boolean deleted = fileToDelete.delete();
                assert(deleted);
            }
        }
    }
    public void deleteResourceFile() {
        deleteResourceFileContent();
        file.delete();
    }
    public void writeToFile(String line){
        try {
            Writer fr = Files.newBufferedWriter(file.toPath(), UTF_8, CREATE, APPEND);
            fr.write(line);
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException("Error Writing to file: " + path);
        }
    }
    public int countFiles(){
        if(notExistsCurrentTimeResourceFile()) return 0;
        return Objects.requireNonNull(file.listFiles()).length;
    }

    public void setPath(String path) {
        this.path = path;
        fullPath = Path.of("src", "main", "resources",path);
        file = new File(fullPath.toString());
    }
    public File getFile(){
        return file;
    }
}


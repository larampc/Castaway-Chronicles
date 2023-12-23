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

    public List<String> readStaticResourceFile(String path){
        URL resource = getClass().getClassLoader().getResource(path);
        try {
            return new BufferedReader(new FileReader(resource.getFile(), StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
        } catch (IOException | NullPointerException e){
            throw new RuntimeException("Couldn't read from file: " + path);
        }
    }
    public boolean existsStaticResourceFile(String path){
        return getClass().getClassLoader().getResource(path) != null;
    }
    public boolean notExistsCurrentTimeResourceFile(String path){
        File file = new File(Path.of("src", "main", "resources",path).toString());
        return !file.exists();
    }

    public List<String> readCurrentTimeResourceFile(String path){
        try {
            return Files.readAllLines(Path.of("src", "main", "resources",path));
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read from file: " + path);
        }
    }
    public void deleteResourceDirContent(String path) {
        File file = new File(Path.of("src", "main", "resources",path).toString());
        File[] allContents = file.listFiles();
        if (allContents != null) {
            for (File fileToDelete : allContents) {
                deleteDirContent(fileToDelete);
            }
        }
    }
    private void deleteDirContent(File dir){
        if(dir.isFile()) {
            dir.delete();
            return;
        }
        File[] allContents = dir.listFiles();
        if (allContents != null) {
            for (File fileToDelete : allContents) {
                deleteDirContent(fileToDelete);
            }
        }
        dir.delete();
    }
    public void deleteResourceFile(String path) {
        File file = new File(Path.of("src", "main", "resources",path).toString());
        if(file.isDirectory()) deleteResourceDirContent(path);
        file.delete();
    }
    public void writeToFile(String path, String line){
        File file = new File(Path.of("src", "main", "resources",path).toString());
        try {
            Writer fr = Files.newBufferedWriter(file.toPath(), UTF_8, CREATE, APPEND);
            fr.write(line);
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException("Error Writing to file: " + path);
        }
    }
    public int countFiles(String path){
        if(notExistsCurrentTimeResourceFile(path)) return 0;
        File file = new File(Path.of("src", "main", "resources",path).toString());
        return Objects.requireNonNull(file.listFiles()).length;
    }
    public File getFile(String path){
        return new File(Path.of("src", "main", "resources",path).toString());
    }
    public void createResourceDir(String path){
        new File(Path.of("src", "main", "resources", path).toString()).mkdir();
    }
}


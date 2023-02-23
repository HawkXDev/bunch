package pathsimple;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Feel Path by reading the documentation
 */
public class PathSimple {
    public static void main(String[] args) {
        // Setting the file path
        Path filePath = FileSystems.getDefault().getPath("src", "pathsimple", "logs", "access.log");
        System.out.println("Specified file path: " + filePath);
        System.out.println("File system: " + filePath.getFileSystem());
        System.out.println("Path name count: " + filePath.getNameCount());

        try {
            // Create all necessary directories
            Files.createDirectories(filePath.getParent());
            // Create a file
            Files.createFile(filePath);
            System.out.println("File created successfully");
        } catch (IOException e) {
            System.out.println("File creation error: " + e.getMessage());
            return;
        }

        // Creating a Path object using the Path.of() method
        Path pathCreatedFile = Path.of("src", "pathsimple", "logs", "access.log");
        // Creating BufferedReader object using Files.newBufferedReader method
        try (BufferedReader reader = Files.newBufferedReader(pathCreatedFile, StandardCharsets.UTF_8)) {
        } catch (IOException e) {
            System.out.println("BufferedReader error: " + e.getMessage());
        }
    }
}

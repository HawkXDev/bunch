package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Walk through the files tree and get a map with the names of all directories and their size in bytes with all subdirectories and files
 */
public class WalkFileTree {

    public static void main(String[] args) {
        String folderName;
        try (Scanner scanner = new Scanner(System.in)) {
            folderName = scanner.nextLine();
        }

        File folder = new File(folderName);
        if (!folder.exists()) {
            System.out.println("Directory does not exist");
            return;
        }

        Map<String, Long> map;
        try {
            map = getFilesData(folder);
        } catch (IOException e) {
            throw new RuntimeException("Directory reading error");
        }

        Map<String, Long> sortedMap = sortMap(map);
        printMap(sortedMap);
    }

    private static Map<String, Long> getFilesData(File folder) throws IOException {
        EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        HashMap<String, Long> map = new HashMap<>();
        Files.walkFileTree(folder.toPath(), options, Integer.MAX_VALUE, new fileVisitor(map));
        return map;
    }

    private static class fileVisitor extends SimpleFileVisitor<Path> {
        private final Map<String, Long> map;

        fileVisitor(Map<String, Long> map) {
            this.map = map;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            long size = calculateSize(dir);
            map.put(dir.toString(), size);
            return super.preVisitDirectory(dir, attrs);
        }

        private long calculateSize(Path path) throws IOException {
            if (Files.isRegularFile(path)) {
                return Files.size(path);
            } else if (Files.isDirectory(path)) {
                long size = 0;
                File[] files = path.toFile().listFiles();
                if (files != null) {
                    for (File file : files) {
                        size += calculateSize(file.toPath());
                    }
                }
                return size;
            } else {
                return 0;
            }
        }
    }

    private static Map<String, Long> sortMap(Map<String, Long> map) {
        List<Map.Entry<String, Long>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        Map<String, Long> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Long> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    private static void printMap(Map<String, Long> sortedMap) {
        for (Map.Entry<String, Long> e : sortedMap.entrySet()) {
            System.out.printf("%s : %d%n", e.getKey(), e.getValue());
        }
    }

}

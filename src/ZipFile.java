import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Add the file inside the archive to the 'new' directory.
 */
public class ZipFile {

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        String zipName = args[1];

        Path path = Paths.get(fileName);
        String fileNameZip = "new/" + path.getFileName();
        Map<String, byte[]> archiveFiles = new HashMap<>();

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipName))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (!zipEntry.isDirectory()) {
                    String name = zipEntry.getName();
                    if (!name.equals(fileNameZip)) {
                        byte[] bytes = readEntry(zis);
                        archiveFiles.put(name, bytes);
                    }
                }
                zis.closeEntry();
            }
        }

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipName))) {
            ZipEntry zipEntry = new ZipEntry(fileNameZip);
            zos.putNextEntry(zipEntry);
            Files.copy(path, zos);
            zos.closeEntry();

            for (Map.Entry<String, byte[]> entry : archiveFiles.entrySet()) {
                zipEntry = new ZipEntry(entry.getKey());
                zos.putNextEntry(zipEntry);
                zos.write(entry.getValue());
                zos.closeEntry();
            }
        }
    }

    private static byte[] readEntry(ZipInputStream zis) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;

        while ((length = zis.read(buffer)) > 0) {
            bos.write(buffer, 0, length);
        }

        return bos.toByteArray();
    }

}

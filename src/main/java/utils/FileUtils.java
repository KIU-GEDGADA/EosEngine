package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This function handles operations on files
 */
public class FileUtils {

    private FileUtils() {
    }

    /**
     * This function reads a file from a filepath and returns a string of the file content
     * @param filePath the filepath to read
     * @return the string of the files content
     */
    public static String readFile(String filePath) {
        String result = "";
        try {
            result = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

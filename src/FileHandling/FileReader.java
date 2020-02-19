package FileHandling;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private static String filePath = FilePath.INPUT_PATH;

    public static List<String> readFile() {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath));

            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
                System.out.println(line);
            }

            reader.close();

            return lines;
        } catch (Exception e) {
            System.err.println("Exception occurred trying to read " + filePath);
            e.printStackTrace();
            return null;
        }
    }
}

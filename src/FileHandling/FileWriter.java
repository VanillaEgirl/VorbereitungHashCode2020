package FileHandling;


import java.io.PrintWriter;
import java.util.List;

public class FileWriter {
    private static String filePath = FilePath.OUTPUT_PATH;
    private static final String ENCODING = "UTF-8";

    public static void writeFile(List<String> lines) {
        try {
            PrintWriter writer = new PrintWriter(filePath, ENCODING);

            for (String line : lines) {
                System.out.println(line);
                writer.println(line);
            }

            writer.close();
        } catch (Exception e) {
            System.err.println("Exception occurred trying to write " + filePath);
            e.printStackTrace();
        }
    }
}
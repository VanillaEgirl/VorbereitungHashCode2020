package root.FileHandling;


import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

public class FileWriter {
    private static String filePath = FilePath.OUTPUT_PATH;
    private static final String ENCODING = "UTF-8";

    public static void writeFile(List<Integer> pizzas) {
        try {
            PrintWriter writer = new PrintWriter(filePath, ENCODING);

            Collections.sort(pizzas);

            writer.println(pizzas.size());

            StringBuilder stringBuilder = new StringBuilder();

            for (int pizzaId : pizzas) {
                stringBuilder.append(pizzaId);
                stringBuilder.append(" ");
            }

            writer.println(stringBuilder.toString());

            writer.close();
        } catch (Exception e) {
            System.err.println("Exception occurred trying to write " + filePath);
            e.printStackTrace();
        }
    }
}
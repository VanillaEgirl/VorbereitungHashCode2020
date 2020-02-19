package root.FileHandling;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private static String filePath = FilePath.INPUT_PATH;

    public static InputDTO readFile() {
        List<String> lines = new ArrayList<>();

        InputDTO inputDTO = new InputDTO();

        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath));

            String line;
            String[] parts;

            line = reader.readLine();
            parts = line.split("\\s+");
            inputDTO.maxPizzaSlices = Integer.parseInt(parts[0]);
            inputDTO.numberPizzaTypes = Integer.parseInt(parts[1]);

            line = reader.readLine();
            parts = line.split("\\s+");

            for (String part : parts) {
                int number = Integer.parseInt(part);
                inputDTO.numberSlices.add(number);
            }

            /*while ((line = reader.readLine()) != null) {
                lines.add(line);
                System.out.println(line);
            }*/

            reader.close();

            return inputDTO;
        } catch (Exception e) {
            System.err.println("Exception occurred trying to read " + filePath);
            e.printStackTrace();
            return null;
        }
    }
}

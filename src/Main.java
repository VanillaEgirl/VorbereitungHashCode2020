import FileHandling.FileReader;
import FileHandling.FileWriter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hallo do Schneckli!");

        List<String> strings = FileReader.readFile();
        FileWriter.writeFile(strings);
    }
}

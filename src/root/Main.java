package root;

import root.FileHandling.FileReader;
import root.FileHandling.FileWriter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strings = FileReader.readFile();
        FileWriter.writeFile(strings);
    }
}

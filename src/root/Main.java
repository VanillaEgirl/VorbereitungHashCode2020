package root;

import root.FileHandling.FileReader;
import root.FileHandling.FileWriter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hallo do Schneckli!");

        List<String> strings = FileReader.readFile();
        FileWriter.writeFile(strings);
    }
}

package root;

import root.FileHandling.FileReader;
import root.FileHandling.FileWriter;
import root.FileHandling.InputDTO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputDTO input = FileReader.readFile();
    }
}

package root;

import root.FileHandling.FileReader;
import root.FileHandling.FileWriter;
import root.FileHandling.InputDTO;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int MAX_NUMBER_OF_SLICES;

    public static void main(String[] args) {
        InputDTO input = FileReader.readFile();

        MAX_NUMBER_OF_SLICES = input.maxPizzaSlices;

        List<Integer> pizzaIds = findSolution(input.numberSlices);

        FileWriter.writeFile(pizzaIds);
    }

    private static List<Integer> findSolution(List<Integer> numbersOfSlices) {
        List<Integer> pizzaIds = new ArrayList<>();

        int slices = 0;

        for (int i = numbersOfSlices.size() - 1; i >= 0; i--) {
            int numberOfSlices = numbersOfSlices.get(i);

            if (slices + numberOfSlices < MAX_NUMBER_OF_SLICES) {
                pizzaIds.add(i);
                slices += numberOfSlices;
            }
        }

        return pizzaIds;
    }
}

package root;

import root.FileHandling.FileReader;
import root.FileHandling.FileWriter;
import root.FileHandling.InputDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static int MAX_NUMBER_OF_SLICES;

    public static void main(String[] args) {
        InputDTO input = FileReader.readFile();

        MAX_NUMBER_OF_SLICES = input.maxPizzaSlices;

        List<Integer> pizzaIds = findSolution(input.pizzas, input.maxPizzaSlices);

        FileWriter.writeFile(pizzaIds);

        validateSolution(input.pizzas, pizzaIds);
    }

    private static List<Integer> findSolution(List<Pizza> pizzas, int missingSlices) {
        List<Integer> pizzaIds = new ArrayList<>();

        int biggestStillFittingPizza = -1;

        for (int i = pizzas.size() - 1; i >= 0; i--) {
            Pizza currentPizza = pizzas.get(i);
            int missingSlicesAfterPizza = missingSlices - currentPizza.numberOfSlices;

            if (missingSlicesAfterPizza == 0) {
                pizzaIds.add(i);
                return pizzaIds;
            }

            if (missingSlicesAfterPizza > 0) {
                if (missingSlicesAfterPizza < pizzas.get(0).numberOfSlices * 100 && i > 0) {
                    biggestStillFittingPizza = i;
                    break;
                } else {
                    pizzaIds.add(i);
                    missingSlices -= currentPizza.numberOfSlices;
                }
            }
        }

        List<Pizza> availablePizzas = pizzas.subList(0, biggestStillFittingPizza + 1);

        RecursionDTO recursionResult = findRecursiveSolution(availablePizzas, missingSlices, 0);
        pizzaIds.addAll(recursionResult.pizzaIds);

        return pizzaIds;
    }

    private static RecursionDTO findRecursiveSolution(List<Pizza> availablePizzas, int missingSlices, int level) {
        RecursionDTO recursionDTO = new RecursionDTO();
        recursionDTO.missingSlices = missingSlices;

        if (availablePizzas.size() <= 0) {
            return recursionDTO;
        }

        List<Pizza> relevantPizzas = new ArrayList<>(availablePizzas);

        List<RecursionDTO> recursionResults = new ArrayList<>();

        int lowestResultSlices = missingSlices;
        RecursionDTO bestResult = null;
        int bestPizzaId = -1;

        for (int i = availablePizzas.size() - 1; i >= 0; i--) {
            Pizza currentPizza = availablePizzas.get(i);

            int missingSlicesAfterPizza = missingSlices - currentPizza.numberOfSlices;

            if (missingSlicesAfterPizza == 0) {
                System.out.println("Perfect Pizza: " + currentPizza.id);
                recursionDTO.pizzaIds.add(currentPizza.id);
                recursionDTO.missingSlices = 0;
                return recursionDTO;
            } else if (missingSlicesAfterPizza > 0) {
                List<Pizza> tempList = new ArrayList<>(relevantPizzas);
                tempList.removeIf(obj -> obj.id == currentPizza.id);

                RecursionDTO recursionResult = findRecursiveSolution(tempList, missingSlicesAfterPizza, level + 1);

                if (recursionResult.missingSlices == 0) {
                    System.out.println("Perfect Pizza recursion: " + currentPizza.id);
                    recursionDTO.pizzaIds.add(currentPizza.id);
                    recursionDTO.pizzaIds.addAll(recursionResult.pizzaIds);
                    recursionDTO.missingSlices = 0;
                    return recursionDTO;
                }

                if (recursionResult.missingSlices < lowestResultSlices) {
                    lowestResultSlices = recursionResult.missingSlices;
                    bestResult = recursionResult;
                    bestPizzaId = currentPizza.id;
                }

                recursionResults.add(recursionResult);
            } else {
                relevantPizzas.removeIf(obj -> obj.id == currentPizza.id);
            }
        }

        if (level == 0) {
            System.out.println();
        }


        if (recursionResults.size() > 0) {
            System.out.println("Best Pizza recursion: " + bestPizzaId);

            recursionDTO.pizzaIds.add(bestPizzaId);
            recursionDTO.pizzaIds.addAll(bestResult.pizzaIds);
            recursionDTO.missingSlices = bestResult.missingSlices;
        }

        return recursionDTO;
    }

    private static void validateSolution(List<Pizza> pizzas, List<Integer> pizzaIds) {
        int sliceCount = 0;

        for (int pizzaId : pizzaIds) {
            sliceCount += pizzas.get(pizzaId).numberOfSlices;
        }

        System.out.println("MAX NUMBER OF SLICES: " + MAX_NUMBER_OF_SLICES);
        System.out.println("SLICES FOR THIS SOLUTION: " + sliceCount);

        if (sliceCount == MAX_NUMBER_OF_SLICES) {
            System.out.println("PERFECT SOLUTION");
        } else if (sliceCount < MAX_NUMBER_OF_SLICES) {
            System.out.println("VALID SOLUTION");
        } else {
            System.out.println("INVALID SOLUTION");
        }
    }
}

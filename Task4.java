package Learn;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task4 {

    private static final double ZERO_DOUBLE = 0.0;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input absolute path to file :");
        String path = scanner.next();
        FileReader fileReader = new FileReader(path);
        List<Integer> numbers = createArray(new Scanner(fileReader));

        System.out.println(calculateAllSteps(numbers));
    }

    private static long calculateAllSteps(List<Integer> numbers) {
        long averageValues = Math.round(numbers.stream().mapToDouble(p -> p).average().orElse(ZERO_DOUBLE));
        return numbers.stream().mapToLong(p -> calculateSteps(p, averageValues)).sum();
    }

    private static long calculateSteps(int value, long averageValues) {
        return Math.abs(value - averageValues);
    }

    private static List<Integer> createArray(Scanner scanner) {
        List<Integer> numbers = new ArrayList<>();
        while (scanner.hasNext()) {
            numbers.add(scanner.nextInt());
        }
        return numbers;
    }
}

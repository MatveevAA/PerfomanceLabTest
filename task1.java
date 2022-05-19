package Learn;

import java.util.*;

import static java.lang.String.join;
import static java.lang.String.valueOf;

public class Main {

    private static final int FIRST_INDEX = 0;
    private static final int ONE_INT = 1;
    private static final int LAST_NUMBER = ONE_INT;
    private static final int COMPENSATION_ZERO = ONE_INT;
    private static final String EMPTY_DELIMITER = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input size array n:");
        int sizeArray = scanner.nextInt();
        System.out.println("Input length iterable m:");
        int lengthIterable = scanner.nextInt();
        StringBuilder builder = new StringBuilder();

        System.out.println(getAccumulateNumber(createArrayNumbers(sizeArray), lengthIterable, FIRST_INDEX, builder));
    }

    static private String getAccumulateNumber(List<Integer> numbers, int lengthIterable, int startIndex, StringBuilder builder) {
        int newStartIndex = startIndex + lengthIterable - COMPENSATION_ZERO;

        if (getNumberByCircleIndex(numbers, newStartIndex) != LAST_NUMBER) {
            builder.append(getNumberByCircleIndex(numbers, startIndex));
            return getAccumulateNumber(numbers, lengthIterable, newStartIndex, builder);
        }
        return builder.append(getNumberByCircleIndex(numbers, startIndex)).toString();
    }

    static private int getNumberByCircleIndex(List<Integer> numbers, int index) {
        return numbers.get(index % numbers.size());
    }

    static private List<Integer> createArrayNumbers(int size) {
        List<Integer> arrayNumbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            arrayNumbers.add(i + COMPENSATION_ZERO);
        }
        return arrayNumbers;
    }
}

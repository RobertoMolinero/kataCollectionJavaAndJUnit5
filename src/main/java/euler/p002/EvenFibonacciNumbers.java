package euler.p002;

import java.util.HashMap;
import java.util.Map;

public class EvenFibonacciNumbers {

    public static Long getSumOfEvenFibonacciNumbers(Long limit) {
        int index = 0;
        Long fibonacci = 0L;
        Long sum = 0L;

        while ((fibonacci = getFibonacci(index)) <= limit) {
            if (fibonacci % 2 == 0) {
                sum += fibonacci;
            }

            index += 1;
        }

        return sum;
    }

    public static Long getFibonacci(int index) {
        if (index <= 1) {
            return Long.valueOf(index);
        }
        return getFibonacci(index - 1) + getFibonacci(index - 2);
    }

    public static Long getSumOfEvenFibonacciNumbersFromCache(Long limit) {
        Map<Integer, Long> allFibonacciNumbers = calculateAllFibonacciNumbers(limit);

        Long sum = 0L;
        int index = 3;

        while (index < allFibonacciNumbers.size()) {
            sum += allFibonacciNumbers.get(index);
            index += 3;
        }

        return sum;
    }

    public static Map<Integer, Long> calculateAllFibonacciNumbers(Long limit) {

        Map<Integer, Long> allFibonacciNumbers = new HashMap<>();
        allFibonacciNumbers.put(0, 0L);
        allFibonacciNumbers.put(1, 1L);

        int index = 2;
        Long fibonacci = 1L;

        while (fibonacci <= limit) {
            allFibonacciNumbers.put(index, fibonacci);

            index += 1;
            fibonacci = allFibonacciNumbers.get(index - 1) + allFibonacciNumbers.get(index - 2);
        }

        return allFibonacciNumbers;
    }
}

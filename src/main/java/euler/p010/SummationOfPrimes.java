package euler.p010;

import euler.p007.ListOfPrimeNumbers;

import java.util.ArrayList;
import java.util.List;

public class SummationOfPrimes {

    public static long calculateSumOfAllPrimeNumbersUntil(int limit) {
        List<Integer> primeNumbersUntil = calculateAllPrimeNumbersUntil(limit);
        return primeNumbersUntil.stream().mapToLong(Integer::longValue).sum();
    }

    public static List<Integer> calculateAllPrimeNumbersUntil(int limit) {

        List<Integer> primeNumbers = new ArrayList<>();
        primeNumbers.add(2);

        int nextPrimeNumber = 3;

        while (nextPrimeNumber < limit) {
            primeNumbers.add(nextPrimeNumber);
            nextPrimeNumber = ListOfPrimeNumbers.findNextPrimeNumber(primeNumbers);
        }

        return primeNumbers;
    }
}

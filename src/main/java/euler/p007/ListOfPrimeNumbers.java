package euler.p007;

import java.util.ArrayList;
import java.util.List;

public class ListOfPrimeNumbers {

    public static int calculatePrimeNumber(int n) {

        List<Integer> primeNumbers = new ArrayList<>();
        primeNumbers.add(2);
        primeNumbers.add(3);

        while (primeNumbers.size() < n) {
            int nextPrimeNumber = findNextPrimeNumber(primeNumbers);
            primeNumbers.add(nextPrimeNumber);
        }

        return primeNumbers.get(primeNumbers.size() - 1);
    }

    public static int findNextPrimeNumber(List<Integer> primeNumbers) {

        int next = primeNumbers.get(primeNumbers.size() - 1);

        for (int i = next + 2; i < Integer.MAX_VALUE; i += 2) {
            final int candidate = i;
            if (primeNumbers.stream().anyMatch(n -> candidate % n == 0))
                continue;

            return i;
        }

        return 0;
    }
}

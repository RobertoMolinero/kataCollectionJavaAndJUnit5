package euler.p005;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmallestMultiple {

    private SmallestMultiple() {
    }

    public static Map<Integer, Integer> getPrimeFactorisationForSingleNumber(int number) {

        Map<Integer, Integer> primeFactorisation = new HashMap<>();

        for (var i = 2; i <= number; i++) {
            while (number % i == 0) {
                Integer power = primeFactorisation.get(i);

                if (power == null) {
                    primeFactorisation.put(i, 1);
                } else {
                    power++;
                    primeFactorisation.put(i, power);
                }

                number /= i;
            }
        }

        return primeFactorisation;
    }

    public static List<Map<Integer, Integer>> getPrimeFactorisation(int number) {
        List<Map<Integer, Integer>> individualPrimeFactorisations = new ArrayList<>();

        for (var i = 2; i <= number; i++) {
            individualPrimeFactorisations.add(getPrimeFactorisationForSingleNumber(i));
        }

        return individualPrimeFactorisations;
    }

    public static Map<Integer, Integer> mergeIndividualPrimeFactorisations(List<Map<Integer, Integer>> individualPrimeFactorisations) {
        Map<Integer, Integer> primeFactorisation = new HashMap<>();

        for (Map<Integer, Integer> individualPrimeFactorisation : individualPrimeFactorisations) {
            for (Map.Entry<Integer, Integer> entry : individualPrimeFactorisation.entrySet()) {
                if (primeFactorisation.get(entry.getKey()) == null || individualPrimeFactorisation.get(entry.getKey()) > primeFactorisation.get(entry.getKey())) {
                    primeFactorisation.put(entry.getKey(), individualPrimeFactorisation.get(entry.getKey()));
                }
            }
        }

        return primeFactorisation;
    }

    public static int computePrimeFactorisations(Map<Integer, Integer> primeFactorisations) {
        return primeFactorisations.entrySet().stream().mapToInt(entry -> (int) Math.pow(entry.getKey(), entry.getValue())).reduce(1, (a, b) -> a * b);
    }

    public static int computeSmallestMultipleUntil(int limit) {
        List<Map<Integer, Integer>> individualPrimeFactorisation = getPrimeFactorisation(limit);
        Map<Integer, Integer> primeFactorisation = mergeIndividualPrimeFactorisations(individualPrimeFactorisation);
        return computePrimeFactorisations(primeFactorisation);
    }
}

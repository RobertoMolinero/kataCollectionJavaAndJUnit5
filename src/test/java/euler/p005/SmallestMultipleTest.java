package euler.p005;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SmallestMultipleTest {

    @ParameterizedTest
    @MethodSource
    void getPrimeFactorisationForSingleNumber(int number, Map<Integer, Integer> expectedPrimeFactorisation) {
        assertEquals(expectedPrimeFactorisation, SmallestMultiple.getPrimeFactorisationForSingleNumber(number));
    }

    static Stream<Arguments> getPrimeFactorisationForSingleNumber() {
        return Stream.of(
                arguments(1, Map.of()),
                arguments(2, Map.of(2, 1)),
                arguments(3, Map.of(3, 1)),
                arguments(4, Map.of(2, 2)),
                arguments(5, Map.of(5, 1)),
                arguments(6, Map.of(2, 1, 3, 1)),
                arguments(7, Map.of(7, 1)),
                arguments(8, Map.of(2, 3)),
                arguments(9, Map.of(3, 2)),
                arguments(10, Map.of(2, 1, 5, 1))
        );
    }

    @ParameterizedTest
    @MethodSource
    void getPrimeFactorisation(int number, List<Map<Integer, Integer>> individualPrimeFactorisations) {
        assertEquals(individualPrimeFactorisations, SmallestMultiple.getPrimeFactorisation(number));
    }

    static Stream<Arguments> getPrimeFactorisation() {
        return Stream.of(
                arguments(10, List.of(
                        Map.of(2, 1),
                        Map.of(3, 1),
                        Map.of(2, 2),
                        Map.of(5, 1),
                        Map.of(2, 1, 3, 1),
                        Map.of(7, 1),
                        Map.of(2, 3),
                        Map.of(3, 2),
                        Map.of(2, 1, 5, 1))
                ));
    }

    @ParameterizedTest
    @MethodSource
    void mergeIndividualPrimeFactorisations(List<Map<Integer, Integer>> individualPrimeFactorisations, Map<Integer, Integer> expectedPrimeFactorisation) {
        assertEquals(expectedPrimeFactorisation, SmallestMultiple.mergeIndividualPrimeFactorisations(individualPrimeFactorisations));
    }

    static Stream<Arguments> mergeIndividualPrimeFactorisations() {
        return Stream.of(
                arguments(
                        List.of(
                                Map.of(2, 1),
                                Map.of(3, 1),
                                Map.of(2, 2),
                                Map.of(5, 1),
                                Map.of(2, 1, 3, 1),
                                Map.of(7, 1),
                                Map.of(2, 3),
                                Map.of(3, 2),
                                Map.of(2, 1, 5, 1)
                        ), Map.of(2, 3, 3, 2, 5, 1, 7, 1))
        );
    }

    @ParameterizedTest
    @MethodSource
    void computePrimeFactorisations(Map<Integer, Integer> primeFactorisation, int expectedSmallestMultiple) {
        assertEquals(expectedSmallestMultiple, SmallestMultiple.computePrimeFactorisations(primeFactorisation));
    }

    static Stream<Arguments> computePrimeFactorisations() {
        return Stream.of(
                arguments(Map.of(2, 3, 3, 2, 5, 1, 7, 1), 2520)
        );
    }

    @ParameterizedTest
    @MethodSource
    void computeSmallestMultipleUntil(int limit, int expectedSmallestMultiple) {
        assertEquals(expectedSmallestMultiple, SmallestMultiple.computeSmallestMultipleUntil(limit));
    }

    static Stream<Arguments> computeSmallestMultipleUntil() {
        return Stream.of(
                arguments(10, 2520),
                arguments(12, 2520 * 11),
                arguments(15, 2520 * 11 * 13),
                arguments(18, 2520 * 11 * 13 * 2 * 17),
                arguments(20, 2520 * 11 * 13 * 2 * 17 * 19)
        );
    }
}

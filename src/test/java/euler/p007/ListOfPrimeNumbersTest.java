package euler.p007;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ListOfPrimeNumbersTest {

    @ParameterizedTest
    @MethodSource
    void calculatePrimeNumber(int n, int primeNumber) {
        assertEquals(primeNumber, ListOfPrimeNumbers.calculatePrimeNumber(n));
    }

    static Stream<Arguments> calculatePrimeNumber() {
        return Stream.of(
                arguments(2, 3),
                arguments(3, 5),
                arguments(4, 7),
                arguments(5, 11),
                arguments(6, 13),
                arguments(10001, 104743)
        );
    }

    @ParameterizedTest
    @MethodSource
    void findNextPrimeNumber(List<Integer> primeNumbers, int nextPrimeNumber) {
        assertEquals(nextPrimeNumber, ListOfPrimeNumbers.findNextPrimeNumber(primeNumbers));
    }

    static Stream<Arguments> findNextPrimeNumber() {
        return Stream.of(
                arguments(List.of(2, 3), 5),
                arguments(List.of(2, 3, 5), 7),
                arguments(List.of(2, 3, 5, 7), 11),
                arguments(List.of(2, 3, 5, 7, 11), 13)
        );
    }
}

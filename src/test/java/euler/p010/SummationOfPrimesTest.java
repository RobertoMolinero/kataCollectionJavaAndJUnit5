package euler.p010;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SummationOfPrimesTest {

    @ParameterizedTest
    @MethodSource
    void calculateSumOfAllPrimeNumbersUntil(int limit, long expectedSum) {
        assertEquals(expectedSum, SummationOfPrimes.calculateSumOfAllPrimeNumbersUntil(limit));
    }

    static Stream<Arguments> calculateSumOfAllPrimeNumbersUntil() {
        return Stream.of(
                arguments(3, 2L),
                arguments(5, 5L),
                arguments(7, 10L),
                arguments(10, 17L),
                arguments(11, 17L),
                arguments(13, 28L)
                // arguments(2000000, 142913828922L)
        );
    }

    @ParameterizedTest
    @MethodSource
    void calculateAllPrimeNumbersUntil(int limit, int expectedSizeOfList, List<Integer> expectedList) {
        List<Integer> integerList = SummationOfPrimes.calculateAllPrimeNumbersUntil(limit);
        assertEquals(expectedSizeOfList, integerList.size());
        assertEquals(expectedList, integerList);
    }

    static Stream<Arguments> calculateAllPrimeNumbersUntil() {
        return Stream.of(
                arguments(3, 1, List.of(2)),
                arguments(5, 2, List.of(2, 3)),
                arguments(7, 3, List.of(2, 3, 5)),
                arguments(11, 4, List.of(2, 3, 5, 7)),
                arguments(13, 5, List.of(2, 3, 5, 7, 11))
        );
    }
}

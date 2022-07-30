package euler.p006;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SumSquareDifferenceTest {

    @ParameterizedTest
    @MethodSource
    void calculateDifference(int limit, int expectedSum) {
        assertEquals(expectedSum, SumSquareDifference.calculateDifference(limit));
    }

    static Stream<Arguments> calculateDifference() {
        return Stream.of(
                arguments(10, 2640),
                arguments(100, 25164150)
        );
    }

    @ParameterizedTest
    @MethodSource
    void calculateSumOfTheSquares(int limit, int expectedSum) {
        assertEquals(expectedSum, SumSquareDifference.calculateSumOfTheSquares(limit));
    }

    static Stream<Arguments> calculateSumOfTheSquares() {
        return Stream.of(
                arguments(10, 385)
        );
    }

    @ParameterizedTest
    @MethodSource
    void calculateSquareOfTheSum(long limit, long expectedSum) {
        assertEquals(expectedSum, SumSquareDifference.calculateSquareOfTheSum(limit));
    }

    static Stream<Arguments> calculateSquareOfTheSum() {
        return Stream.of(
                arguments(10, 55 * 55),
                arguments(100, 5050 * 5050),
                arguments(1000, 500500L * 500500L),
                arguments(10000, 50005000L * 50005000L)
        );
    }
}

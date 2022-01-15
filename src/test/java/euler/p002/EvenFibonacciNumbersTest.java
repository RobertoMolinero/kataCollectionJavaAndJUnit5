package euler.p002;

import euler.TimingExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(TimingExtension.class)
class EvenFibonacciNumbersTest {

    @ParameterizedTest
    @MethodSource
    void getSumOfEvenFibonacciNumbers(Long limit, Long expectedSum) {
        assertEquals(expectedSum, EvenFibonacciNumbers.getSumOfEvenFibonacciNumbers(limit));
    }

    static Stream<Arguments> getSumOfEvenFibonacciNumbers() {
        return Stream.of(
                arguments(2L, 2L),
                arguments(8L, 10L),
                arguments(34L, 44L),
                arguments(4000000L, 4613732L)
        );
    }

    @ParameterizedTest
    @MethodSource
    void getFibonacci(int index, Long expectedSum) {
        assertEquals(expectedSum, EvenFibonacciNumbers.getFibonacci(index));
    }

    static Stream<Arguments> getFibonacci() {
        return Stream.of(
                arguments(1, 1L),
                arguments(2, 1L),
                arguments(3, 2L),
                arguments(4, 3L),
                arguments(5, 5L),
                arguments(6, 8L),
                arguments(7, 13L),
                arguments(8, 21L),
                arguments(9, 34L),
                arguments(10, 55L)
        );
    }

    @ParameterizedTest
    @MethodSource
    void calculateAllFibonacciNumbers(Long limit, int expectedLength) {
        assertEquals(expectedLength, EvenFibonacciNumbers.calculateAllFibonacciNumbers(limit).size());
    }

    static Stream<Arguments> calculateAllFibonacciNumbers() {
        return Stream.of(
                arguments(3L, 5),
                arguments(13L, 8),
                arguments(89L, 12)
        );
    }

    @ParameterizedTest
    @MethodSource
    void getSumOfEvenFibonacciNumbersFromCache(Long limit, Long expectedSum) {
        assertEquals(expectedSum, EvenFibonacciNumbers.getSumOfEvenFibonacciNumbersFromCache(limit));
    }

    static Stream<Arguments> getSumOfEvenFibonacciNumbersFromCache() {
        return Stream.of(
                arguments(2L, 2L),
                arguments(8L, 10L),
                arguments(34L, 44L),
                arguments(4000000L, 4613732L)
        );
    }
}

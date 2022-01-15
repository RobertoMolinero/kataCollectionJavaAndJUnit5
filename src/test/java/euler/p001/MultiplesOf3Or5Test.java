package euler.p001;

import euler.TimingExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(TimingExtension.class)
class MultiplesOf3Or5Test {

    @ParameterizedTest
    @MethodSource("exampleCalculations")
    void calculateSimple(int limit, int expectedSum) {
        assertEquals(expectedSum, MultiplesOf3Or5.calculateSimple(limit));
    }

    @ParameterizedTest
    @MethodSource("exampleCalculations")
    void calculateSimpleWithStream(int limit, int expectedSum) {
        assertEquals(expectedSum, MultiplesOf3Or5.calculateSimpleWithStream(limit));
    }

    @ParameterizedTest
    @MethodSource("exampleCalculations")
    void calculateMultiplesOf3And5(int limit, int expectedSum) {
        assertEquals(expectedSum, MultiplesOf3Or5.calculateMultiplesOf3And5(limit));
    }

    static Stream<Arguments> exampleCalculations() {
        return Stream.of(
                arguments(10, 23),
                arguments(100, 2318),
                arguments(1000, 233168),
                arguments(10000, 23331668)
        );
    }

    @ParameterizedTest
    @MethodSource
    void calculateSumOfGauss(int limit, int expectedSum) {
        assertEquals(expectedSum, MultiplesOf3Or5.calculateSumOfGauss(limit));
    }

    static Stream<Arguments> calculateSumOfGauss() {
        return Stream.of(
                arguments(10, 55),
                arguments(100, 5050),
                arguments(1000, 500500),
                arguments(10000, 50005000)
        );
    }

    @ParameterizedTest
    @MethodSource
    void calculateSumOfGaussWithMultiple(int limit, int multiple, int expectedSum) {
        assertEquals(expectedSum, MultiplesOf3Or5.calculateSumOfGaussWithMultiple(limit, multiple));
    }

    static Stream<Arguments> calculateSumOfGaussWithMultiple() {
        return Stream.of(
                arguments(999, 3, 166833),
                arguments(999, 5, 99500),
                arguments(999, 15, 33165)
        );
    }
}

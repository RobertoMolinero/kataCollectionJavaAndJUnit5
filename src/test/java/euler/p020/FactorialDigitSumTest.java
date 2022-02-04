package euler.p020;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class FactorialDigitSumTest {

    @ParameterizedTest
    @MethodSource
    void calculateFactorialDigitSum(int limit, long expectedSum) {
        Assertions.assertEquals(expectedSum, FactorialDigitSum.calculateFactorialDigitSum(limit));
    }

    static Stream<Arguments> calculateFactorialDigitSum() {
        return Stream.of(
                arguments(10, 27L),
                arguments(100, 648L)
        );
    }
}

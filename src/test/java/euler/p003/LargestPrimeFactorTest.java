package euler.p003;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LargestPrimeFactorTest {

    @ParameterizedTest
    @MethodSource
    void getLargestPrimeFactor(long limit, long expected) {
        assertEquals(expected, LargestPrimeFactor.getLargestPrimeFactor(limit));
    }

    static Stream<Arguments> getLargestPrimeFactor() {
        return Stream.of(
                arguments(24L, 3),
                arguments(13195L, 29),
                arguments(600851475143L, 6857)
        );
    }
}

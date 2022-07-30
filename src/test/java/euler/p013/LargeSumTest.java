package euler.p013;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LargeSumTest {

    @ParameterizedTest
    @MethodSource
    void calculateLargeSum(String fileName, long expectedSum) {
        assertEquals(expectedSum, LargeSum.calculateLargeSum(fileName));
    }

    static Stream<Arguments> calculateLargeSum() {
        return Stream.of(
                arguments("src/test/euler/resources/p013/largeSum.txt", 0L)
        );
    }
}

package euler.p004;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LargestPalindromeProductTest {

    @ParameterizedTest
    @MethodSource
    void isPalindrom(int candidate, boolean isPalindrom) {
        assertEquals(isPalindrom, LargestPalindromeProduct.isPalindrom(candidate));
    }

    static Stream<Arguments> isPalindrom() {
        return Stream.of(
                arguments(979, true),
                arguments(888, true),
                arguments(566, false),
                arguments(56, false)
        );
    }

    @Test
    void getLargestPalindromeProduct() {
        assertEquals(906609, LargestPalindromeProduct.getLargestPalindromeProduct());
    }
}

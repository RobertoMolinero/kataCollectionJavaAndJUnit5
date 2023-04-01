package euler.p036;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class DoubleBasePalindromeTest {

    @ParameterizedTest
    @MethodSource
    void getSumOfPalindromes(int limit, int expectedSum) {
        Assertions.assertEquals(expectedSum, DoubleBasePalindrome.getSumOfPalindromes(limit));
    }

    static Stream<Arguments> getSumOfPalindromes() {
        return Stream.of(
                arguments(1, 1),
                arguments(3, 4),
                arguments(5, 9),
                arguments(1_000_000, 872187)
        );
    }

    @ParameterizedTest
    @MethodSource
    void convertToBinary(int decimal, BigInteger binary) {
        Assertions.assertEquals(binary, DoubleBasePalindrome.convertToBinary(decimal));
    }

    static Stream<Arguments> convertToBinary() {
        return Stream.of(
                arguments(1, new BigInteger("1")),
                arguments(1_000_000, new BigInteger("11110100001001000000"))
        );
    }

    @ParameterizedTest
    @MethodSource
    void isPalindrom(BigInteger candidate, boolean isPalindrom) {
        Assertions.assertEquals(isPalindrom, DoubleBasePalindrome.isPalindrom(candidate));
    }

    static Stream<Arguments> isPalindrom() {
        return Stream.of(
                arguments(new BigInteger("979"), true),
                arguments(new BigInteger("888"), true),
                arguments(new BigInteger("566"), false),
                arguments(new BigInteger("56"), false)
        );
    }
}
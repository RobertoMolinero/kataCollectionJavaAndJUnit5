package euler.p036;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class DoubleBasePalindrome {

    public static int getSumOfPalindromes(int limit) {
        return IntStream
                .rangeClosed(1, limit)
                .filter(i -> DoubleBasePalindrome.isPalindrom(new BigInteger("" + i)))
                .filter(i -> DoubleBasePalindrome.isPalindrom(DoubleBasePalindrome.convertToBinary(i)))
                .sum();
    }

    public static BigInteger convertToBinary(int decimal) {
        return new BigInteger(Integer.toBinaryString(decimal));
    }

    public static boolean isPalindrom(BigInteger candidate) {
        String candidateAsString = candidate.toString();
        return new StringBuilder(candidateAsString).reverse().toString().equals(candidateAsString);
    }
}

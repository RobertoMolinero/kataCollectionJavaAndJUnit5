package euler.p004;

public class LargestPalindromeProduct {

    public static int getLargestPalindromeProduct() {

        for (int candidate = 999 * 999; candidate >= 101 * 101; candidate--) {
            if (isPalindrom(candidate)) {
                for (int divisor = 999; divisor >= 101; divisor--) {
                    if (candidate % divisor == 0 && candidate / divisor <= 999) {
                        return candidate;
                    }
                }
            }
        }

        return 0;
    }

    public static boolean isPalindrom(int candidate) {
        String candidateAsString = "" + candidate;
        return new StringBuilder(candidateAsString).reverse().toString().equals(candidateAsString);
    }
}

package euler.p003;

public class LargestPrimeFactor {

    public static long getLargestPrimeFactor(long number) {
        long candidate = number;
        int limit = (int) Math.sqrt(number);

        for (int i = 2; i <= limit; i++) {
            if (number % i == 0) {
                candidate = number / i;
                return getLargestPrimeFactor(candidate);
            }
        }

        return candidate;
    }
}

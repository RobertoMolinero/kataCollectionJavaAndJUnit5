package euler.p020;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FactorialDigitSum {

    private FactorialDigitSum() {
    }

    public static long calculateFactorialDigitSum(int limit) {

        var factorial = BigInteger.valueOf(1L);

        for (var i = 2; i <= limit; i++) {
            factorial = BigInteger.valueOf(i).multiply(factorial);
        }

        List<Integer> digits = new ArrayList<>();

        while (factorial.compareTo(BigInteger.valueOf(0L)) > 0) {
            digits.add(factorial.mod(BigInteger.valueOf(10L)).intValue());
            factorial = factorial.divide(BigInteger.valueOf(10L));
        }

        return digits.stream().mapToInt(Integer::intValue).sum();
    }
}

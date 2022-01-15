package euler.p001;

import java.util.stream.IntStream;

public class MultiplesOf3Or5 {

    public static int calculateSimple(int limit) {
        int sum = 0;

        for (int i = 1; i < limit; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }

        return sum;
    }

    public static int calculateSimpleWithStream(int limit) {
        return IntStream.range(1, limit).filter(n -> n % 3 == 0 || n % 5 == 0).sum();
    }

    public static int calculateSumOfGauss(int limit) {
        double limitAsDouble = limit;
        return (int) ((limitAsDouble / 2) * (limitAsDouble + 1));
    }

    public static int calculateSumOfGaussWithMultiple(int limit, int multiple) {
        int adjustedLimit = limit / multiple;
        int sumOfGauss = calculateSumOfGauss(adjustedLimit);
        return multiple * sumOfGauss;
    }

    public static int calculateMultiplesOf3And5(int limit) {
        int withMultiple3 = calculateSumOfGaussWithMultiple(limit - 1, 3);
        int withMultiple5 = calculateSumOfGaussWithMultiple(limit - 1, 5);
        int withMultiple15 = calculateSumOfGaussWithMultiple(limit - 1, 15);
        return withMultiple3 + withMultiple5 - withMultiple15;
    }
}

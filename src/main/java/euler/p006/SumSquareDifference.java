package euler.p006;

import java.util.stream.IntStream;

public class SumSquareDifference {

    public static long calculateDifference(long limit) {
        return calculateSquareOfTheSum(limit) - calculateSumOfTheSquares(limit);
    }

    public static long calculateSumOfTheSquares(long limit) {
        return IntStream.rangeClosed(1, (int) limit).map(n -> n * n).sum();
    }

    public static long calculateSquareOfTheSum(long limit) {
        long sum = limit * (limit + 1) / 2;
        return sum * sum;
    }
}

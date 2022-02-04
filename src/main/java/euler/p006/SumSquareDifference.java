package euler.p006;

import java.util.stream.IntStream;

public class SumSquareDifference {

    public static int calculateDifference(int limit) {
        return calculateSquareOfTheSum(limit) - calculateSumOfTheSquares(limit);
    }

    public static int calculateSumOfTheSquares(int limit) {
        return IntStream.rangeClosed(1, limit).map(n -> n * n).sum();
    }

    public static int calculateSquareOfTheSum(int limit) {
        int sum = limit * (limit + 1) / 2;
        return sum * sum;
    }
}

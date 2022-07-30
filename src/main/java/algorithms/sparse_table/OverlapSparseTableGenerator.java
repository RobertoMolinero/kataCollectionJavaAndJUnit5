package algorithms.sparse_table;

import java.util.List;
import java.util.stream.IntStream;

public class OverlapSparseTableGenerator implements SparseTableGenerator {

    @Override
    public SparseTable generateTable(List<Integer> values) {

        int length = values.size();
        int power = (int) (Math.log(length) / Math.log(2));

        int[] log2 = new int[length + 1];

        for (int i = 2; i <= length; i++) {
            log2[i] = log2[i / 2] + 1;
        }

        int[][] table = new int[power + 1][length];

        IntStream.range(0, length).forEach(i -> table[0][i] = values.get(i));

        // Build sparse table combining the values of the previous intervals.
        for (int i = 1; i <= power; i++) {
            for (int j = 0; j + (1 << i) <= length; j++) {
                int leftInterval = table[i - 1][j];
                int rightInterval = table[i - 1][j + (1 << (i - 1))];
                table[i][j] = Math.min(leftInterval, rightInterval);
            }
        }

        return new SparseTable(power, log2, table);
    }
}

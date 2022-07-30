package algorithms.sparse_table;

public class SparseTable {

    /**
     * The maximum power of 2 needed. This value is floor(log2(n)).
     */
    private int power;

    /**
     * Fast log base 2 logarithm lookup table, 1 <= i <= n.
     */
    private int[] log2;

    /**
     * The sparse table values.
     */
    private int[][] table;

    public SparseTable(int power, int[] log2, int[][] table) {
        this.power = power;
        this.log2 = log2;
        this.table = table;
    }
}

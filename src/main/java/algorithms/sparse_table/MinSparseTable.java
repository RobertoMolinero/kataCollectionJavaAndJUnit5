package algorithms.sparse_table;

public class MinSparseTable {

    // The number of elements in the original input array.
    private int n;

    // The maximum power of 2 needed. This value is floor(log2(n)).
    private int P;

    // Fast log base 2 logarithm lookup table, 1 <= i <= n.
    private int[] log2;

    // The sparse table values.
    private int[][] dp;

    // Index table associated with the values in the sparse table. This table is only useful when we want to query
    // the index of the min (or max) element in the range [l, r] rather than the value itself. The index tables
    // doesn't make sense for most other range query types like gcd or sum.
    private int[][] it;

    public MinSparseTable(int[] values) {

        n = values.length;
        P = (int) (Math.log(n) / Math.log(2));
        dp = new int[P + 1][n];
        it = new int[P + 1][n];

        for (int i = 0; i < n; i++) {
            dp[0][i] = values[i];
            it[0][i] = i;
        }

        log2 = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            log2[i] = log2[i / 2] + 1;
        }

        // Build sparse table combining the values of the previous intervals.
        for (int p = 1; p <= P; p++) {
            for (int i = 0; i + (1 << p) <= n; i++) {
                int leftInterval = dp[p - 1][i];
                int rightInterval = dp[p - 1][i + (1 << (p - 1))];
                dp[p][i] = Math.min(leftInterval, rightInterval);

                // Propagate the index of the best value.
                if (leftInterval <= rightInterval) {
                    it[p][i] = it[p - 1][i];
                } else {
                    it[p][i] = it[p - 1][i + (1 << (p - 1))];
                }
            }
        }
    }

    private long queryMin(int l, int r) {
        int length = r - l + 1;
        int p = log2[length];
        int k = 1 << p;
        return Math.min(dp[p][l], dp[p][r - k + 1]);
    }

    // Returns the index of the minimum element in the range [l, r].
    public int queryMinIndex(int l, int r) {
        int length = r - l + 1;
        int p = log2[length];
        int k = 1 << p;

        long leftInterval = dp[p][l];
        long rightInterval = dp[p][r - k + 1];

        if (leftInterval <= rightInterval) {
            return it[p][l];
        }

        return it[p][r - k + 1];
    }
}

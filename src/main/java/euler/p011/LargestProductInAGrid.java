package euler.p011;

public class LargestProductInAGrid {

    private LargestProductInAGrid() {
    }

    public static int findLargestProductInAGrid(String[][] grid) {

        var max = 0;

        for (var direction : Direction.values()) {
            int maxInDirection = findLargestProductInAGridInDirection(grid, direction);

            if (maxInDirection > max) {
                max = maxInDirection;
            }
        }

        return max;
    }

    public static int findLargestProductInAGridInDirection(String[][] grid, Direction direction) {

        var max = 0;

        for (var y = direction.getMarginTop(); y < grid.length - direction.getMarginBottom(); y++) {
            for (var x = direction.getMarginLeft(); x < grid[y].length - direction.getMarginRight(); x++) {

                var n0 = Integer.parseInt(grid[y][x]);
                var n1 = Integer.parseInt(grid[y + direction.getVertical()][x + direction.getHorizontal()]);
                var n2 = Integer.parseInt(grid[y + 2 * direction.getVertical()][x + 2 * direction.getHorizontal()]);
                var n3 = Integer.parseInt(grid[y + 3 * direction.getVertical()][x + 3 * direction.getHorizontal()]);
                var sum = n0 * n1 * n2 * n3;

                if (sum > max) {
                    max = sum;
                    System.out.printf("Neues Maximun in Direction %s at Index [%d, %d]: [%d; ; %d; %d; %d]%n", direction, x, y, n0, n1, n2, n3);
                }
            }
        }

        return max;
    }
}

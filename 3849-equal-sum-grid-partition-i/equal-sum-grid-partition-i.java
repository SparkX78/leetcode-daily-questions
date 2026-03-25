class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long total = 0; // FIX: use long
        for (int[] row : grid) {
            for (int val : row) {
                total += val;
            }
        }

        if (total % 2 != 0) return false;

        long target = total / 2;

        // Horizontal cut
        long rowSum = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                rowSum += grid[i][j];
            }
            if (rowSum == target) return true;
        }

        // Vertical cut
        long[] colSum = new long[n]; // FIX: long[]
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                colSum[j] += grid[i][j];
            }
        }

        long prefixCol = 0;
        for (int j = 0; j < n - 1; j++) {
            prefixCol += colSum[j];
            if (prefixCol == target) return true;
        }

        return false;
    }
}
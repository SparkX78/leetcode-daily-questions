class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long totalSum = 0;
        int maxVal = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                totalSum += grid[i][j];
                if (grid[i][j] > maxVal) maxVal = grid[i][j];
            }
        }

        // 1. Horizontal Cuts
        if (m >= 2) {
            int[] topFreq = new int[maxVal + 1];
            int[] botFreq = new int[maxVal + 1];
            for (int[] row : grid) {
                for (int val : row) botFreq[val]++;
            }

            long s1 = 0, s2 = totalSum;
            for (int r = 0; r < m - 1; r++) {
                for (int c = 0; c < n; c++) {
                    int val = grid[r][c];
                    topFreq[val]++;
                    botFreq[val]--;
                    s1 += val;
                    s2 -= val;
                }
                if (isValidPartition(s1, s2, 0, 0, r, n - 1, topFreq, r + 1, 0, m - 1, n - 1, botFreq, grid)) {
                    return true;
                }
            }
        }

        // 2. Vertical Cuts
        if (n >= 2) {
            int[] leftFreq = new int[maxVal + 1];
            int[] rightFreq = new int[maxVal + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) rightFreq[grid[i][j]]++;
            }

            long s1 = 0, s2 = totalSum;
            for (int c = 0; c < n - 1; c++) {
                for (int r = 0; r < m; r++) {
                    int val = grid[r][c];
                    leftFreq[val]++;
                    rightFreq[val]--;
                    s1 += val;
                    s2 -= val;
                }
                if (isValidPartition(s1, s2, 0, 0, m - 1, c, leftFreq, 0, c + 1, m - 1, n - 1, rightFreq, grid)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isValidPartition(long s1, long s2, int r1_1, int c1_1, int r1_2, int c1_2, int[] f1,
                                     int r2_1, int c2_1, int r2_2, int c2_2, int[] f2, int[][] grid) {
        if (s1 == s2) return true;
        if (s1 > s2) {
            long diff = s1 - s2;
            if (diff < f1.length) return canRemove(grid, (int) diff, r1_1, c1_1, r1_2, c1_2, f1);
        } else {
            long diff = s2 - s1;
            if (diff < f2.length) return canRemove(grid, (int) diff, r2_1, c2_1, r2_2, c2_2, f2);
        }
        return false;
    }

    private boolean canRemove(int[][] grid, int val, int r1, int c1, int r2, int c2, int[] freq) {
        int rows = r2 - r1 + 1;
        int cols = c2 - c1 + 1;
        // If dimensions are both > 1, any cell of that value works
        if (rows > 1 && cols > 1) return freq[val] > 0;
        // If it's a 1D strip, only the two ends maintain connectivity
        if (rows == 1 && cols > 1) return grid[r1][c1] == val || grid[r1][c2] == val;
        if (cols == 1 && rows > 1) return grid[r1][c1] == val || grid[r2][c1] == val;
        // Single cell case
        return grid[r1][c1] == val;
    }
}
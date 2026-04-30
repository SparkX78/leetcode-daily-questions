class Solution {
    int m, n;
    int[][][] dp;

    public int maxPathScore(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;

        dp = new int[m][n][k + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -2); // -2 = unvisited
            }
        }

        return solve(grid, k, 0, 0, 0);
    }

    private int solve(int[][] grid, int k, int i, int j, int cost) {
        if (i >= m || j >= n) return -1;

        int newCost = cost + (grid[i][j] > 0 ? 1 : 0);

        if (newCost > k) return -1;

        if (i == m - 1 && j == n - 1) {
            return grid[i][j];
        }

        if (dp[i][j][cost] != -2) {
            return dp[i][j][cost];
        }

        int down = solve(grid, k, i + 1, j, newCost);
        int right = solve(grid, k, i, j + 1, newCost);

        int res = -1;

        if (down != -1) res = Math.max(res, down);
        if (right != -1) res = Math.max(res, right);

        if (res == -1) {
            return dp[i][j][cost] = -1;
        }

        return dp[i][j][cost] = res + grid[i][j];
    }
}
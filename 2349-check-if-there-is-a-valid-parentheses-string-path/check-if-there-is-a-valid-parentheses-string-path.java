class Solution {
    private Boolean[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Total length of path is m + n - 1. A valid parentheses string must have an even length.
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Maximum open brackets can never exceed (m + n) / 2
        memo = new Boolean[m][n][(m + n + 1) / 2];
        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int open) {
        // Adjust net balance of open parentheses
        if (grid[r][c] == '(') {
            open++;
        } else {
            open--;
        }

        // Invalid path: more closing brackets than opening brackets
        if (open < 0) {
            return false;
        }

        // Max possible remaining steps is (m - 1 - r) + (n - 1 - c).
        // If open exceeds remaining steps, we can never close all open brackets.
        if (open > (m - 1 - r) + (n - 1 - c)) {
            return false;
        }

        // Reached the destination
        if (r == m - 1 && c == n - 1) {
            return open == 0;
        }

        if (memo[r][c][open] != null) {
            return memo[r][c][open];
        }

        boolean result = false;
        if (r + 1 < m) {
            result = result || dfs(grid, r + 1, c, open);
        }
        if (c + 1 < n) {
            result = result || dfs(grid, r, c + 1, open);
        }

        return memo[r][c][open] = result;
    }
}
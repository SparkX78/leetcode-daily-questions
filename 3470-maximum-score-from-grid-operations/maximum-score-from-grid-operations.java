import java.util.Arrays;

class Solution {
    /**
     * Maximizes score using DP based on column heights.
     * Time Complexity: O(n^3)
     * Space Complexity: O(n^2)
     */
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        if (n == 0) return 0;
        
        // Precompute prefix sums for each column for O(1) range sum queries.
        long[][] pref = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                pref[j][i + 1] = pref[j][i] + grid[i][j];
            }
        }

        // dp[currH][prevH] stores the max score for cols 0 to j-1, 
        // given col j has height currH and col j-1 has height prevH.
        long[][] dp = new long[n + 1][n + 1];
        for (long[] row : dp) Arrays.fill(row, -1L);

        // Base case: Start at column 0. We imagine a virtual column -1 with height 0.
        for (int h = 0; h <= n; h++) {
            dp[h][0] = 0;
        }

        // Transition through columns 0 to n-2.
        for (int j = 0; j < n - 1; j++) {
            long[][] nextDp = new long[n + 1][n + 1];
            for (long[] row : nextDp) Arrays.fill(row, -1L);

            for (int curr = 0; curr <= n; curr++) {
                // Optimized transition using prefix/suffix max logic.
                // Score of column j is determined by max(h[j-1], h[j+1]) impacting white cells in j.
                
                // Case A: prevH > nextH -> max(prevH, nextH) = prevH
                long[] suffixMax = new long[n + 2];
                Arrays.fill(suffixMax, -1L);
                for (int prev = n; prev >= 0; prev--) {
                    long val = -1;
                    if (dp[curr][prev] != -1) {
                        long score = (prev > curr) ? (pref[j][prev] - pref[j][curr]) : 0;
                        val = dp[curr][prev] + score;
                    }
                    suffixMax[prev] = Math.max(suffixMax[prev + 1], val);
                }

                // Case B: prevH <= nextH -> max(prevH, nextH) = nextH
                long prefixMaxDP = -1;
                for (int next = 0; next <= n; next++) {
                    if (dp[curr][next] != -1) {
                        prefixMaxDP = Math.max(prefixMaxDP, dp[curr][next]);
                    }

                    long best = -1;
                    // Apply Case B
                    if (prefixMaxDP != -1) {
                        long score = (next > curr) ? (pref[j][next] - pref[j][curr]) : 0;
                        best = Math.max(best, prefixMaxDP + score);
                    }
                    // Apply Case A
                    if (next + 1 <= n) {
                        best = Math.max(best, suffixMax[next + 1]);
                    }
                    nextDp[next][curr] = best;
                }
            }
            dp = nextDp;
        }

        // Final Step: Account for the score of the last column (n-1).
        // Since there is no column n, h[n] is effectively 0.
        long maxTotalScore = 0;
        for (int curr = 0; curr <= n; curr++) {
            for (int prev = 0; prev <= n; prev++) {
                if (dp[curr][prev] != -1) {
                    long lastColScore = (prev > curr) ? (pref[n - 1][prev] - pref[n - 1][curr]) : 0;
                    maxTotalScore = Math.max(maxTotalScore, dp[curr][prev] + lastColScore);
                }
            }
        }

        return maxTotalScore;
    }
}
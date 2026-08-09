class Solution {
    private int[][] memo;
    private int[] suffixSum;

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        memo = new int[n][n + 1];
        suffixSum = new int[n];

        // Calculate suffix sums to quickly query total remaining stones
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        return solve(0, 1, piles);
    }

    private int solve(int i, int M, int[] piles) {
        int n = piles.length;

        // Base case: If we can take all remaining piles, take them all
        if (i >= n) return 0;
        if (i + 2 * M >= n) return suffixSum[i];

        if (memo[i][M] != 0) return memo[i][M];

        int maxStones = 0;

        // Try taking X piles where 1 <= X <= 2 * M
        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);
            // Current player's score = total remaining stones - optimal score of the next player
            int currentStones = suffixSum[i] - solve(i + X, nextM, piles);
            maxStones = Math.max(maxStones, currentStones);
        }

        memo[i][M] = maxStones;
        return memo[i][M];
    }
}
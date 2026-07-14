class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // dp[g1][g2] stores the number of pairs of disjoint subsequences
        // with GCD g1 and g2 respectively.
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1; // Base case: both subsequences are empty

        for (int x : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) continue;

                    long count = dp[g1][g2];

                    // Choice 1: Skip the current element x
                    nextDp[g1][g2] = (int) ((nextDp[g1][g2] + count) % MOD);

                    // Choice 2: Include x in the first subsequence
                    int n1 = gcd(g1, x);
                    nextDp[n1][g2] = (int) ((nextDp[n1][g2] + count) % MOD);

                    // Choice 3: Include x in the second subsequence
                    int n2 = gcd(g2, x);
                    nextDp[g1][n2] = (int) ((nextDp[g1][n2] + count) % MOD);
                }
            }
            dp = nextDp;
        }

        // Sum up all valid pairs where both subsequences are non-empty (g > 0)
        // and have equal GCDs (g1 == g2)
        long ans = 0;
        for (int g = 1; g <= maxVal; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }

        return (int) ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
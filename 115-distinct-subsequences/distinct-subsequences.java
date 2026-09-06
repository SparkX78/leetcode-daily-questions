class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // Base case optimization: if t is longer than s, it's impossible
        if (m < n) return 0;
        
        // 1D DP array initialized to 0
        int[] dp = new int[n + 1];
        
        // Base case: empty string t can always be formed in 1 way
        dp[0] = 1;
        
        for (int i = 1; i <= m; i++) {
            char charS = s.charAt(i - 1);
            // Iterate backwards to avoid overwriting previous state
            for (int j = Math.min(i, n); j >= 1; j--) {
                if (charS == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return dp[n];
    }
}
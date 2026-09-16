class Solution {
    int[][] dp;
    int M = 1000000007;
    public int numberOfSets(int n, int K) {
        dp = new int[K+1][n+1];
        for(int i = 0; i < n; i++){
            dp[0][i] = 1;
        }
        
        for(int k = 1; k <= K ; k++){
            int[] preRow = new int[n+1];
            for(int d = n-1; d >= 0; d--){
                preRow[d] = (preRow[d+1] + dp[k-1][d]) % M;
            }
            for(int i = n-1; i >= 0; i--){
                int take = preRow[i+1];
                int skip = dp[k][i+1];
                dp[k][i] = (take + skip)% M;
            }
        }
        return dp[K][0];
    }
}
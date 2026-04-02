class Solution {
    int m;
    int n;
    Integer[][][] dp;
    public int maximumAmount(int[][] coins) {
        m = coins.length;
        n = coins[0].length;
        dp = new Integer[m+1][n+1][3];
        return solve(coins, 0, 0, 2);
    }
    public int solve(int[][] coins, int i, int j, int neu){
        if(i == m-1 && j == n-1){
            if(coins[i][j] < 0 && neu > 0){
                return 0;
            }
            return coins[i][j];
        }

        if(i >= m || j >= n){
            return Integer.MIN_VALUE;
        }
        if(dp[i][j][neu] != null){
            return dp[i][j][neu];
        }
        int take = coins[i][j] + Math.max(solve(coins, i+1, j, neu), solve(coins, i, j+1, neu));
        int skip = Integer.MIN_VALUE;
        if(coins[i][j] < 0 && neu > 0){
            int skip_down = solve(coins, i+1, j, neu-1);
            int skip_right = solve(coins, i, j+1, neu-1);
            skip = Math.max(skip_down, skip_right);
        }
        return dp[i][j][neu] = Math.max(take, skip);
    }
}
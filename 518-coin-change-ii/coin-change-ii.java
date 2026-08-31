class Solution {
    int[][] dp;
    public int change(int amount, int[] coins) {
        dp = new int[coins.length+1][amount+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        return solve(amount, coins, 0);
    }
    public int solve(int amount, int[] coins, int i){
        int n = coins.length;
        if(amount == 0){
            return 1;
        }
        if(i >= n){
            return 0;
        }
        if(dp[i][amount] != -1){
            return dp[i][amount];
        }
        if(amount < coins[i]){
            return dp[i][amount] = solve(amount, coins, i+1);
            
        }
        int take = solve(amount - coins[i], coins, i);
        int skip = solve(amount, coins, i+1);

        dp[i][amount] = take + skip;

        return dp[i][amount];
    }
}
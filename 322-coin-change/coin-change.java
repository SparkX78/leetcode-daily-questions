class Solution {
    public int coinChange(int[] coins, int amount) {
       int[][] dp = new int[coins.length][amount+1];
       for(int[] row : dp){
            Arrays.fill(row, -1);
       } 
       int result = solve(0, coins, amount, dp);
       return result == 1e9 ? -1 : result;
    }
    public int solve(int idx, int[] coins, int amount, int[][] dp){
        if(amount == 0){
            return 0;
        }
        if(idx == coins.length || amount < 0){
            return (int) 1e9;
        }
        if(dp[idx][amount] != -1){
            return dp[idx][amount];
        }
        int exclude = solve(idx+1, coins, amount, dp);
        int include = (int) 1e9;
        if(amount >= coins[idx]){
            include = 1 + solve(idx, coins, amount - coins[idx], dp);
        }
        return dp[idx][amount] = Math.min(include, exclude);
    }
}
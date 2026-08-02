class Solution {
    int[][] dp = new int[501][501];
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int sum = 0;
        for(int i = 0; i < piles.length; i++){
            sum += piles[i];
        }
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }
        int alice_score = solve(piles, 0, n-1);
        return alice_score > sum/2;
    }
    public int solve(int[] piles, int i, int j){
        if(i > j){
            return 0;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        int take_i = piles[i] + Math.min(solve(piles, i+2, j), solve(piles, i+1, j-1));
        int take_j = piles[j] + Math.min(solve(piles, i+1, j-1), solve(piles, i, j-2));

        return dp[i][j] = Math.max(take_i, take_j);
    }
}
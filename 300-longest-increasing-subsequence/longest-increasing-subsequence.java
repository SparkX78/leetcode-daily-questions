class Solution {
    int[][] dp;
    public int lengthOfLIS(int[] nums) {
        
    
        int n = nums.length;
        dp = new int[n+1][n+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        return solve(nums, 0, -1, n);
    }
    public int solve(int[] nums, int i, int P, int n){
        if(i >= n){
            return 0;
        }
        if(dp[i][P+1] != -1){
            return dp[i][P+1];
        }
        int take = 0;
        if(P == -1 || nums[i] > nums[P]){
            take = 1 + solve(nums, i+1, i, n);
        }
        int skip = solve(nums, i+1, P, n);

        return dp[i][P+1] = Math.max(take, skip);
    }
    
}
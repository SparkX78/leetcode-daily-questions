class Solution {
    int[][] dp;
    public int findTargetSumWays(int[] nums, int target) {
        int total = 0;
        int n = nums.length;
        for(int i = 0; i < nums.length; i++){
            total += nums[i];
        }
        dp = new int[n+1][2*total +1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return solve(nums, 0, 0, total, target);
    }
    public int solve(int[] nums, int i, int curSum, int total, int target){
        if(i == nums.length){
            if(curSum == target){
                return 1;
            }
            else{
                return 0;
            }
        }
        if(dp[i][curSum + total] != -1){
            return dp[i][curSum + total];
        }
        int plus = solve(nums, i+1, curSum + nums[i], total, target);
        int minus = solve(nums, i+1, curSum - nums[i], total, target);

        return dp[i][curSum + total] = plus + minus;
    }
}
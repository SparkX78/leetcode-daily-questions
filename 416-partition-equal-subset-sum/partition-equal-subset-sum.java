class Solution {
    Boolean[][] dp;
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        
        int sum  = 0;
        for(int i : nums){
            sum += i;
        }
        dp = new Boolean[n+1][sum+1];
        
        if(sum % 2 != 0){
            return false;
        }
        int x = sum/2;
        return solve(nums, 0,x );
    }
    public boolean solve(int[] nums, int i, int x){
        if(x == 0){
            return true;
        }
        if(i >= nums.length){
            return false;
        }
        if(dp[i][x] != null){
            return dp[i][x];
        }
        boolean take = false;
        boolean skip = false;
        if(nums[i] <= x){
            take = solve(nums, i+1, x-nums[i]);
        }
        skip = solve(nums, i+1, x);

        return dp[i][x] = take || skip;
    }

}
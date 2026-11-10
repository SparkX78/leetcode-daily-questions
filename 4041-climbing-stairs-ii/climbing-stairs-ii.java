import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        // Memoization table initialized to -1
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return solve(n, costs, memo);
    }
    
    private int solve(int j, int[] costs, int[] memo) {
        // Base case: starting step 0 has a cost of 0
        if (j == 0) {
            return 0;
        }
        
        // If already calculated, return the stored result
        if (memo[j] != -1) {
            return memo[j];
        }
        
        int minCost = Integer.MAX_VALUE;
        
        // Try jumping from j - 1, j - 2, or j - 3
        for (int k = 1; k <= 3; k++) {
            int prevStep = j - k;
            if (prevStep >= 0) {
                // Cost = cost to reach prevStep + current step cost + squared jump distance
                int jumpCost = solve(prevStep, costs, memo) + costs[j - 1] + (k * k);
                minCost = Math.min(minCost, jumpCost);
            }
        }
        
        return memo[j] = minCost;
    }
}
import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[32];
        
        // Count the number of set bits at each position
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCounts[i]++;
                }
            }
        }
        
        long totalSum = 0;
        long MOD = 1_000_000_007;
        
        // Greedily build the k largest possible numbers
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int bit = 0; bit < 32; bit++) {
                if (bitCounts[bit] > 0) {
                    currentNum |= (1L << bit);
                    bitCounts[bit]--;
                }
            }
            totalSum = (totalSum + (currentNum * currentNum) % MOD) % MOD;
        }
        
        return (int) totalSum;
    }
}
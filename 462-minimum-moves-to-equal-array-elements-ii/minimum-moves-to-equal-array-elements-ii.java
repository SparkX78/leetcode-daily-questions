import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        // Sort the array to easily find the median
        Arrays.sort(nums);
        
        int moves = 0;
        int median = nums[nums.length / 2];
        
        // Sum the absolute differences from the median
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        
        return moves;
    }
}
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        
        // Base cases for small n
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        // For n >= 3, any XOR sum from 0 up to (2^(msb + 1) - 1) can be formed.
        // Therefore, the total number of unique values is 2^(msb + 1).
        return Integer.highestOneBit(n) << 1;
    }
}
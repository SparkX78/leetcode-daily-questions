class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXOR = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            totalXOR ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }

        // If all elements are 0, we cannot form a non-zero XOR subsequence
        if (!hasNonZero) {
            return 0;
        }

        // If the entire array's XOR is non-zero, the answer is n
        // Otherwise, removing one non-zero element gives a non-zero XOR, so n - 1
        return totalXOR != 0 ? nums.length : nums.length - 1;
    }
}
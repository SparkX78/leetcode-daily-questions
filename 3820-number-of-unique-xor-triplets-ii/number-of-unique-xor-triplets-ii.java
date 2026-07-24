class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        // Find the next power of 2 to bound the maximum XOR value
        int maxPossibleXor = 1;
        while (maxPossibleXor <= maxVal) {
            maxPossibleXor <<= 1;
        }

        boolean[] pairXor = new boolean[maxPossibleXor];
        boolean[] tripletXor = new boolean[maxPossibleXor];

        // Step 1: Compute all unique pair XOR values
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                pairXor[nums[i] ^ nums[j]] = true;
            }
        }

        // Step 2: Combine pair XOR values with a third element
        for (int px = 0; px < maxPossibleXor; px++) {
            if (pairXor[px]) {
                for (int num : nums) {
                    tripletXor[px ^ num] = true;
                }
            }
        }

        // Step 3: Count unique triplet XOR values
        int count = 0;
        for (int i = 0; i < maxPossibleXor; i++) {
            if (tripletXor[i]) {
                count++;
            }
        }

        return count;
    }
}
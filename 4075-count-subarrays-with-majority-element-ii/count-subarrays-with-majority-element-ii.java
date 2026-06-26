class Solution {
    // Binary Indexed Tree (Fenwick Tree) to handle point updates and prefix queries
    private class FenwickTree {
        private int[] tree;
        private int size;

        public FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size + 1];
        }

        public void update(int index, int delta) {
            for (; index <= size; index += index & -index) {
                tree[index] += delta;
            }
        }

        public int query(int index) {
            int sum = 0;
            for (; index > 0; index -= index & -index) {
                sum += tree[index];
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        // Total possible range of prefix sums is from -n to n.
        // Size required is 2 * n + 1. We shift indices by adding (n + 1).
        FenwickTree bit = new FenwickTree(2 * n + 1);
        
        int currentPrefixSum = n + 1; 
        bit.update(currentPrefixSum, 1); // Insert the initial prefix sum of 0
        
        long totalSubarrays = 0;
        
        for (int num : nums) {
            // Transform element: +1 if it matches target, -1 otherwise
            currentPrefixSum += (num == target) ? 1 : -1;
            
            // Query the count of all previous prefix sums strictly less than currentPrefixSum
            totalSubarrays += bit.query(currentPrefixSum - 1);
            
            // Add the current prefix sum to the Fenwick Tree
            bit.update(currentPrefixSum, 1);
        }
        
        return totalSubarrays;
    }
}
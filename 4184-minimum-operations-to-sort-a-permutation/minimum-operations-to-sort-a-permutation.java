import java.util.function.IntPredicate;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;
        
        // Find the index of 0
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }
        
        final int finalZero = zero;
        
        // Helper to check if elements follow a consistent step (1 or -1) cyclically
        IntPredicate check = step -> {
            for (int i = 1; i < n; i++) {
                int prev = (finalZero + (i - 1) * step + n) % n;
                int curr = (finalZero + i * step + n) % n;
                if (nums[prev] > nums[curr]) {
                    return false;
                }
            }
            return true;
        };
        
        int ans = Integer.MAX_VALUE;
        
        // Case 1: Increasing order (step = 1)
        if (check.test(1)) {
            ans = Math.min(ans, zero);              // Pure left rotations
            ans = Math.min(ans, n - zero + 2);      // Reverse -> Rotate -> Reverse
        }
        
        // Case 2: Decreasing order (step = -1)
        if (check.test(-1)) {
            ans = Math.min(ans, zero + 2);          // Reverse -> Rotate (or Rotate -> Reverse)
            ans = Math.min(ans, n - zero);          // Rotate -> Reverse
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
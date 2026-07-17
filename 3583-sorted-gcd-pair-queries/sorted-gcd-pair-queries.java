import java.util.Arrays;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // 1. Count frequencies of each number
        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }

        // 2. Count how many elements are divisible by each i
        long[] divisibleCount = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            for (int j = i; j <= maxVal; j += i) {
                divisibleCount[i] += freq[j];
            }
        }

        // 3. Compute the exact count of pairs with GCD equal to i using inclusion-exclusion
        long[] gcdCount = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            long totalPairs = divisibleCount[i] * (divisibleCount[i] - 1) / 2;
            // Subtract pairs where the GCD is a larger multiple of i
            for (int j = 2 * i; j <= maxVal; j += i) {
                totalPairs -= gcdCount[j];
            }
            gcdCount[i] = totalPairs;
        }

        // 4. Compute prefix sums of the pair counts
        long[] pref = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            pref[i] = pref[i - 1] + gcdCount[i];
        }

        // 5. Answer each query using binary search
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = binarySearch(pref, queries[i]);
        }

        return ans;
    }

    private int binarySearch(long[] pref, long queryVal) {
        int low = 1, high = pref.length - 1;
        int ans = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (pref[mid] > queryVal) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid GCD index
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}
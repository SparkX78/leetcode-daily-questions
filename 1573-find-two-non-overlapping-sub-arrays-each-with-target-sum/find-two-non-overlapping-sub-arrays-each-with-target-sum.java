import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE);
        
        // Maps: PrefixSum -> Ending Index
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // Base case for subarrays starting at index 0
        
        int curSum = 0;
        int bestMin = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;
        
        // Single loop to traverse the array exactly once
        for (int j = 0; j < n; j++) {
            curSum += arr[j];
            map.put(curSum, j); // Store current prefix sum and its index
            
            // Check if there is a valid subarray ending at index j that sums to target
            if (map.containsKey(curSum - target)) {
                int i = map.get(curSum - target) + 1; // Start index of the subarray
                int len = j - i + 1;
                
                // If a previous non-overlapping valid subarray exists
                if (i > 0 && minLen[i - 1] != Integer.MAX_VALUE) {
                    result = Math.min(result, len + minLen[i - 1]);
                }
                
                bestMin = Math.min(bestMin, len);
            }
            
            minLen[j] = bestMin;
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}

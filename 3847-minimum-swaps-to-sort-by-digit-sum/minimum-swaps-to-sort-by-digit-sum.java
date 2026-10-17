import java.util.Arrays;

class Solution {
    
    // Helper function to calculate the digit sum of a number
    private int getDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        // Store indices and their corresponding digit sums using parallel arrays or packed representations
        Integer[] indices = new Integer[n];
        int[] digitSums = new int[n];
        
        for (int i = 0; i < n; i++) {
            indices[i] = i;
            digitSums[i] = getDigitSum(nums[i]);
        }

        // Sort indices based on custom criteria: digit sum ascending, then nums value ascending
        Arrays.sort(indices, (a, b) -> {
            if (digitSums[a] != digitSums[b]) {
                return Integer.compare(digitSums[a], digitSums[b]);
            }
            return Integer.compare(nums[a], nums[b]);
        });

        boolean[] visited = new boolean[n];
        int swaps = 0;

        // Cycle decomposition to find the minimum number of swaps
        for (int i = 0; i < n; i++) {
            if (visited[i] || indices[i] == i) {
                continue;
            }

            int cycleSize = 0;
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = indices[curr];
                cycleSize++;
            }

            if (cycleSize > 1) {
                swaps += (cycleSize - 1);
            }
        }

        return swaps;
    }
}
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int score_i = Greater(i, nums) - Small(i, nums);
            if (score_i <= k) {
                return i;
            }
        }
        return -1;
    }

    // FIXED: Loop from 0 to 'start' to get the prefix max
    public int Greater(int start, int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int j = 0; j <= start; j++) {
            max = Math.max(max, nums[j]);
        }
        return max;
    }

    public int Small(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int k = s; k < nums.length; k++) {
            min = Math.min(min, nums[k]);
        }
        return min;
    }
}
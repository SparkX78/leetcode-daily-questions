class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            // Found target
            if (nums[mid] == target) {
                return mid;
            }

            // Check which half is sorted
            if (nums[l] <= nums[mid]) {
                // Left half is sorted
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // Right half is sorted
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        return -1; // target not found
    }
}
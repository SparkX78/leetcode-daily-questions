class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        
        // Initial operations: append nums[0] to arr1, nums[1] to arr2
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        
        // Process remaining elements starting from index 2
        for (int i = 2; i < n; i++) {
            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }
        
        // Concatenate arr1 and arr2 into the result array
        int[] result = new int[n];
        int idx = 0;
        
        for (int val : arr1) {
            result[idx++] = val;
        }
        for (int val : arr2) {
            result[idx++] = val;
        }
        
        return result;
    }
}
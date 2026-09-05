class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int min_val = Integer.MAX_VALUE;
        int n = nums.length;
        int[] minPos = new int[nums.length];
        for(int i = n-1; i >= 0; i--){
            min_val = Math.min(min_val, nums[i]);
            minPos[i] = min_val;
        }
        int max_val = Integer.MIN_VALUE;
        for(int j = 0; j < n; j++  ){
            max_val = Math.max(max_val, nums[j]);
            min_val = minPos[j];
            if(max_val - min_val <= k){
                return j;
            }
        } 
        return -1; 
    }
}
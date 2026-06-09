class Solution {
    public long maxTotalValue(int[] nums, int k) {
        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        for(int num : nums){
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return k*(max-min);
    }
}
class Solution {
    public int[] leftRightDifference(int[] nums) {
        int leftSum = 0;
        int totalSum = 0;
        int[] result = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            totalSum += nums[i];
            
        }
        for(int i = 0; i < nums.length ; i++){
            totalSum -= nums[i];
            result[i] = Math.abs(leftSum - totalSum);
            leftSum += nums[i];
        }
        return result;
    }
}
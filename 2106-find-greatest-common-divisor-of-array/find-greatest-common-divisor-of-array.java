class Solution {
    public int findGCD(int[] nums) {
        Arrays.sort(nums);
        int smallest = nums[0];
        int largest = nums[nums.length-1];
        while(largest != 0){
            int temp = largest;
            largest = smallest% largest;
            smallest = temp;

        }
        return smallest;
    }
}
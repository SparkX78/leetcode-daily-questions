class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int[] prefixGCD = new int[n];
        int j = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] > max){
                max = nums[i];
                

            }
            prefixGCD[j++] = GCD(max, nums[i]);
        }
        Arrays.sort(prefixGCD);

        long totalSum = 0;
        int left = 0;
        int right = n - 1;
        
        while (left < right) {
            totalSum += GCD(prefixGCD[left], prefixGCD[right]);
            left++;
            right--;
        }
        
        // If n is odd, the middle element (where left == right) is automatically ignored
        return totalSum;

    }
    public int GCD(int max, int value){
        while(value != 0){
            int temp = value;
            value = max % value;
            max = temp;
        }
        return max;
    }
}
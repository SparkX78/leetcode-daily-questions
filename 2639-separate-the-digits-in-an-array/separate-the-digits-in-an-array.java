class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int i = nums.length-1; i >= 0; i--){
            while(nums[i] > 0){
                list.add(nums[i] % 10);
                nums[i] = nums[i]/10;
            }
        }
        int[] ans = new int[list.size()];
        int ind = 0;
        for(int i = list.size()-1; i >= 0; i--){
            ans[ind++] = list.get(i);
        }
        return ans;
    }
}
class Solution {
    public int minimumCost(int[] cost) {
        int ans = 0;
        int taken = 0;
        Arrays.sort(cost);
        for(int i = cost.length-1; i >= 0; i--){
            if(taken == 2){
                taken = 0;
            }
            else{
                ans += cost[i];
                taken++;
            }
            
        }
        return ans;
    }
}
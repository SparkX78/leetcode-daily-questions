class Solution {
    int[][] t = new int[23][23];
    public boolean predictTheWinner(int[] nums) {
        
        int n = nums.length;
        int player1_score = solve(0,n-1, nums);
        int total_score = 0;
        for(int i = 0; i < n; i++){
            total_score += nums[i];
        }
        for(int[] row : t){
            Arrays.fill(row,-1);
        }
        int player2_score = total_score - player1_score;
        return player1_score >= player2_score;
    }
    public int solve(int i, int j, int[] nums){
        if(i > j){
            return 0;
        }
        if(t[i][j] == -1){
            return 0;
        }
        int take_i = nums[i] + Math.min(solve(i+2, j, nums), solve(i+1, j-1, nums));
        int take_j = nums[j] + Math.min(solve(i, j-2, nums), solve(i+1, j-1, nums));

        return t[i][j] = Math.max(take_i, take_j);
    }
}
class Solution {
    int[][][] dp;
    int m ;
    int n ;
    public int cherryPickup(int[][] grid) {
       m = grid.length;
       n = grid[0].length;
       dp = new int[m][n][n];
       for(int i = 0 ; i < m ; i++){
            for(int j = 0; j < n; j++){
                Arrays.fill(dp[i][j], -1);
            }
            
       }
       return solve(grid, 0, 0 , n-1);
       
    }
    public int solve(int[][] grid, int row, int c1, int c2){
        if(row == m-1){
            if(c1 == c2){
                return grid[row][c1];
            }
            return grid[row][c1]+ grid[row][c2];

            
        }
        if(dp[row][c1][c2] != -1){
            return dp[row][c1][c2];
        }
        
        int ans = 0;
        for(int i = -1; i <=1; i++){
            for(int j = -1; j <=1; j++){
                int new_row = row+1;
                int new_c1 = c1+ i;
                int new_c2 = c2 + j;
                if(new_c1 >= 0 && new_c1 < n && new_c2 >=  0 && new_c2 < n ){
                    int cherry = (c1 == c2) ? grid[row][c1] : grid[row][c1] + grid[row][c2];
                    cherry += solve(grid, new_row, new_c1, new_c2);
                    ans = Math.max(ans, cherry);
                }
            }
        }
        return dp[row][c1][c2] = ans;
    }
}
class Solution {
    int[][] dp;
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        dp = new int[m+1][n+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        return solve(grid, 0, 0, m, n);
    }
    public int solve(int[][] grid, int i, int j, int m, int n){
        if(i == m-1 && j == n-1){
            return grid[i][j] ;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        if(i == m-1){
            dp[i][j] =  grid[i][j] + solve(grid, i, j+1, m, n);
        }
        else if(j == n-1){
            dp[i][j] = grid[i][j] + solve(grid, i+1, j, m, n);
        }
        else{
            dp[i][j] = grid[i][j] + Math.min(solve(grid, i, j+1, m,n), solve(grid, i+1, j, m, n));
        }
        return dp[i][j];
    }
}
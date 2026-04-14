class Solution {
    int[][] dirs = {{0,1}, {0, -1}, {-1, 0}, {1, 0}};
    int[][] dp;
    int m, n;
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        dp = new int[m][n];
        
        int max = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
            
                max = Math.max(max,dfs(matrix, i , j, m, n, dp) );
                
            }
        }
        return max;

    }
    private int dfs(int[][] matrix, int i, int j, int m, int n, int[][] dp){
        int maxLen = 1;
        if(dp[i][j] != 0){
            return dp[i][j];
        }
        for(int[] d : dirs){
            int new_i = i+ d[0];
            int new_j = j+ d[1];
            if(new_i >= 0 && new_j >= 0 && new_i < m && new_j < n && matrix[new_i][new_j] > matrix[i][j]){
                maxLen = Math.max(maxLen, 1+ dfs(matrix, new_i, new_j, m, n, dp));
            }
        }
        dp[i][j] = maxLen;
        return maxLen;
    }
}
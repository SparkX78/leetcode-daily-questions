class Solution {
    
    static final long MOD = 1000000007;
    
    Long[][] maxMemo;
    Long[][] minMemo;
    int[][] grid;
    int m, n;
    
    public int maxProductPath(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        
        maxMemo = new Long[m][n];
        minMemo = new Long[m][n];
        
        long[] res = dfs(0, 0);
        long max = res[0];
        
        return max >= 0 ? (int)(max % MOD) : -1;
    }
    
    private long[] dfs(int i, int j) {
        
        // destination
        if (i == m - 1 && j == n - 1) {
            return new long[]{grid[i][j], grid[i][j]};
        }
        
        // memo hit
        if (maxMemo[i][j] != null) {
            return new long[]{maxMemo[i][j], minMemo[i][j]};
        }
        
        long maxVal = Long.MIN_VALUE;
        long minVal = Long.MAX_VALUE;
        
        // down
        if (i + 1 < m) {
            long[] down = dfs(i + 1, j);
            
            long a = down[0] * grid[i][j];
            long b = down[1] * grid[i][j];
            
            maxVal = Math.max(maxVal, Math.max(a, b));
            minVal = Math.min(minVal, Math.min(a, b));
        }
        
        // right
        if (j + 1 < n) {
            long[] right = dfs(i, j + 1);
            
            long a = right[0] * grid[i][j];
            long b = right[1] * grid[i][j];
            
            maxVal = Math.max(maxVal, Math.max(a, b));
            minVal = Math.min(minVal, Math.min(a, b));
        }
        
        maxMemo[i][j] = maxVal;
        minMemo[i][j] = minVal;
        
        return new long[]{maxVal, minVal};
    }
}
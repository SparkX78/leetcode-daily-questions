class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;
        
        // Prefix count arrays for 'X' and 'Y'
        int[][] prefX = new int[m][n];
        int[][] prefY = new int[m][n];
        int result = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Current cell values
                int xVal = (grid[i][j] == 'X' ? 1 : 0);
                int yVal = (grid[i][j] == 'Y' ? 1 : 0);

                // Standard 2D prefix sum logic
                prefX[i][j] = xVal;
                prefY[i][j] = yVal;

                if (i > 0) {
                    prefX[i][j] += prefX[i - 1][j];
                    prefY[i][j] += prefY[i - 1][j];
                }
                if (j > 0) {
                    prefX[i][j] += prefX[i][j - 1];
                    prefY[i][j] += prefY[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    prefX[i][j] -= prefX[i - 1][j - 1];
                    prefY[i][j] -= prefY[i - 1][j - 1];
                }

                // Check the given conditions
                if (prefX[i][j] == prefY[i][j] && prefX[i][j] > 0) {
                    result++;
                }
            }
        }

        return result;
    }
}

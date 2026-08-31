class Solution {
    int[][] dp;
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        dp = new int[m+1][n+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        return solve(word1, word2, 0, 0);
    }
    public int solve(String word1, String word2, int i, int j){
        if(i == word1.length()){
            return word2.length()-j;
        }
        if(j == word2.length()){
            return word1.length()-i;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        if(word1.charAt(i) == word2.charAt(j)){
            return dp[i][j] = solve(word1, word2, i+1, j+1);
        }
        int insert = solve(word1, word2, i, j+1);
        int delete = solve(word1, word2, i+1, j);
        int replace = solve(word1, word2, i+1, j+1);

        return dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
    }
}
class Solution {
    Boolean[][] dp ;
    public String longestPalindrome(String s) {
        int n = s.length();
        dp = new Boolean[n+1][n+1];
        
        int maxLen = Integer.MIN_VALUE;
        int start = 0;
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                if(solve(s,i, j) == true){
                    if(j-i+1 > maxLen){
                        maxLen = j-i+1;
                        start = i;
                    }
                }
                
            }
        }
        return s.substring(start, start + maxLen);
    }
    public boolean solve(String s, int i, int j){
        if(i >= j){
            return true;
        }
        if(dp[i][j] != null){
            return dp[i][j];
        }
        if(s.charAt(i) == s.charAt(j)){
            dp[i][j] = solve(s, i+1, j-1);
        }
        else{
            dp[i][j] = false;
        }
        return dp[i][j];
    }
}
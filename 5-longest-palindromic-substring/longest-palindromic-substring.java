class Solution {
    public String longestPalindrome(String s) {
        int maxLen = Integer.MIN_VALUE;
        int sp = 0;
        for(int i = 0; i < s.length(); i++){
            for(int j = i; j < s.length(); j++){
                if(solve(s, i, j)){
                    if(j-i+1 > maxLen){
                        maxLen = j-i+1;
                        sp = i;
                    }
                }
            }
        }
        return s.substring(sp, sp+ maxLen);
    }
    private boolean solve(String s, int i, int j){
        if(i >= j){
            return true;
        }
        if(s.charAt(i) == s.charAt(j)){
            return solve(s, i+1, j-1);
        }
        return false;
    }
}
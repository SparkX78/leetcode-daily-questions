class Solution {
    public int numberOfSubstrings(String s) {
        int left = 0;
        int right = 0 ;
        int n = s.length();
        int[] count = new int[3];
        int ans = 0;
        while(right < n){
            count[s.charAt(right)-'a']++;
            while(isValid(count)){
                ans += n-right;
                count[s.charAt(left)-'a']--;
                left++;
            }
            right++;

        }
        return ans;

    }
    boolean isValid(int[] count){
        if(count[0] > 0 && count[1] > 0 && count[2] > 0){
            return true;
        }
        return false;
    }
}
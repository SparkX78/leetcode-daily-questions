class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int count = 0;
        int left = 0;
        String ans = "";
        for(int right = 0; right < n; right ++){
            if(s.charAt(right) == '1'){
                count++;
            }
            while(count == k){
                String sub = s.substring(left, right+1);
                if(ans.isEmpty() || sub.length() < ans.length() || sub.length() == ans.length() && sub.compareTo(ans) < 0){
                    ans = sub;
                }
                if(s.charAt(left) == '1'){
                    count--;
                }
                left++;
            }

        } 
        return ans;  
    }
}
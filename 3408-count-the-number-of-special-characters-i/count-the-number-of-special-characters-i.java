class Solution {
    public int numberOfSpecialChars(String word) {
        
        int[] high = new int[26];
        int[] low = new int[26];
        for(char ch : word.toCharArray()){
            if(ch >= 'a' && ch <= 'z'){
                high[ch - 'a']++;
            }
            else if(ch >= 'A' && ch <= 'Z'){
                low[ch - 'A']++;
            }
        }
        int count = 0;
        for(int i = 0; i < 26; i++){
            if(high[i] > 0 && low[i] > 0){
                count++;
            }
        }
        return count;
        
    }
}
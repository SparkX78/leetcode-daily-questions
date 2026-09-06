class Solution {
    public String minWindow(String s, String t) {
        if(t.length() > s.length()){
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for(char ch : t.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }
        int i = 0;
        int j = 0;
        int n = s.length();
        int minLen = Integer.MAX_VALUE;
        int start_i = 0;
        int count = t.length();
        while(j < n ){
            if(map.containsKey(s.charAt(j))){
                if(map.get(s.charAt(j)) > 0){
                    count--;
                    
                }
                map.put(s.charAt(j), map.get(s.charAt(j))-1);
            }
            
            while(count == 0){
                int curLen = j-i+1;
                if(curLen < minLen){
                    minLen = curLen;
                    start_i = i;
                }
                if(map.containsKey(s.charAt(i))){
                    map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
                    if(map.get(s.charAt(i)) > 0){
                        count++;
                    }
                }
                
                i++;
            }
            j++;
        }
        return minLen == Integer.MAX_VALUE ? "": s.substring(start_i, start_i + minLen);
    }
}
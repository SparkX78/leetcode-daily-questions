class Solution {
    public String smallestSubsequence(String s) {
        // Store the last seen index of each character
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        // Keep track of characters currently in the result stack
        boolean[] visited = new boolean[26];
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            
            // If character is already included, skip it
            if (visited[curr - 'a']) {
                continue;
            }
            
            // Maintain monotonic increasing order if the popped element appears later
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) > curr && lastIndex[sb.charAt(sb.length() - 1) - 'a'] > i) {
                char removed = sb.charAt(sb.length() - 1);
                visited[removed - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            
            // Add current character to result
            sb.append(curr);
            visited[curr - 'a'] = true;
        }
        
        return sb.toString();
    }
}
class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        // last[j] stores the largest index in word1 from where the suffix word2[j...] 
        // can be matched as a subsequence.
        int[] last = new int[m + 1];
        last[m] = n;
        
        int ptr = n - 1;
        for (int j = m - 1; j >= 0; j--) {
            while (ptr >= 0 && word1.charAt(ptr) != word2.charAt(j)) {
                ptr--;
            }
            last[j] = ptr;
            if (ptr >= 0) {
                ptr--;
            }
        }
        
        int[] result = new int[m];
        boolean usedChange = false;
        int word1Idx = 0;
        
        for (int j = 0; j < m; j++) {
            boolean matched = false;
            while (word1Idx < n) {
                // Scenario 1: Exact character match
                if (word1.charAt(word1Idx) == word2.charAt(j)) {
                    result[j] = word1Idx;
                    word1Idx++;
                    matched = true;
                    break;
                } 
                // Scenario 2: Single allowed change match
                else if (!usedChange && last[j + 1] > word1Idx) {
                    usedChange = true;
                    result[j] = word1Idx;
                    word1Idx++;
                    matched = true;
                    break;
                }
                
                word1Idx++;
            }
            
            // If we couldn't match the j-th character, no valid sequence exists
            if (!matched) {
                return new int[0];
            }
        }
        
        return result;
    }
}
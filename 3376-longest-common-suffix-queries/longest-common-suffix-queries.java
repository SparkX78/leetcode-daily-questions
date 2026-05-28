class Solution {
    // Define the Trie Node structure
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        // Stores the index of the optimal string passing through or ending at this node
        int minIndex = -1; 
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        TrieNode root = new TrieNode();
        
        // Find the absolute default best index for the global root
        // (This handles cases where a query shares NO common suffix with any container word)
        int globalBestIndex = 0;
        for (int i = 1; i < wordsContainer.length; i++) {
            if (shouldUpdate(wordsContainer, i, globalBestIndex)) {
                globalBestIndex = i;
            }
        }
        root.minIndex = globalBestIndex;

        // 1. Build the Trie with words from wordsContainer (Reversed)
        for (int i = 0; i < wordsContainer.length; i++) {
            String word = wordsContainer[i];
            TrieNode curr = root;
            
            // Traverse from the end of the string to the beginning
            for (int j = word.length() - 1; j >= 0; j--) {
                int charIdx = word.charAt(j) - 'a';
                if (curr.children[charIdx] == null) {
                    curr.children[charIdx] = new TrieNode();
                }
                curr = curr.children[charIdx];
                
                // Update the best index at this node if the current word is a better match
                if (curr.minIndex == -1 || shouldUpdate(wordsContainer, i, curr.minIndex)) {
                    curr.minIndex = i;
                }
            }
        }

        // 2. Answer the queries
        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            String query = wordsQuery[i];
            TrieNode curr = root;
            int bestIdx = root.minIndex; // Start with the global fallback
            
            // Search from the end of the query string backwards
            for (int j = query.length() - 1; j >= 0; j--) {
                int charIdx = query.charAt(j) - 'a';
                if (curr.children[charIdx] == null) {
                    break; // No deeper common suffix found
                }
                curr = curr.children[charIdx];
                bestIdx = curr.minIndex; // Update to the deeper, longer match
            }
            ans[i] = bestIdx;
        }

        return ans;
    }

    // Helper method to determine if candidate index 'currIdx' is better than 'existingIdx'
    private boolean shouldUpdate(String[] wordsContainer, int currIdx, int existingIdx) {
        int currLen = wordsContainer[currIdx].length();
        int existingLen = wordsContainer[existingIdx].length();
        
        if (currLen < existingLen) {
            return true;
        } else if (currLen == existingLen) {
            return currIdx < existingIdx; // Ties broken by earlier appearance
        }
        return false;
    }
}
class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] s = new char[n];
        char cur = 'a';

        // Step 1: Greedy assignment of characters
        for (int i = 0; i < n; i++) {
            if (s[i] == 0) { // If character not assigned
                if (cur > 'z') return ""; // More than 26 distinct characters needed
                
                for (int j = i; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        s[j] = cur;
                    }
                }
                cur++;
            }
        }

        // Step 2: Verification of the LCP matrix
        // We iterate backwards to use DP-like relation: lcp[i][j] = 1 + lcp[i+1][j+1]
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int expectedLcp = 0;
                if (s[i] == s[j]) {
                    expectedLcp = 1;
                    if (i + 1 < n && j + 1 < n) {
                        expectedLcp += lcp[i + 1][j + 1];
                    }
                }
                
                if (lcp[i][j] != expectedLcp) {
                    return "";
                }
            }
        }

        return new String(s);
    }
}
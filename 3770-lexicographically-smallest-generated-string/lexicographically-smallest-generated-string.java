class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int N = n + m - 1;

        char[] result = new char[N];
        boolean[] fixed = new boolean[N];

        // Step 1: Initialize result
        for (int i = 0; i < N; i++) {
            result[i] = '?';
        }

        // Step 2: Apply 'T' constraints (Fixed positions)
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    char targetChar = str2.charAt(j);
                    if (result[i + j] != '?' && result[i + j] != targetChar) {
                        return ""; // Irreconcilable conflict between 'T' constraints
                    }
                    result[i + j] = targetChar;
                    fixed[i + j] = true;
                }
            }
        }

        // Step 3: Fill '?' with 'a' initially
        for (int i = 0; i < N; i++) {
            if (result[i] == '?') {
                result[i] = 'a';
            }
        }

        // Step 4: Satisfy 'F' constraints
        // We iterate and if an 'F' position matches str2, we try to change 
        // the rightmost non-fixed character in that range to 'b'.
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                while (isSame(result, str2, i, m)) {
                    // Match found at an 'F' position. Try to break it.
                    boolean broken = false;
                    // Look for the rightmost character in this window that we can change
                    for (int j = m - 1; j >= 0; j--) {
                        if (!fixed[i + j]) {
                            // If it was 'a', make it 'b'. If 'b', make it 'c' (or just 'b' is usually enough)
                            result[i + j] = (result[i + j] == 'a') ? 'b' : 'a';
                            broken = true;
                            break; 
                        }
                    }
                    
                    if (!broken) {
                        // All characters in this 'F' window were forced by 'T' constraints
                        return ""; 
                    }
                }
            }
        }

        return new String(result);
    }

    // Helper function to check if substring matches str2
    private boolean isSame(char[] result, String str2, int start, int m) {
        for (int j = 0; j < m; j++) {
            if (result[start + j] != str2.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
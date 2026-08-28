import java.util.Arrays;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        if (n != target.length()) {
            return ""; 
        }

        int half = n / 2;
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Validate palindrome condition & find center character
        char center = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                if (center != 0) return ""; // More than 1 odd frequency char
                center = (char) ('a' + i);
            }
            count[i] /= 2; // Keep half count for building the first half
        }

        // 1. Check if the absolute smallest palindromic permutation is already > target
        StringBuilder minHead = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int k = 0; k < count[i]; k++) {
                minHead.append((char) ('a' + i));
            }
        }
        String minPal = buildPalindrome(minHead.toString(), center);
        if (minPal.compareTo(target) > 0) {
            return minPal;
        }

        // 2. Try to match a prefix of target's first half of length `i` (0 <= i <= half)
        // and set the i-th character to something strictly greater than target[i].
        int[] prefCount = count.clone();
        
        // Find maximum prefix match with target's first half
        int matchedLen = 0;
        while (matchedLen < half) {
            int tIdx = target.charAt(matchedLen) - 'a';
            if (prefCount[tIdx] > 0) {
                prefCount[tIdx]--;
                matchedLen++;
            } else {
                break;
            }
        }

        // Case A: Full first half can match target's first half
        if (matchedLen == half) {
            String head = target.substring(0, half);
            String fullPal = buildPalindrome(head, center);
            if (fullPal.compareTo(target) > 0) {
                return fullPal;
            }
        }

        // Case B: Backtrack from matchedLen down to 0 to find the rightmost index
        // where we can pick a larger character than target[i]
        int[] remCount = count.clone();
        for (int i = 0; i < matchedLen; i++) {
            remCount[target.charAt(i) - 'a']--;
        }

        for (int i = matchedLen; i >= 0; i--) {
            if (i < half) {
                int targetCharIdx = target.charAt(i) - 'a';
                
                // Try placing a character strictly larger than target[i]
                for (int c = targetCharIdx + 1; c < 26; c++) {
                    if (remCount[c] > 0) {
                        remCount[c]--;

                        StringBuilder head = new StringBuilder();
                        head.append(target, 0, i);
                        head.append((char) ('a' + c));

                        // Fill remaining slots of first half with smallest available characters
                        for (int ch = 0; ch < 26; ch++) {
                            while (remCount[ch] > 0) {
                                head.append((char) ('a' + ch));
                                remCount[ch]--;
                            }
                        }

                        return buildPalindrome(head.toString(), center);
                    }
                }
            }

            // Restore the character at index i - 1 for the next loop iteration (backtracking)
            if (i > 0) {
                remCount[target.charAt(i - 1) - 'a']++;
            }
        }

        return "";
    }

    private String buildPalindrome(String head, char center) {
        StringBuilder sb = new StringBuilder(head);
        if (center != 0) {
            sb.append(center);
        }
        for (int i = head.length() - 1; i >= 0; i--) {
            sb.append(head.charAt(i));
        }
        return sb.toString();
    }
}
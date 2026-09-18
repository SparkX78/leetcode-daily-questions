import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        // Find the first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] == -1) {
                first[ch] = i;
            }
            last[ch] = i;
        }

        List<String> result = new ArrayList<>();
        int lastRight = -1; // End boundary of the previously selected valid substring

        for (int i = 0; i < n; i++) {
            // Only consider starting a valid substring at the first occurrence of a character
            if (i == first[s.charAt(i) - 'a']) {
                int right = getValidRightBoundary(s, i, first, last);

                if (right != -1) {
                    // If the current valid range is completely after the previously chosen substring
                    if (i > lastRight) {
                        result.add(""); // Placeholder for a new substring
                    }
                    // Greedily shrink/update to the current minimal rightmost substring
                    result.set(result.size() - 1, s.substring(i, right + 1));
                    lastRight = right;
                }
            }
        }

        return result;
    }

    private int getValidRightBoundary(String s, int start, int[] first, int[] last) {
        int right = last[s.charAt(start) - 'a'];

        for (int i = start; i <= right; i++) {
            int ch = s.charAt(i) - 'a';
            // If a character inside starts before our starting point, this range is invalid
            if (first[ch] < start) {
                return -1;
            }
            // Expand the right boundary to include all occurrences of the inner characters
            right = Math.max(right, last[ch]);
        }

        return right;
    }
}
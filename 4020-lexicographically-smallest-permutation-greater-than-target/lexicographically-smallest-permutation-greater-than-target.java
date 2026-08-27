class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // Try to match a prefix of target of length `len` (from n down to 0)
        // then place a strictly larger character at position `len`.
        for (int len = n; len >= 0; len--) {
            int[] curFreq = freq.clone();
            boolean possible = true;
            
            // Verify if we can construct target[0 ... len-1] using available characters
            for (int i = 0; i < len; i++) {
                int c = target.charAt(i) - 'a';
                if (curFreq[c] > 0) {
                    curFreq[c]--;
                } else {
                    possible = false;
                    break;
                }
            }
            
            if (!possible) continue;

            // If we matched the full string, target itself can't be strictly greater
            if (len == n) continue;

            // At index `len`, pick the smallest available character strictly greater than target[len]
            int targetChar = target.charAt(len) - 'a';
            int nextChar = -1;
            for (int c = targetChar + 1; c < 26; c++) {
                if (curFreq[c] > 0) {
                    nextChar = c;
                    break;
                }
            }

            // If a valid character exists, construct the final result
            if (nextChar != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.substring(0, len));
                sb.append((char) ('a' + nextChar));
                curFreq[nextChar]--;

                // Append remaining characters in ascending order to keep it minimal
                for (int c = 0; c < 26; c++) {
                    while (curFreq[c] > 0) {
                        sb.append((char) ('a' + c));
                        curFreq[c]--;
                    }
                }
                return sb.toString();
            }
        }

        return "";
    }
}
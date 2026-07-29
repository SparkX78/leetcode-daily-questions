import java.util.*;

class Solution {
    private static final long INF = 1_000_000_000_000_000_000L; // Safety threshold above max k (10^6)

    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int halfLen = n / 2;

        // Count character frequencies for the first half
        int[] freq = new int[26];
        for (int i = 0; i < halfLen; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // Check if there are enough total permutations
        long totalPerms = countPermutations(freq, halfLen, k);
        if (totalPerms < k) {
            return "";
        }

        StringBuilder leftHalf = new StringBuilder();
        int remainingLen = halfLen;
        long targetK = k;

        // Build the left half character by character
        for (int i = 0; i < halfLen; i++) {
            for (int c = 0; c < 26; c++) {
                if (freq[c] == 0) continue;

                // Try placing character 'a' + c
                freq[c]--;
                long count = countPermutations(freq, remainingLen - 1, targetK);

                if (count >= targetK) {
                    leftHalf.append((char) ('a' + c));
                    remainingLen--;
                    break; // Fixed character at current index
                } else {
                    targetK -= count;
                    freq[c]++; // Backtrack
                }
            }
        }

        // Build result: leftHalf + (middle char if odd) + reversed(leftHalf)
        StringBuilder result = new StringBuilder(leftHalf);
        if (n % 2 == 1) {
            result.append(s.charAt(halfLen));
        }
        result.append(new StringBuilder(leftHalf).reverse());

        return result.toString();
    }

    /**
     * Calculates the number of unique permutations of remaining characters:
     * N! / (f1! * f2! * ... * fn!) capped at maxK to prevent overflow.
     */
    private long countPermutations(int[] freq, int totalLen, long maxK) {
        if (totalLen == 0) return 1;

        long perms = 1;
        int currentLen = totalLen;

        for (int count : freq) {
            if (count == 0) continue;

            // Compute C(currentLen, count) = currentLen! / (count! * (currentLen - count)!)
            long comb = combination(currentLen, count, maxK);
            perms = multiplyCapped(perms, comb, maxK);

            if (perms >= maxK) return maxK;

            currentLen -= count;
        }

        return perms;
    }

    // Helper to compute C(n, r) safely without overflow
    private long combination(int n, int r, long maxK) {
        if (r > n - r) r = n - r; // C(n, r) == C(n, n - r)
        long res = 1;
        for (int i = 1; i <= r; i++) {
            res = res * (n - i + 1) / i;
            if (res >= maxK) return maxK;
        }
        return res;
    }

    // Safely multiply two numbers and cap at maxK
    private long multiplyCapped(long a, long b, long maxK) {
        if (a == 0 || b == 0) return 0;
        if (a >= maxK || b >= maxK) return maxK;
        if (a > maxK / b) return maxK;
        return a * b;
    }
}
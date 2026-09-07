class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        // end[i] stores the number of distinct subsequences ending with character ('a' + i)
        long[] end = new long[26];
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            // Total subsequences formed so far (plus 1 for the empty subsequence)
            long currentSum = 1;
            for (int i = 0; i < 26; i++) {
                currentSum = (currentSum + end[i]) % MOD;
            }
            // Appending character 'c' to all existing subsequences creates currentSum new subsequences ending in 'c'
            end[idx] = currentSum;
        }
        
        // Sum up all distinct subsequences
        long result = 0;
        for (int i = 0; i < 26; i++) {
            result = (result + end[i]) % MOD;
        }
        
        return (int) result;
    }
}
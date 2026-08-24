class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] pref = new int[n];
        pref[0] = stones[0];
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] + stones[i];
        }

        // Alice must pick at least 2 stones, so the last possible state for the current player 
        // to choose from is index n - 1.
        int maxDiff = pref[n - 1];

        // Iterate backwards from n - 2 down to 1 (since x > 1, prefix index must be >= 1)
        for (int i = n - 2; i >= 1; i--) {
            maxDiff = Math.max(maxDiff, pref[i] - maxDiff);
        }

        return maxDiff;
    }
}
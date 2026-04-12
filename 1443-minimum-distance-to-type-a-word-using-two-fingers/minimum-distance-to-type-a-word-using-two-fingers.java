class Solution {
    private Integer[][] memo;
    private String word;

    public int minimumDistance(String word) {
        this.word = word;
        // memo[index][otherFingerPos]
        // 26 represents a finger that hasn't been placed yet (starting position)
        this.memo = new Integer[word.length()][27];
        return solve(1, 26);
    }

    private int solve(int idx, int otherFinger) {
        // Base case: finished the word
        if (idx == word.length()) {
            return 0;
        }

        if (memo[idx][otherFinger] != null) {
            return memo[idx][otherFinger];
        }

        int prevChar = word.charAt(idx - 1) - 'A';
        int currChar = word.charAt(idx) - 'A';

        // Option 1: Use the finger that just typed the previous character
        int opt1 = getDist(prevChar, currChar) + solve(idx + 1, otherFinger);

        // Option 2: Use the "other" finger
        int opt2 = getDist(otherFinger, currChar) + solve(idx + 1, prevChar);

        return memo[idx][otherFinger] = Math.min(opt1, opt2);
    }

    private int getDist(int from, int to) {
        // If finger hasn't been placed yet, cost is 0
        if (from == 26) return 0;
        
        int r1 = from / 6, c1 = from % 6;
        int r2 = to / 6, c2 = to % 6;
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }
        
        // If count[0] is even, Alice wins if both 1s and 2s exist.
        if (count[0] % 2 == 0) {
            return count[1] >= 1 && count[2] >= 1;
        }
        
        // If count[0] is odd, Alice wins if difference between 1s and 2s is greater than 2.
        return Math.abs(count[1] - count[2]) > 2;
    }
}
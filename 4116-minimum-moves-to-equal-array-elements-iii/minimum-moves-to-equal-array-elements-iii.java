class Solution {
    public int minMoves(int[] nums) {
        int mx = Integer.MIN_VALUE;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        long res = 0;
        for (int x : nums) {
            res += (mx - x);
        }
        return (int)res;
    }
}
class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {

        int n = s.length();

        boolean[] reachable = new boolean[n];
        reachable[0] = true;

        int count = 0;

        for (int i = 1; i < n; i++) {

            // add new index entering window
            if (i - minJump >= 0 && reachable[i - minJump]) {
                count++;
            }

            // remove old index leaving window
            if (i - maxJump - 1 >= 0 && reachable[i - maxJump - 1]) {
                count--;
            }

            // current position reachable?
            if (s.charAt(i) == '0' && count > 0) {
                reachable[i] = true;
            }
        }

        return reachable[n - 1];
    }
}
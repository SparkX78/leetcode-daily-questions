import java.util.Arrays;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        // If there are no restrictions, the max height will simply be at building n (n - 1)
        if (restrictions.length == 0) {
            return n - 1;
        }

        // 1. Create an expanded list to include explicit boundaries for building 1
        // We'll also append building n after sorting to handle it neatly.
        int[][] extended = new int[restrictions.length + 1][2];
        extended[0] = new int[]{1, 0};
        System.arraycopy(restrictions, 0, extended, 1, restrictions.length);

        // 2. Sort restrictions by building ID
        Arrays.sort(extended, (a, b) -> Integer.compare(a[0], b[0]));

        // Append the last building n if it's not already restricted
        if (extended[extended.length - 1][0] != n) {
            int[][] temp = new int[extended.length + 1][2];
            System.arraycopy(extended, 0, temp, 0, extended.length);
            temp[temp.length - 1] = new int[]{n, n - 1};
            extended = temp;
        }

        int m = extended.length;

        // 3. Forward Pass: Left to Right
        for (int i = 1; i < m; i++) {
            int limitFromLeft = extended[i - 1][1] + (extended[i][0] - extended[i - 1][0]);
            extended[i][1] = Math.min(extended[i][1], limitFromLeft);
        }

        // 4. Backward Pass: Right to Left
        for (int i = m - 2; i >= 0; i--) {
            int limitFromRight = extended[i + 1][1] + (extended[i + 1][0] - extended[i][0]);
            extended[i][1] = Math.min(extended[i][1], limitFromRight);
        }

        // 5. Find the absolute maximum height between any two adjacent restricted buildings
        int maxHeight = 0;
        for (int i = 0; i < m - 1; i++) {
            int id1 = extended[i][0];
            int h1 = extended[i][1];
            int id2 = extended[i + 1][0];
            int h2 = extended[i + 1][1];

            // Formula to find the highest point between two restrictions
            int peak = (h1 + h2 + (id2 - id1)) / 2;
            maxHeight = Math.max(maxHeight, peak);
        }

        return maxHeight;
    }
}
import java.util.*;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] pos = new long[n];
        for (int i = 0; i < n; i++) {
            pos[i] = getPerimeterPos(points[i][0], points[i][1], side);
        }

        // Sort points based on their perimeter position
        long[] combined = new long[n];
        for (int i = 0; i < n; i++) {
            combined[i] = (pos[i] << 32) | i;
        }
        Arrays.sort(combined);

        int[] sortedX = new int[n];
        int[] sortedY = new int[n];
        for (int i = 0; i < n; i++) {
            int originalIdx = (int) (combined[i] & 0xFFFFFFFFL);
            sortedX[i] = points[originalIdx][0];
            sortedY[i] = points[originalIdx][1];
        }

        int low = 0, high = 2 * side;
        int ans = 0;
        
        // Pre-calculate the depth for binary lifting
        int logK = 0;
        if (k > 1) {
            logK = 31 - Integer.numberOfLeadingZeros(k - 1);
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(mid, sortedX, sortedY, n, k, logK)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    /**
     * Maps a point on the square boundary to a 1D coordinate [0, 4*side).
     */
    private long getPerimeterPos(int x, int y, int side) {
        if (y == 0) return x;                         // Bottom side
        if (x == side) return (long) side + y;        // Right side
        if (y == side) return 2L * side + (side - x); // Top side
        return 3L * side + (side - y);                // Left side
    }

    private boolean check(int mid, int[] x, int[] y, int n, int k, int logK) {
        if (k == 1) return true;

        // Find nxt[i]: the first point j (cyclically) such that dist(i, j) >= mid
        int[] nxt = new int[n];
        int r = 0;
        for (int l = 0; l < n; l++) {
            if (r <= l) r = l + 1;
            while (r < l + n) {
                int ri = r % n;
                if (Math.abs(x[l] - x[ri]) + Math.abs(y[l] - y[ri]) >= mid) break;
                r++;
            }
            nxt[l] = r;
        }

        // Build Binary Lifting Table: jump[p][i] is the index reached after 2^p jumps
        int[][] jump = new int[logK + 1][n];
        for (int i = 0; i < n; i++) jump[0][i] = nxt[i];
        
        for (int p = 1; p <= logK; p++) {
            for (int i = 0; i < n; i++) {
                int nextIdx = jump[p - 1][i];
                if (nextIdx >= i + n) {
                    jump[p][i] = nextIdx; // Already exceeded a full lap
                } else {
                    // Logic: jumping from (base + lap*n) is same as jumping from base and adding lap*n
                    jump[p][i] = jump[p - 1][nextIdx % n] + (nextIdx / n) * n;
                }
            }
        }

        // Greedily check starting points. Checking up to nxt[0] covers all optimal sets.
        int limit = Math.min(n, nxt[0]);
        for (int i = 0; i < limit; i++) {
            int curr = i;
            int steps = k - 1;
            for (int p = logK; p >= 0; p--) {
                if (((steps >> p) & 1) == 1) {
                    curr = jump[p][curr % n] + (curr / n) * n;
                }
            }
            
            // Final check: the k-th point must be within one lap and 
            // the distance back to the 1st point must be >= mid.
            if (curr < i + n) {
                if (Math.abs(x[curr % n] - x[i]) + Math.abs(y[curr % n] - y[i]) >= mid) {
                    return true;
                }
            }
        }
        return false;
    }
}
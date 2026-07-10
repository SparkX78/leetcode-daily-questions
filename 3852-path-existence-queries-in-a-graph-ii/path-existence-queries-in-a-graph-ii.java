import java.util.Arrays;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Pair structure to store value and its original index
        int[][] sorted = new int[n][2];
        for (int i = 0; i < n; i++) {
            sorted[i][0] = nums[i];
            sorted[i][1] = i;
        }
        
        // Sort based on the values of elements
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Map original index to the sorted position
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[sorted[i][1]] = i;
        }
        
        // Determine the binary lifting limit
        int LOG = 0;
        while ((1 << LOG) <= n) {
            LOG++;
        }
        
        int[][] up = new int[LOG][n];
        int[] parent = new int[n];
        
        // Step 1: Find the greedy parent (furthest right reach) for each node
        for (int i = 0; i < n; i++) {
            int target = sorted[i][0] + maxDiff;
            // Binary search for the upper bound range
            int low = i, high = n - 1, ans = i;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (sorted[mid][0] <= target) {
                    ans = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            parent[i] = ans;
            up[0][i] = ans;
        }
        
        // Step 2: Build the binary lifting sparse table
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }
        
        // Step 3: Process queries
        int[] result = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int uOrig = queries[q][0];
            int vOrig = queries[q][1];
            
            int u = pos[uOrig];
            int v = pos[vOrig];
            
            // Ensure u is always the smaller sorted index
            if (u > v) {
                int temp = u;
                u = v;
                v = temp;
            }
            
            if (u == v) {
                result[q] = 0;
                continue;
            }
            
            // Check if v is even reachable from u by checking the ultimate ancestor
            int ultimateAncestor = u;
            for (int j = LOG - 1; j >= 0; j--) {
                ultimateAncestor = up[j][ultimateAncestor];
            }
            if (ultimateAncestor < v) {
                result[q] = -1;
                continue;
            }
            
            // If already within 1 step
            if (parent[u] >= v) {
                result[q] = 1;
                continue;
            }
            
            // Lift u to the furthest node whose parent still cannot reach v
            int steps = 0;
            for (int j = LOG - 1; j >= 0; j--) {
                if (parent[up[j][u]] < v) {
                    u = up[j][u];
                    steps += (1 << j);
                }
            }
            
            // It takes 1 step to move to parent[u], and 1 more step from there to reach v
            result[q] = steps + 2;
        }
        
        return result;
    }
}
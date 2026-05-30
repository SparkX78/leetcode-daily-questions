import java.util.*;

class Solution {
    // Segment Tree to find the maximum gap in a range
    int[] tree;
    int n;

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
    }

    private int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return 0;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        int p1 = query(2 * node, start, mid, l, r);
        int p2 = query(2 * node + 1, mid + 1, end, l, r);
        return Math.max(p1, p2);
    }

    public List<Boolean> getResults(int[][] queries) {
        // Determine the maximum possible coordinate boundary needed
        int maxCoord = 0;
        for (int[] q : queries) {
            maxCoord = Math.max(maxCoord, q[1]);
        }
        // Safely bound the size of our segment tree
        n = Math.max(maxCoord, 50000) + 1;
        tree = new int[4 * n];

        // TreeSet to track active obstacles
        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(n);
        
        // Initially, there's a huge gap from 0 to n
        update(1, 0, n - 1, 0, n);

        List<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {
            int type = q[0];
            if (type == 1) {
                int x = q[1];
                
                // Find adjacent obstacles
                int prev = obstacles.floor(x);
                int next = obstacles.ceiling(x);
                
                // Insert the new obstacle
                obstacles.add(x);
                
                // Update gaps in the segment tree
                update(1, 0, n - 1, prev, x - prev);
                update(1, 0, n - 1, x, next - x);
                
            } else {
                int x = q[1];
                int sz = q[2];
                
                // Find the obstacle immediately before or at x
                int prev = obstacles.floor(x);
                
                // 1. Max gap completely to the left of 'prev'
                int maxGapsBefore = query(1, 0, n - 1, 0, prev - 1);
                
                // 2. The residual gap between 'prev' and 'x'
                int lastGap = x - prev;
                
                int maxAvailableGap = Math.max(maxGapsBefore, lastGap);
                
                ans.add(maxAvailableGap >= sz);
            }
        }

        return ans;
    }
}
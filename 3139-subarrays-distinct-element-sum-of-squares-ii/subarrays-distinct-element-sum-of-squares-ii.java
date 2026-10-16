import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1000000007;

    // Segment tree arrays
    private long[] tree;
    private long[] lazy;

    private void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = l + (r - l) / 2;
            
            // Left child update
            long addL = (lazy[node] * (mid - l + 1)) % MOD;
            tree[2 * node] = (tree[2 * node] + addL) % MOD;
            lazy[2 * node] = (lazy[2 * node] + lazy[node]) % MOD;

            // Right child update
            long addR = (lazy[node] * (r - mid)) % MOD;
            tree[2 * node + 1] = (tree[2 * node + 1] + addR) % MOD;
            lazy[2 * node + 1] = (lazy[2 * node + 1] + lazy[node]) % MOD;

            lazy[node] = 0;
        }
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            tree[node] = (tree[node] + val * (r - l + 1)) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
            return;
        }
        push(node, l, r);
        int mid = l + (r - l) / 2;
        update(2 * node, l, mid, ql, qr, val);
        update(2 * node + 1, mid + 1, r, ql, qr, val);
        tree[node] = (tree[2 * node] + tree[2 * node + 1]) % MOD;
    }

    private long query(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return tree[node];
        push(node, l, r);
        int mid = l + (r - l) / 2;
        long leftSum = query(2 * node, l, mid, ql, qr);
        long rightSum = query(2 * node + 1, mid + 1, r, ql, qr);
        return (leftSum + rightSum) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        tree = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> lastOccurrence = new HashMap<>();
        long currentSumOfSquares = 0;
        long totalSum = 0;

        for (int j = 0; j < n; j++) {
            int prev = lastOccurrence.getOrDefault(nums[j], -1);
            lastOccurrence.put(nums[j], j);

            // Range [prev + 1, j] gets incremented by 2*sum + 1
            // We can query the current sum of linear distinct counts in range [prev + 1, j]
            long subSum = query(1, 0, n - 1, prev + 1, j);
            
            // New sum of squares = old_sum_squares + 2 * subSum + (j - prev)
            long increment = (2 * subSum + (j - prev)) % MOD;
            currentSumOfSquares = (currentSumOfSquares + increment) % MOD;

            totalSum = (totalSum + currentSumOfSquares) % MOD;

            // Update the segment tree by adding 1 to range [prev + 1, j] (representing the increase in distinct counts)
            update(1, 0, n - 1, prev + 1, j, 1);
        }

        return (int) totalSum;
    }
}
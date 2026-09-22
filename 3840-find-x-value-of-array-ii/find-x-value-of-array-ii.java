class Solution {
    static class Node {
        int prod;
        int[] count;

        Node(int k) {
            prod = 1;
            count = new int[k];
        }
    }

    private int kVal;
    private Node[] tree;
    private int n;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.kVal = k;
        this.n = nums.length;
        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int m = queries.length;
        int[] ans = new int[m];

        for (int i = 0; i < m; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int targetX = queries[i][3];

            // Step 1: Update nums[idx] to val
            update(1, 0, n - 1, idx, val);

            // Step 2: Query the range [start, n - 1]
            Node res = query(1, 0, n - 1, start, n - 1);

            // Step 3: Get count for x_i
            ans[i] = res.count[targetX];
        }

        return ans;
    }

    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node parent = new Node(kVal);
        parent.prod = (left.prod * right.prod) % kVal;

        // Combine prefix product remainder frequencies
        for (int x = 0; x < kVal; x++) {
            parent.count[x] += left.count[x];
            int rem = (left.prod * x) % kVal;
            parent.count[rem] += right.count[x];
        }

        return parent;
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            tree[node] = new Node(kVal);
            int rem = nums[l] % kVal;
            tree[node].prod = rem;
            tree[node].count[rem] = 1;
            return;
        }

        int mid = l + (r - l) / 2;
        build(2 * node, l, mid, nums);
        build(2 * node + 1, mid + 1, r, nums);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            int rem = val % kVal;
            tree[node].prod = rem;
            for (int i = 0; i < kVal; i++) {
                tree[node].count[i] = 0;
            }
            tree[node].count[rem] = 1;
            return;
        }

        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(2 * node, l, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, r, idx, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = l + (r - l) / 2;
        if (qr <= mid) {
            return query(2 * node, l, mid, ql, qr);
        }
        if (ql > mid) {
            return query(2 * node + 1, mid + 1, r, ql, qr);
        }

        Node leftRes = query(2 * node, l, mid, ql, qr);
        Node rightRes = query(2 * node + 1, mid + 1, r, ql, qr);

        return merge(leftRes, rightRes);
    }
}
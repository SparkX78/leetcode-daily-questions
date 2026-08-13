class Solution {
    static class Node {
        char leftChar, rightChar;
        int maxLen;
        int prefixLen;
        int suffixLen;
        int length;

        public Node(char c) {
            this.leftChar = c;
            this.rightChar = c;
            this.maxLen = 1;
            this.prefixLen = 1;
            this.suffixLen = 1;
            this.length = 1;
        }

        public Node() {}
    }

    private Node[] tree;
    private char[] chars;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        this.chars = s.toCharArray();
        this.tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            
            chars[idx] = c;
            update(1, 0, n - 1, idx, c);
            ans[i] = tree[1].maxLen;
        }

        return ans;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }

        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);

        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            tree[node] = new Node(c);
            return;
        }

        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, c);
        } else {
            update(2 * node + 1, mid + 1, end, idx, c);
        }

        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node merge(Node left, Node right) {
        Node res = new Node();
        res.length = left.length + right.length;
        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        // Base prefix and suffix lengths
        res.prefixLen = left.prefixLen;
        if (left.prefixLen == left.length && left.rightChar == right.leftChar) {
            res.prefixLen = left.length + right.prefixLen;
        }

        res.suffixLen = right.suffixLen;
        if (right.suffixLen == right.length && left.rightChar == right.leftChar) {
            res.suffixLen = right.length + left.suffixLen;
        }

        // Maximum repeat length in this combined range
        res.maxLen = Math.max(left.maxLen, right.maxLen);
        if (left.rightChar == right.leftChar) {
            res.maxLen = Math.max(res.maxLen, left.suffixLen + right.prefixLen);
        }

        return res;
    }
}
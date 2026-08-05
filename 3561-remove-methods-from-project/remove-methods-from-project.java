import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Build adjacency list for graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            adj.get(inv[0]).add(inv[1]);
        }

        // Step 1: Find all suspicious nodes starting from k
        boolean[] isSuspicious = new boolean[n];
        dfs(k, adj, isSuspicious);

        // Step 2: Check if any non-suspicious node invokes a suspicious node
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            if (!isSuspicious[u] && isSuspicious[v]) {
                // Cannot remove suspicious nodes; return all nodes
                List<Integer> all = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    all.add(i);
                }
                return all;
            }
        }

        // Step 3: Collect and return only the remaining non-suspicious nodes
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isSuspicious[i]) {
                result.add(i);
            }
        }
        return result;
    }

    private void dfs(int u, List<List<Integer>> adj, boolean[] isSuspicious) {
        isSuspicious[u] = true;
        for (int v : adj.get(u)) {
            if (!isSuspicious[v]) {
                dfs(v, adj, isSuspicious);
            }
        }
    }
}
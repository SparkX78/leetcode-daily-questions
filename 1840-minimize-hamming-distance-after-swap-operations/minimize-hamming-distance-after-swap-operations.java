import java.util.*;

class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;

        // Step 1: Build graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int[] swap : allowedSwaps) {
            graph.get(swap[0]).add(swap[1]);
            graph.get(swap[1]).add(swap[0]);
        }

        boolean[] visited = new boolean[n];
        int result = 0;

        // Step 2: Traverse components
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> comp = new ArrayList<>();
                dfs(i, graph, visited, comp);

                // Step 3: Count source values
                Map<Integer, Integer> count = new HashMap<>();
                for (int idx : comp) {
                    count.put(source[idx], count.getOrDefault(source[idx], 0) + 1);
                }

                // Step 4: Match with target
                for (int idx : comp) {
                    if (count.getOrDefault(target[idx], 0) > 0) {
                        count.put(target[idx], count.get(target[idx]) - 1);
                    } else {
                        result++;
                    }
                }
            }
        }

        return result;
    }

    private void dfs(int node, List<List<Integer>> graph, boolean[] visited, List<Integer> comp) {
        visited[node] = true;
        comp.add(node);

        for (int nei : graph.get(node)) {
            if (!visited[nei]) {
                dfs(nei, graph, visited, comp);
            }
        }
    }
}
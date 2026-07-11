import java.util.*;

class Solution {

    public int countCompleteComponents(int n, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                List<Integer> component = new ArrayList<>();

                dfs(i, graph, visited, component);

                int vertices = component.size();

                int degreeSum = 0;

                for (int node : component) {
                    degreeSum += graph.get(node).size();
                }

                int edgeCount = degreeSum / 2;

                if (edgeCount == vertices * (vertices - 1) / 2) {
                    answer++;
                }
            }
        }

        return answer;
    }

    void dfs(int node, List<List<Integer>> graph,
             boolean[] visited, List<Integer> component) {

        visited[node] = true;
        component.add(node);

        for (int neighbour : graph.get(node)) {
            if (!visited[neighbour]) {
                dfs(neighbour, graph, visited, component);
            }
        }
    }
}
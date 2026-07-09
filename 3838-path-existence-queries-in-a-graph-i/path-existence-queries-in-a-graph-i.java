class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // comp[i] will store the component ID of node i
        int[] comp = new int[n];
        int componentId = 0;
        comp[0] = componentId;
        
        // Group nodes into continuous connected components
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                componentId++;
            }
            comp[i] = componentId;
        }
        
        // Process each query in O(1) time
        boolean[] answer = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            answer[i] = (comp[u] == comp[v]);
        }
        
        return answer;
    }
}
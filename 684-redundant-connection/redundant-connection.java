class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        for(int i = 1; i <= n; i++){
            adj.put(i, new ArrayList<>());
        }
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            boolean[] visited = new boolean[edges.length+1];
            if(dfs(adj, u, v, visited)){
                return edge;

            }
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return new int[0];
    }
    public boolean dfs(HashMap<Integer, List<Integer>> adj, int u, int target, boolean[] visited){
        if(u == target){
            return true;
        }
        visited[u] = true;
        for(int neigh : adj.get(u)){
            if(!visited[neigh]){
                
                if(dfs(adj, neigh, target, visited)){
                    return true;
                }
            }
        }
        return false;
    }
}
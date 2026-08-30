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
            if(bfs(adj, u, v, n)){
                return edge;

            }
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return new int[0];
    }
    public boolean bfs(HashMap<Integer, List<Integer>> adj, int u, int target, int n){
        
        boolean[] visited = new boolean[n+1];
        visited[u] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(u);
        while(!queue.isEmpty()){
            int curr = queue.poll();
            if(curr == target){
                return true;
            }
            for(int neigh : adj.get(curr)){
                if(!visited[neigh] ){
                    visited[neigh] = true;
                    queue.offer(neigh);
                }
            }
        }
        return false;
    }
}
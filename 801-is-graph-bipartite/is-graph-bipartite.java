class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);
        for(int i = 0; i < n; i++){
            if(color[i] == -1){
                if(!validateBFS(graph, i, color)){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean validateBFS(int[][] graph, int node, int[] color){
        Queue<Integer> queue = new LinkedList<>();
       
        queue.add(node);
        color[node] = 0;
        while(!queue.isEmpty()){
            int curr = queue.poll();
            for(int v : graph[curr]){
               
                if( color[v] == -1){
                    color[v] = 1 ^ color[curr];
                   
                    queue.offer(v);
                }
                else if(color[v] == color[curr]){
                    return false;
                }
            }

        }
        return true;
    }
}
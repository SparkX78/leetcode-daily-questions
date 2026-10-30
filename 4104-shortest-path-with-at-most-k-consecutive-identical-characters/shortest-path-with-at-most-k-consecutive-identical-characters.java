class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for(int i = 0;i < n ;i++){
            graph.add(new ArrayList<>());
        }
        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            int w = e[2];
            graph.get(u).add(new int[]{v,w});

        }
        int[][] distance = new int[n][k+1];
        for(int i = 0; i < n; i++){
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        distance[0][1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, 1});
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int dist = curr[0];
            int node = curr[1];
            int count = curr[2];
            if(dist > distance[node][count]){
                continue;
            }
            if(node == n-1){
                return dist;
            }
            for(int[] edge : graph.get(node)){
                int v = edge[0];
                int w = edge[1];
                int new_count = labels.charAt(node) == labels.charAt(v) ? count + 1: 1;
                if (new_count > k) {
                    continue;
                }
                if(dist + w < distance[v][new_count]){
                    distance[v][new_count] = dist + w;
                    pq.offer(new int[]{distance[v][new_count], v, new_count});
                }
                

                
            }
        }
        return -1;


    }
}
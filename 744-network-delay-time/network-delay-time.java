class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] edge : times){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(v);
            temp.add(w);

            adj.get(u).add(temp);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[0], b[0]));
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        pq.offer(new int[]{0, k});

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int D = curr[0];
            int node = curr[1];

            if(D > dist[node]){
                continue;
            }

            for(ArrayList<Integer> neigh : adj.get(node)){
                int next_node = neigh.get(0);
                int weight = neigh.get(1);

                if(weight + D < dist[next_node]){
                    dist[next_node] = weight + D;
                    pq.offer(new int[]{dist[next_node] ,next_node});
                }
            }

        }
        int maxTime = 0;
        for(int i = 1; i <=n; i++){
            if(dist[i] == Integer.MAX_VALUE){
                return -1;
            }

            maxTime = Math.max(maxTime, dist[i]);
        }
        return maxTime;
    }
}
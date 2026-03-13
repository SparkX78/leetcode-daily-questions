class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] t: times){
            graph[t[0]].add(new int[]{t[1], t[2]});
        }
        int[] dist = new int[n+1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-a[1]);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        pq.add(new int[] {k,0});
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int node = curr[0];
            int d = curr[1];
            for(int[] neighbour : graph[node]){
                int next = neighbour[0];
                int weight = neighbour[1];
                if(d + weight < dist[next]){
                    dist[next] = d+weight;
                    pq.add(new int[]{next, dist[next]});
                }
            }
        }
        int ans = 0;
        for(int i = 1; i <=n; i++){
            if(dist[i] == Integer.MAX_VALUE){
                return -1;
            }
            ans = Math.max(ans, dist[i]);
        }
        return ans;
    }
}
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[numCourses];
        for(int[] vec : prerequisites){
            int a = vec[0];
            int b = vec[1];

            //b-->a
            indegree[a]++;
            adj.computeIfAbsent(b, k-> new ArrayList<>()).add(a);
        }
        return topoLogical(adj, indegree, numCourses);

    }
    public int[] topoLogical(HashMap<Integer, List<Integer>> adj, int[] indegree, int n){
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        int[] result = new int[n];
        int k = 0;
        for(int i = 0; i < n; i++){
            if(indegree[i] == 0 && k < n){
                result[k++] = i;
                count++;
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int u = queue.poll();
            for(int v : adj.getOrDefault(u, new ArrayList<>())){
                indegree[v]--;
                if(indegree[v] == 0 && k < n){
                    result[k++] = v;
                    count++;
                    queue.offer(v);
                }
                
            }
            
        }
        if(count == n){
            return result;
        }
        return new int[]{};
    }
}
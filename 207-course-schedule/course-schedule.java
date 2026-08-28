class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int[] indegree = new int[numCourses];
        for(int[] vec : prerequisites){
            int a =vec[0];
            int b = vec[1];

            //b-->a
            map.computeIfAbsent(b, k->new ArrayList<>()).add(a);
            indegree[a]++;
        }
        return topoLogical(map, indegree, numCourses);
    }
    public boolean topoLogical(HashMap<Integer, List<Integer>> map, int[] indegree, int n){
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++){
            if(indegree[i] == 0){
                count++;
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int u = queue.poll();
            for(int v : map.getOrDefault(u, new ArrayList<>()) ){
                indegree[v]--;
                if(indegree[v] == 0){
                    count++;
                    queue.offer(v);
                }
            }
        }
        if(count == n){
            return true;
        }
        return false;
    }
}
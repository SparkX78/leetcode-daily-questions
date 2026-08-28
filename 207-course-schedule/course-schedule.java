class Solution {
    public boolean isCycleDFS(HashMap<Integer, List<Integer>> adj, int u, boolean[] visited, boolean[] inRecursion){
        visited[u] = true;
        inRecursion[u] = true;

        for(int v : adj.getOrDefault(u, new ArrayList<>())){
            if(!visited[v] && isCycleDFS(adj, v, visited, inRecursion)){
                return true;
            }
            if(inRecursion[v] == true){
                return true;
            }
            

        }
        inRecursion[u] = false;
        return false;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        boolean[] visited = new boolean[numCourses];
        boolean[] inRecursion = new boolean[numCourses];
        Arrays.fill(visited, false);
        Arrays.fill(inRecursion, false);
        for(int[] vec : prerequisites){
            int a = vec[0];
            int b = vec[1];

            //b ---> a
            adj.computeIfAbsent(b,k-> new ArrayList()).add(a);

        }
        for(int i = 0; i < numCourses; i++){
            if(!visited[i] && isCycleDFS(adj, i, visited, inRecursion)){
                return false;
            }
            
        }
        return true;
    }
}
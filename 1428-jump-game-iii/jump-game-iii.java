class Solution {
    boolean[] visited;
    public boolean canReach(int[] arr, int start) {
        visited = new boolean[arr.length];
        return dfs(arr, start);
    }
    public boolean dfs(int[] arr, int index){
        if(index < 0 || index >= arr.length || visited[index]){
            return false;
        }
        if(arr[index] == 0){
            return true;
        }
        visited[index] = true;
        return dfs(arr, index + arr[index]) || dfs(arr, index - arr[index]);
    }
}
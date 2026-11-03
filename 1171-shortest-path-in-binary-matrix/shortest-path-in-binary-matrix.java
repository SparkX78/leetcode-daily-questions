class Solution {
    int[][] dirs = {{1,1}, {1,0}, {1,-1}, {0,1}, {0,-1}, {-1,0}, {-1,-1}, {-1,1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(m == 0 || n == 0 || grid[0][0] != 0){
            return -1;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        int level = 0;
        grid[0][0] = 1;
        while(!queue.isEmpty()){
            int N = queue.size();
            while(N-- > 0){
                int[] curr = queue.poll();
                int row = curr[0];
                int col = curr[1];
                if(row == m-1 && col == n-1){
                    return level + 1;
                }
                for(int[] d : dirs){
                    int new_row = d[0] + row;
                    int new_col = d[1] + col;
                    if(new_row >= 0 && new_col >= 0 && new_row < m && new_col < n && grid[new_row][new_col] != 1){
                        queue.offer(new int[]{new_row, new_col});
                        grid[new_row][new_col] = 1;
                    }
                }
                
            }
            level++;
        }
        return -1;
        
    }
}
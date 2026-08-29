class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 2){
                    queue.offer(new int[]{i,j});
                }
                else if(grid[i][j] == 1){
                    fresh++;
                }
            }
        }
        if(fresh == 0){
            return 0;
        }
        int minutes = 0;
        while(!queue.isEmpty()){
            

            int N = queue.size();
            while(N-- > 0){
                int[] curr = queue.poll();
                int first = curr[0];
                int second = curr[1];
                for(int[] d : dir){
                    
                    int new_row = first + d[0];
                    int new_col = second + d[1];
                    if(new_row >= 0 && new_row < m && new_col >= 0 && new_col < n && grid[new_row][new_col] == 1){
                        grid[new_row][new_col] = 2;
                        queue.offer(new int[]{new_row, new_col});
                        fresh--;
                    }


                }
            }
            minutes++;
        }
        return (fresh == 0)? minutes-1 : -1;
    }
}
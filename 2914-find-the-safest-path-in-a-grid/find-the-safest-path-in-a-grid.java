class Solution {
    public int maximumSafenessFactor(List<List<Integer>> mat) {
        int[][] dir = {{0,-1}, {0, 1}, {-1, 0}, {1,0}};
        int n = mat.size();
        Queue<int[]> q = new ArrayDeque<>();
        int[][] grid = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int value = mat.get(i).get(j);
                if(value == 1){
                    q.offer(new int[]{i,j});
                }
                grid[i][j] = value;
            }
        }
        while(!q.isEmpty()){
            int[] curr = q.remove();
            int x = curr[0];
            int y = curr[1];
            for(int i = 0; i < 4; i++){
                int r = x + dir[i][0];
                int c = y + dir[i][1];

                if(r < 0 || r >= n || c < 0 || c >= n || grid[r][c] > 0){
                    continue;
                }
                grid[r][c] = grid[x][y] + 1;
                q.offer(new int[]{r,c});
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
    (a, b) -> b[0] - a[0]
);
        pq.offer(new int[]{grid[0][0], 0, 0});
        grid[0][0] = -1;
        while(!pq.isEmpty()){
            int cur[] = pq.remove();
            int sfac = cur[0];
            int x = cur[1];
            int y = cur[2];
            if(x == n-1 && y == n-1){
                return sfac -1;
            }
            for(int i = 0; i < 4; i++){
                int r = x + dir[i][0];
                int c = y + dir[i][1];


                if(r < 0 || r >= n || c < 0 || c >= n || grid[r][c] < 0){
                    continue;
                }

                int min = Math.min(sfac, grid[r][c]);
                pq.offer(new int[]{min, r, c});
                grid[r][c] = -1;
            }

        }
        return 0;
    }
}
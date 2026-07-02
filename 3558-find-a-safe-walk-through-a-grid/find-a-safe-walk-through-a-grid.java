class Solution {
    public boolean findSafeWalk(List<List<Integer>> mat, int health) {
        int n = mat.get(0).size();
        int m = mat.size();
        int[][] dir = {{0,-1}, {0, 1}, {-1, 0}, {1, 0}};
        int[][] grid = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                grid[i][j] = mat.get(i).get(j);
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{grid[0][0], 0, 0});
        grid[0][0] = Integer.MAX_VALUE;

        while(!pq.isEmpty()){
            int[] curr = pq.remove();
            int cost = curr[0];
            int x = curr[1];
            int y = curr[2];
            if(x == m-1 && y == n-1){
                return true;
            }
            for(int i= 0; i < 4; i++){
                int r = x + dir[i][0];
                int c = y + dir[i][1];
                if(r < 0 || r>=m || c < 0 || c >= n || health-grid[r][c] <= 0){
                    continue;
                }
                int nextCost = cost + grid[r][c];
                if(nextCost < health){
                    pq.offer(new int[]{nextCost, r, c});
                }
                
                grid[r][c] = Integer.MAX_VALUE;
            }

        }
        return false;

    }
}
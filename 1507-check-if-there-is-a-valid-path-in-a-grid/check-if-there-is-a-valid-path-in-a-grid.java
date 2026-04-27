

class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // directions: up, right, down, left
        int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

        // mapping for each type
        int[][] typeDirs = {
            {},
            {3,1},    // type 1
            {0,2},    // type 2
            {3,2},    // type 3
            {1,2},    // type 4
            {3,0},    // type 5
            {1,0}     // type 6
        };

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];

            if (r == m - 1 && c == n - 1) return true;

            for (int d : typeDirs[grid[r][c]]) {
                int nr = r + dirs[d][0];
                int nc = c + dirs[d][1];

                if (nr < 0 || nc < 0 || nr >= m || nc >= n || visited[nr][nc])
                    continue;

                // check reverse connection
                int rev = (d + 2) % 4;
                for (int nd : typeDirs[grid[nr][nc]]) {
                    if (nd == rev) {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                        break;
                    }
                }
            }
        }

        return false;
    }
}

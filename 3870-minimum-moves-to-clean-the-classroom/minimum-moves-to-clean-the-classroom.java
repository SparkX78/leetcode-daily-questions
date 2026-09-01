import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startR = -1, startC = -1;
        int litterCount = 0;
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        // Identify starting position and index all litter cells
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litterId[r][c] = litterCount++;
                }
            }
        }

        int targetMask = (1 << litterCount) - 1;
        
        // If there's no litter to collect, return 0 moves
        if (targetMask == 0) {
            return 0;
        }

        // bestEnergy[r][c][mask] stores the max remaining energy for that state
        int[][][] bestEnergy = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }

        // Queue holds: {r, c, mask, currentEnergy}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC, 0, energy});
        bestEnergy[startR][startC][0] = energy;

        int moves = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int mask = curr[2];
                int e = curr[3];

                if (mask == targetMask) {
                    return moves;
                }

                if (e == 0) {
                    continue; // Cannot move further without energy
                }

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    
                    char cell = classroom[nr].charAt(nc);
                    if (cell == 'X') continue;

                    int nextMask = mask;
                    if (cell == 'L') {
                        nextMask |= (1 << litterId[nr][nc]);
                    }

                    int nextEnergy = e - 1;
                    if (cell == 'R') {
                        nextEnergy = energy; // Reset to max capacity
                    }

                    if (nextEnergy > bestEnergy[nr][nc][nextMask]) {
                        bestEnergy[nr][nc][nextMask] = nextEnergy;
                        queue.offer(new int[]{nr, nc, nextMask, nextEnergy});
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}
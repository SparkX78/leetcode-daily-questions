import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> la = new ArrayList<>();
        List<int[]> lb = new ArrayList<>();

        // Collect positions of 1s in both matrices
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) la.add(new int[]{r, c});
                if (img2[r][c] == 1) lb.add(new int[]{r, c});
            }
        }

        // Map offset vector -> count of overlaps
        Map<String, Integer> countMap = new HashMap<>();
        int maxOverlap = 0;

        for (int[] p1 : la) {
            for (int[] p2 : lb) {
                int dr = p2[0] - p1[0];
                int dc = p2[1] - p1[1];
                String key = dr + "," + dc;

                int currentCount = countMap.getOrDefault(key, 0) + 1;
                countMap.put(key, currentCount);
                maxOverlap = Math.max(maxOverlap, currentCount);
            }
        }

        return maxOverlap;
    }
}
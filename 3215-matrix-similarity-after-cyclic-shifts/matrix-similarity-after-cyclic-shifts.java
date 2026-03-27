class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        k = k % n; // critical optimization

        for (int i = 0; i < m; i++) {

            int shift;
            if (i % 2 == 0) {
                // even row → left shift
                shift = k;
            } else {
                // odd row → right shift
                shift = (n - k) % n;
            }

            for (int j = 0; j < n; j++) {
                if (mat[i][j] != mat[i][(j + shift) % n]) {
                    return false;
                }
            }
        }

        return true;
    }
}
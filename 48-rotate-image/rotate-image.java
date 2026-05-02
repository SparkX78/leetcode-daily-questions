class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for(int i = 0; i < n; i++){
            reverse(matrix[i]);
        }
    }

    private void reverse(int[] row){
        int start = 0, end = row.length - 1;
        while(start < end){
            int temp = row[start];
            row[start] = row[end];
            row[end] = temp;
            start++;
            end--;
        }
    }
}
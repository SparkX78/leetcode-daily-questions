class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int r1 = 0;
        int c1 = 0;
        int r2 = m-1;
        int c2 = n-1;
        
        while(r1 < r2 && c1 < c2){
            int total = 2*(r2 - r1)+ 2*(c2 - c1);
            int shift = k % total;
            int[] arr = new int[total];
            int idx = 0;
            for(int i = c1; i <c2; i++){
                arr[idx++] = grid[r1][i];
            }
            for(int i = r1; i < r2; i++){
                arr[idx++] = grid[i][c2];
            }
            for(int i = c2; i > c1; i--){
                arr[idx++] = grid[r2][i];
            }
            for(int i = r2; i > r1; i--){
                arr[idx++] = grid[i][c1];
            }
            reverse(arr, 0, shift-1);
            reverse(arr, shift, total-1);
            reverse(arr, 0, total-1);
            int j = 0;
            for(int i = c1; i < c2; i++){
                grid[r1][i] = arr[j++];
            }
            for(int i = r1; i < r2; i++){
                grid[i][c2] = arr[j++];
            }
            for(int i = c2; i > c1; i--){
                grid[r2][i]= arr[j++];
            }
            for(int i = r2; i > r1; i--){
                grid[i][c1] = arr[j++];
            }
            r1++;
            r2--;
            c1++;
            c2--;
            

        }
        return grid;
    }
    void reverse(int[] arr, int start, int end){
        while(start < end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
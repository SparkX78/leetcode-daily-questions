class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
      int n = mat.length;
      
      for(int c = 1; c<= 4; c++){
        boolean equal = true;
        for(int i = 0; i < n; i++){
            for(int j = 0; j <n; j++){
                if(mat[i][j] != target[i][j]){
                    equal = false;
                    break;
                }
            }
            if(!equal){
                break;
            }
        }
        if(equal){
            return true;
        }
        rotate(mat);

      }
      return false;  
    }
    private void rotate(int[][] mat){
        //Transpose
        for(int i = 0; i < mat.length; i++){
            for(int j = i; j < mat.length; j++){
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
        for(int i = 0; i < mat.length; i++){
            reverse(mat[i]);
        }
    }
    private void reverse(int[] row){
        int left = 0;
        int right = row.length-1;
        while(left < right){
            int temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left++;
            right--;
        }
    }
}
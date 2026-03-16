import java.util.*;

class Solution {
    
    public int[] getBiggestThree(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;
        
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                
                // size 0 rhombus (single cell)
                set.add(grid[i][j]);
                
                for(int k = 1; k < Math.min(m, n); k++){
                    
                    int r1 = i + k;
                    int r2 = i + 2*k;
                    int c1 = j - k;
                    int c2 = j + k;
                    
                    if(r2 >= m || c1 < 0 || c2 >= n)
                        break;
                    
                    int sum = 0;
                    
                    int x = i;
                    int y = j;
                    
                    // top -> left
                    for(int t = 0; t < k; t++)
                        sum += grid[x + t][y - t];
                    
                    // left -> bottom
                    for(int t = 0; t < k; t++)
                        sum += grid[x + k + t][y - k + t];
                    
                    // bottom -> right
                    for(int t = 0; t < k; t++)
                        sum += grid[x + 2*k - t][y + t];
                    
                    // right -> top
                    for(int t = 0; t < k; t++)
                        sum += grid[x + k - t][y + k - t];
                    
                    set.add(sum);
                }
            }
        }
        
        int size = Math.min(3, set.size());
        int[] res = new int[size];
        
        int idx = 0;
        for(int val : set){
            if(idx == size) break;
            res[idx++] = val;
        }
        
        return res;
    }
}
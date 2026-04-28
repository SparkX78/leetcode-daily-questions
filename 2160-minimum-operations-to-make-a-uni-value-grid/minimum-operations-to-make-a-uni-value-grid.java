import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> list = new ArrayList<>();
        
        for (int[] row : grid) {
            for (int num : row) {
                list.add(num);
            }
        }
        
        int base = list.get(0);
        
        // Step 1: feasibility check
        for (int num : list) {
            if ((num - base) % x != 0) {
                return -1;
            }
        }
        
        // Step 2: normalize
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) / x);
        }
        
        // Step 3: sort
        Collections.sort(list);
        
        int median = list.get(list.size() / 2);
        
        // Step 4: compute operations
        int operations = 0;
        for (int num : list) {
            operations += Math.abs(num - median);
        }
        
        return operations;
    }
}
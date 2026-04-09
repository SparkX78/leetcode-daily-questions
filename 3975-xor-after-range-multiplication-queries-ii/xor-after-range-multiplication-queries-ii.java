import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            res[i] = nums[i];
        }

        // B is the threshold for k. sqrt(10^5) is ~316. 
        // 64-100 is often a good balance for Java's overhead.
        int B = 80; 
        
        // Group small-k queries to process them efficiently per k value
        List<int[]>[] smallKGroups = new List[B + 1];
        
        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            if (v == 1) continue; // Multiplying by 1 does nothing

            if (k > B) {
                // Case 1: Large k - Direct update
                for (int i = l; i <= r; i += k) {
                    res[i] = (res[i] * v) % MOD;
                }
            } else {
                // Case 2: Small k - Store for batch processing
                if (smallKGroups[k] == null) smallKGroups[k] = new ArrayList<>();
                smallKGroups[k].add(q);
            }
        }

        // Process each small k value
        for (int k = 1; k <= B; k++) {
            if (smallKGroups[k] == null) continue;
            
            // Difference array for multiplicative updates
            long[] diff = new long[n + k + 1];
            Arrays.fill(diff, 1L);
            
            for (int[] q : smallKGroups[k]) {
                int l = q[0], r = q[1], v = q[3];
                int last = l + ((r - l) / k) * k;
                
                diff[l] = (diff[l] * v) % MOD;
                long invV = power(v, MOD - 2); // Modular inverse via Fermat's Little Theorem
                if (last + k < diff.length) {
                    diff[last + k] = (diff[last + k] * invV) % MOD;
                }
            }
            
            // Apply the prefix products for each residue class modulo k
            for (int r = 0; r < k; r++) {
                long currMultiplier = 1;
                for (int i = r; i < n; i += k) {
                    currMultiplier = (currMultiplier * diff[i]) % MOD;
                    if (currMultiplier != 1) {
                        res[i] = (res[i] * currMultiplier) % MOD;
                    }
                }
            }
        }

        // Final result is the bitwise XOR of all elements
        int xorSum = 0;
        for (long val : res) {
            xorSum ^= (int) val;
        }
        return xorSum;
    }

    // Binary exponentiation for modular inverse
    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }
}
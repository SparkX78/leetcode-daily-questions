import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        
        // Store interval info along with original index: [l, r, weight, original_index]
        int[][] intervalsWithIdx = new int[n][4];
        for (int i = 0; i < n; i++) {
            intervalsWithIdx[i][0] = intervals.get(i).get(0);
            intervalsWithIdx[i][1] = intervals.get(i).get(1);
            intervalsWithIdx[i][2] = intervals.get(i).get(2);
            intervalsWithIdx[i][3] = i;
        }

        // Sort intervals by right boundary `r` ascending
        Arrays.sort(intervalsWithIdx, (a, b) -> Integer.compare(a[1], b[1]));

        // Precalculate predecessor index `p[i]` for each interval using binary search:
        // largest index `j` such that intervalsWithIdx[j][1] < intervalsWithIdx[i][0]
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            int target = intervalsWithIdx[i][0];
            int low = 0, high = i - 1, best = -1;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (intervalsWithIdx[mid][1] < target) {
                    best = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            p[i] = best;
        }

        // DP State: dp[k][i] = best choice choosing k non-overlapping intervals from prefix 0..i
        // Score representation for comparison
        class State {
            long weightSum;
            List<Integer> indices; // Sorted original indices selected

            State(long weightSum, List<Integer> indices) {
                this.weightSum = weightSum;
                this.indices = indices;
            }
        }

        // Compare two choices: higher weight sum first, then lexicographically smaller index array
        Comparator<State> comp = (a, b) -> {
            if (a.weightSum != b.weightSum) {
                return Long.compare(b.weightSum, a.weightSum); // higher sum is better
            }
            // Lexicographically smaller indices preferred
            int sz = Math.min(a.indices.size(), b.indices.size());
            for (int i = 0; i < sz; i++) {
                int cmp = Integer.compare(a.indices.get(i), b.indices.get(i));
                if (cmp != 0) return cmp;
            }
            return Integer.compare(a.indices.size(), b.indices.size());
        };

        // dp[k][i] stores the best choice for taking k intervals in prefix i
        State[][] dp = new State[5][n];

        for (int k = 1; k <= 4; k++) {
            for (int i = 0; i < n; i++) {
                // Option 1: Skip interval i
                State best = (i > 0) ? dp[k][i - 1] : new State(0, new ArrayList<>());

                // Option 2: Pick interval i
                int prevIdx = p[i];
                long prevWeight = 0;
                List<Integer> prevList = new ArrayList<>();
                if (k > 1 && prevIdx != -1) {
                    prevWeight = dp[k - 1][prevIdx].weightSum;
                    prevList = dp[k - 1][prevIdx].indices;
                }

                if (k == 1 || prevIdx != -1 || k - 1 == 0) {
                    long newWeight = prevWeight + intervalsWithIdx[i][2];
                    List<Integer> newList = new ArrayList<>(prevList);
                    newList.add(intervalsWithIdx[i][3]);
                    Collections.sort(newList); // Ensure indices remain sorted for lexicographical comparison

                    State optionTake = new State(newWeight, newList);
                    if (comp.compare(optionTake, best) < 0) {
                        best = optionTake;
                    }
                }

                dp[k][i] = best;
            }
        }

        // Find best choice across choosing at most 4 intervals
        State overallBest = new State(0, new ArrayList<>());
        for (int k = 1; k <= 4; k++) {
            if (comp.compare(dp[k][n - 1], overallBest) < 0) {
                overallBest = dp[k][n - 1];
            }
        }

        int[] result = new int[overallBest.indices.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = overallBest.indices.get(i);
        }
        return result;
    }
}
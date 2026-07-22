import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    // Helper class to store information about contiguous segments of '0's
    private static class Group {

        int start;
        int length;

        Group(int start, int length) {
            this.start = start;
            this.length = length;
        }
    }

    // Sparse Table for O(1) Range Maximum Queries
    private static class SparseTable {

        private final int[][] st;

        public SparseTable(int[] nums) {
            int n = nums.length;
            int maxLog = n > 0 ? 32 - Integer.numberOfLeadingZeros(n) : 0;
            this.st = new int[maxLog + 1][n];

            if (n > 0) {
                System.arraycopy(nums, 0, st[0], 0, n);
            }

            for (int i = 1; i <= maxLog; i++) {
                for (int j = 0; j + (1 << i) <= n; j++) {
                    st[i][j] = Math.max(
                        st[i - 1][j],
                        st[i - 1][j + (1 << (i - 1))]
                    );
                }
            }
        }

        public int query(int l, int r) {
            if (l > r) return 0;
            int k = 31 - Integer.numberOfLeadingZeros(r - l + 1);
            return Math.max(st[k][l], st[k][r - (1 << k) + 1]);
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(
        String s,
        int[][] queries
    ) {
        int n = s.length();

        // Count total '1's in the initial string
        int ones = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') ones++;
        }

        // Group '0's into contiguous blocks
        List<Group> zeroGroups = new ArrayList<>();
        int[] zeroGroupIndex = new int[n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    zeroGroups.get(zeroGroups.size() - 1).length++;
                } else {
                    zeroGroups.add(new Group(i, 1));
                }
                zeroGroupIndex[i] = zeroGroups.size() - 1;
            } else {
                zeroGroupIndex[i] = -1;
            }
        }

        // If there are no '0's or 1 or fewer zero groups, no valid trades can change anything
        if (zeroGroups.isEmpty()) {
            return Collections.nCopies(queries.length, ones);
        }

        // Array storing sum of lengths of adjacent zero groups
        int numZeroGroups = zeroGroups.size();
        int[] zeroMergeLengths = new int[Math.max(0, numZeroGroups - 1)];
        for (int i = 0; i < numZeroGroups - 1; i++) {
            zeroMergeLengths[i] =
                zeroGroups.get(i).length + zeroGroups.get(i + 1).length;
        }

        SparseTable st = new SparseTable(zeroMergeLengths);
        List<Integer> ans = new ArrayList<>(queries.length);

        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];

            // Compute available left/right zero block lengths within range [l, r]
            int left = zeroGroupIndex[l] == -1
                ? 0
                : zeroGroups.get(zeroGroupIndex[l]).length -
                (l - zeroGroups.get(zeroGroupIndex[l]).start);

            int right = zeroGroupIndex[r] == -1
                ? 0
                : r - zeroGroups.get(zeroGroupIndex[r]).start + 1;

            int startAdjacentIdx = s.charAt(l) == '0'
                ? zeroGroupIndex[l] + 1
                : getFirstZeroGroupAfter(l, zeroGroupIndex, zeroGroups);
            int endAdjacentIdx = (
                    s.charAt(r) == '0'
                        ? zeroGroupIndex[r] - 1
                        : getLastZeroGroupBefore(
                            r,
                            zeroGroupIndex,
                            zeroGroups
                        )
                ) -
                1;

            int activeSections = ones;

            // Case 1: l and r are inside adjacent zero groups
            if (
                s.charAt(l) == '0' &&
                s.charAt(r) == '0' &&
                zeroGroupIndex[l] + 1 == zeroGroupIndex[r]
            ) {
                activeSections = Math.max(activeSections, ones + left + right);
            } else if (startAdjacentIdx <= endAdjacentIdx) {
                // Case 2: Full pair of zero groups strictly inside range [l, r]
                activeSections = Math.max(
                    activeSections,
                    ones + st.query(startAdjacentIdx, endAdjacentIdx)
                );
            }

            // Case 3: Partial left zero group + full next zero group
            if (
                s.charAt(l) == '0' &&
                zeroGroupIndex[l] + 1 < numZeroGroups &&
                zeroGroups.get(zeroGroupIndex[l] + 1).start +
                    zeroGroups.get(zeroGroupIndex[l] + 1).length -
                    1 <=
                r
            ) {
                activeSections = Math.max(
                    activeSections,
                    ones +
                    left +
                    zeroGroups.get(zeroGroupIndex[l] + 1).length
                );
            }

            // Case 4: Full previous zero group + partial right zero group
            if (
                s.charAt(r) == '0' &&
                zeroGroupIndex[r] - 1 >= 0 &&
                zeroGroups.get(zeroGroupIndex[r] - 1).start >= l
            ) {
                activeSections = Math.max(
                    activeSections,
                    ones +
                    right +
                    zeroGroups.get(zeroGroupIndex[r] - 1).length
                );
            }

            ans.add(activeSections);
        }

        return ans;
    }

    private int getFirstZeroGroupAfter(
        int idx,
        int[] zeroGroupIndex,
        List<Group> zeroGroups
    ) {
        int next = idx;
        while (
            next < zeroGroupIndex.length && zeroGroupIndex[next] == -1
        ) next++;
        return next < zeroGroupIndex.length ? zeroGroupIndex[next] : 10000000;
    }

    private int getLastZeroGroupBefore(
        int idx,
        int[] zeroGroupIndex,
        List<Group> zeroGroups
    ) {
        int prev = idx;
        while (prev >= 0 && zeroGroupIndex[prev] == -1) prev--;
        return prev >= 0 ? zeroGroupIndex[prev] : -10000000;
    }
}
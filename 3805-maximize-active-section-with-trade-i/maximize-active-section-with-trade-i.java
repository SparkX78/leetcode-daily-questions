class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int ones = 0;
        int prevZero = Integer.MIN_VALUE;
        int maxMerge = 0;

        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }

            int len = j - i;

            if (s.charAt(i) == '1') {
                ones += len;
            } else {
                maxMerge = Math.max(maxMerge, prevZero + len);
                prevZero = len;
            }

            i = j;
        }

        return ones + maxMerge;
    }
}
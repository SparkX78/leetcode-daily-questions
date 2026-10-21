class Solution {

    public int minOperations(String s) {
        int n = s.length();

        char[] arr = s.toCharArray();

        if (isSorted(arr)) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        char minChar = arr[0];
        char maxChar = arr[0];

        for (int i = 1; i < n; i++) {
            minChar = (char) Math.min(minChar, arr[i]);
            maxChar = (char) Math.max(maxChar, arr[i]);
        }

        if (arr[0] == minChar || arr[n - 1] == maxChar) {
            return 1;
        }

        char maxMid = arr[1];
        char minMid = arr[1];

        for (int i = 2; i < n - 1; i++) {
            maxMid = (char) Math.max(maxMid, arr[i]);
            minMid = (char) Math.min(minMid, arr[i]);
        }

        if (arr[0] > maxMid && arr[n - 1] < minMid) {
            return 3;
        }

        return 2;
    }

    private boolean isSorted(char[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }
}
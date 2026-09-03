class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;

        for (int num : nums1) {
            if (num % 2 != 0) {
                minOdd = Math.min(minOdd, num);
            } else {
                minEven = Math.min(minEven, num);
            }
        }

        // Case 1: All elements are already even
        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }

        // Case 2: Try making all elements odd
        // Every even element must be greater than the smallest odd element
        if (minEven > minOdd) {
            return true;
        }

        return false;
    }
}
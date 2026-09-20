class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;
        for(int i = 0; i < s.length(); i++){
            int reverse = 'z' - s.charAt(i) + 1;
            int index = i + 1;

            totalSum += reverse * index;
        }
        return totalSum;
    }
}
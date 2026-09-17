class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int curSum = 0;
        int n = arr.length;
        int[] minLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE);
        int  i= 0;
        int j = 0;
        int bestMin = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;
        while(j < n){
            curSum += arr[j];
            while(i < j && curSum > target){
                curSum -= arr[i++];
            }
            if(curSum == target){
                int len = j-i+1;
                if(i > 0 && minLen[i-1] != Integer.MAX_VALUE){
                    result = Math.min(result, len + minLen[i-1]);
                }
                bestMin = Math.min(bestMin, len);
            }
            minLen[j] = bestMin;
            j++;
        }
        return result == Integer.MAX_VALUE? -1 : result;
    }
}
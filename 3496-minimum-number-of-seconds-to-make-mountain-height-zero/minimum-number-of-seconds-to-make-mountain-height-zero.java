class Solution {

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {

        long low = 0;
        long high = (long)1e18;
        long ans = high;

        while(low <= high){

            long mid = low + (high - low)/2;

            if(canReduce(mid, mountainHeight, workerTimes)){
                ans = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean canReduce(long time, int height, int[] workerTimes){

        long total = 0;

        for(int t : workerTimes){

            long val = (2 * time) / t;

            long x = (long)((Math.sqrt(1 + 4 * val) - 1) / 2);

            total += x;

            if(total >= height)
                return true;
        }

        return total >= height;
    }
}
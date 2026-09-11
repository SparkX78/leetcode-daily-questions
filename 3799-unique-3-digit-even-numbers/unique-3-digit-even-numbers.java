class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for(int i : digits){
            freq[i]++;
        }
        int count = 0;
        for(int k = 100; k < 1000; k += 2){
            int d1 = k/100;
            int d2 = (k/10) % 10;
            int d3 = k % 10;

            int[] currentFreq = new int[10];
            currentFreq[d1]++;
            currentFreq[d2]++;
            currentFreq[d3]++;

            if(currentFreq[d1] <= freq[d1] && currentFreq[d2] <= freq[d2] && currentFreq[d3] <= freq[d3] ){
                count++;
            }

        }
        return count;

        
    }
}
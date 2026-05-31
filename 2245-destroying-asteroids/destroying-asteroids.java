class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long m = mass;
        for(int num : asteroids){
            if(num <= m){
                m += num;
                
            }
            else{
                return false;
            }
        }
        return true;
    }
}
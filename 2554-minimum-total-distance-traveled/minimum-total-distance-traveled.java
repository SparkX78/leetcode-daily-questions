class Solution {
    long[][] dp;
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);
        
        
        List<Integer> position = new ArrayList<>();
        for(int i = 0 ; i < factory.length; i++){
            int pos = factory[i][0];
            int limit = factory[i][1];
            for(int j = 0 ; j < limit; j++){
                position.add(pos);
            }
        }
        dp = new long[robot.size()][position.size()];
        for(long[] row : dp){
            Arrays.fill(row, -1);
        }
        return solve(0, 0, robot, position);
    }
    private long solve(int ri, int fi, List<Integer> robot, List<Integer> position){
        if(ri >= robot.size()){
            return 0;
        }
        if(fi >= position.size()){
            return (long)1e15;
        }
        if(dp[ri][fi] != -1){
            return dp[ri][fi];
        }
        long take = Math.abs(robot.get(ri) - position.get(fi)) + solve(ri+1, fi+1, robot , position);
        long skip = solve(ri, fi+1, robot, position);
        return dp[ri][fi] = Math.min(take, skip);
    }
}
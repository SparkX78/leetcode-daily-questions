class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        int landToWater = solve(
                landStartTime, landDuration,
                waterStartTime, waterDuration
        );

        int waterToLand = solve(
                waterStartTime, waterDuration,
                landStartTime, landDuration
        );

        return Math.min(landToWater, waterToLand);
    }

    private int solve(int[] firstStart, int[] firstDuration,
                      int[] secondStart, int[] secondDuration) {

        int earliestFinishFirst = Integer.MAX_VALUE;

        for (int i = 0; i < firstStart.length; i++) {
            earliestFinishFirst =
                    Math.min(earliestFinishFirst,
                             firstStart[i] + firstDuration[i]);
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < secondStart.length; i++) {
            int finishTime =
                    Math.max(earliestFinishFirst, secondStart[i])
                    + secondDuration[i];

            ans = Math.min(ans, finishTime);
        }

        return ans;
    }
}
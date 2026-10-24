class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aliceArrival = calculateDayOfYear(arriveAlice);
        int aliceLeaving = calculateDayOfYear(leaveAlice);
        int bobArrival = calculateDayOfYear(arriveBob);
        int bobLeaving = calculateDayOfYear(leaveBob);
        
        // Find the start and end of the overlapping period
        int overlapStart = Math.max(aliceArrival, bobArrival);
        int overlapEnd = Math.min(aliceLeaving, bobLeaving);
        
        // Calculate total days together, ensuring it doesn't go below 0
        return Math.max(0, overlapEnd - overlapStart + 1);
    }
    
    private int calculateDayOfYear(String date) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int totalDays = day;
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonths[i];
        }
        
        return totalDays;
    }
}
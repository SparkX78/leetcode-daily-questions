import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            if (item[0] > maxFactor) {
                maxFactor = item[0];
            }
            if (item[1] < minPrice) {
                minPrice = item[1];
            }
        }

        int[] factorCount = new int[maxFactor + 1];
        for (int[] item : items) {
            factorCount[item[0]]++;
        }

        int[] multipleCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (factorCount[f] == 0) continue;
            for (int m = f; m <= maxFactor; m += f) {
                multipleCount[f] += factorCount[m];
            }
        }

        List<int[]> boosted = new ArrayList<>();
        for (int[] item : items) {
            int f = item[0];
            int price = item[1];
            int gain = multipleCount[f] - 1;
            // Only buy boosted copies if 1 boosted copy (2 items) beats 2 cheapest copies
            if (gain > 0 && price < 2L * minPrice) {
                boosted.add(new int[]{price, gain});
            }
        }

        boosted.sort(Comparator.comparingInt(a -> a[0]));

        long remBudget = budget;
        long totalItems = 0;

        for (int[] entry : boosted) {
            long price = entry[0];
            long gain = entry[1];
            if (remBudget < price) {
                break;
            }
            long buy = Math.min(gain, remBudget / price);
            totalItems += buy * 2L;
            remBudget -= buy * price;
        }

        if (remBudget >= minPrice) {
            totalItems += remBudget / minPrice;
        }

        return (int) totalItems;
    }
}
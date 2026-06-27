import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {

        HashMap<Long, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put((long) num, freq.getOrDefault((long) num, 0) + 1);
        }

        int ans = 1;

        // Handle 1 separately
        if (freq.containsKey(1L)) {
            int cnt = freq.get(1L);
            if (cnt % 2 == 0) cnt--;
            ans = Math.max(ans, cnt);
        }

        for (long start : freq.keySet()) {

            if (start == 1) continue;

            long cur = start;
            int len = 1;

            while (true) {

                if (freq.getOrDefault(cur, 0) < 2) break;

                // Prevent overflow while squaring
                if (cur > 1000000000L) break;

                long next = cur * cur;

                if (!freq.containsKey(next)) break;

                len += 2;
                cur = next;
            }

            if (freq.getOrDefault(cur, 0) >= 2 && cur <= 1000000000L
                    && freq.containsKey(cur * cur)) {
                ans = Math.max(ans, len + 1);
            } else {
                ans = Math.max(ans, len);
            }
        }

        return ans;
    }
}
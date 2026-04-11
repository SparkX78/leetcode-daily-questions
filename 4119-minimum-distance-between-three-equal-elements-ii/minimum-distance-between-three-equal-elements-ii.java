class Solution {
    public int minimumDistance(int[] nums) {
        
        Map<Integer, List<Integer>> map = new HashMap<>();

        // store indices
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }

        int min = Integer.MAX_VALUE;

        // check each number
        for (List<Integer> list : map.values()) {
            if (list.size() >= 3) {
                for (int i = 0; i <= list.size() - 3; i++) {
                    int a = list.get(i);
                    int c = list.get(i + 2);
                    min = Math.min(min, 2 * (c - a));
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}

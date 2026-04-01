import java.util.*;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;

        // Sort indices based on their positions
        Arrays.sort(indices, (a, b) -> Integer.compare(positions[a], positions[b]));

        Deque<Integer> stack = new ArrayDeque<>();

        for (int currIndex : indices) {
            if (directions.charAt(currIndex) == 'R') {
                stack.push(currIndex);
            } else {
                // Robot moving Left
                while (!stack.isEmpty() && healths[currIndex] > 0) {
                    int topIndex = stack.peek();
                    
                    if (healths[currIndex] > healths[topIndex]) {
                        // Left robot wins
                        healths[topIndex] = 0;
                        healths[currIndex] -= 1;
                        stack.pop();
                    } else if (healths[currIndex] < healths[topIndex]) {
                        // Right robot wins
                        healths[topIndex] -= 1;
                        healths[currIndex] = 0;
                    } else {
                        // Tie: both destroyed
                        healths[topIndex] = 0;
                        healths[currIndex] = 0;
                        stack.pop();
                    }
                }
            }
        }

        // Collect survivors in original order
        List<Integer> result = new ArrayList<>();
        for (int h : healths) {
            if (h > 0) result.add(h);
        }
        return result;
    }
}
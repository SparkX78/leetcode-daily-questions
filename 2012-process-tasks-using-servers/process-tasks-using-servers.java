import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Min-heap for free servers: sorted by weight, then by index
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Min-heap for unavailable servers: sorted by free_time, then weight, then index
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[2], b[2]);
        });

        // Initialize free servers heap with {weight, index}
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[] { servers[i], i });
        }

        long time = 0;
        for (int j = 0; j < m; j++) {
            time = Math.max(time, j);

            // If no servers are free, jump time to when the next server becomes available
            if (freeServers.isEmpty() && !busyServers.isEmpty()) {
                time = Math.max(time, busyServers.peek()[0]);
            }

            // Release all servers that have finished their tasks by the current time
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= time) {
                int[] server = busyServers.poll();
                // server format: {free_time, weight, index}
                freeServers.offer(new int[] { server[1], server[2] });
            }

            // Assign the current task to the best available free server
            int[] server = freeServers.poll();
            int weight = server[0];
            int index = server[1];

            ans[j] = index;
            // Push the server into the busy heap with its new free time
            busyServers.offer(new int[] { (int) time + tasks[j], weight, index });
        }

        return ans;
    }
}
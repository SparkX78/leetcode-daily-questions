/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void makeGraph(Map<Integer, List<Integer>> adj, TreeNode curr, int parent){
        if(curr == null){
            return;
        }
        if(parent != -1){
            adj.computeIfAbsent(curr.val, k-> new ArrayList()).add(parent);
            adj.computeIfAbsent(parent, k-> new ArrayList()).add(curr.val);
        }
        makeGraph(adj, curr.left, curr.val);
        makeGraph(adj, curr.right, curr.val);

    }
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        makeGraph(adj, root, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        int time = -1;

        while(!queue.isEmpty()){
            int size = queue.size();
            time++;
            for(int i = 0; i < size; i++){
                int curr = queue.poll();
                for(int neigh : adj.getOrDefault(curr, new ArrayList<>())){
                    if(!visited.contains(neigh)){
                        queue.offer(neigh);
                        visited.add(neigh);
                    }
                }
            }
        }
        return time;
    }
}
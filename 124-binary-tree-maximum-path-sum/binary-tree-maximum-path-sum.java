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

    int max_val = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        solve(root);
        return max_val;
    }

    public int solve(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int left = Math.max(0, solve(root.left));
        int right = Math.max(0, solve(root.right));

        // Path passing through current node
        int niche_acha = left + root.val + right;

        // Path going upward through current node
        int koi_acha = Math.max(left, right) + root.val;

        // Path containing only current node
        int root_acha = root.val;

        // Update global maximum
        max_val = Math.max(
            max_val,
            Math.max(niche_acha, Math.max(koi_acha, root_acha))
        );

        // Parent can only use one side
        return Math.max(koi_acha, root_acha);
    }
}
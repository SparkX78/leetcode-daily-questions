class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // Base case: empty node
        if (root == null) {
            return false;
        }

        // Base case: leaf node
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        // Recurse on left and right subtrees with updated target sum
        int remainingSum = targetSum - root.val;
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
    }
}
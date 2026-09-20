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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        Stack<TreeNode> st = new Stack<>();
        Stack<Integer> tempVal = new Stack<>();
        st.push(root);
        tempVal.push(root.val);
        while(!st.isEmpty()){
            TreeNode node = st.pop();
            int value = tempVal.pop();
            if(node.left == null && node.right == null && value == targetSum){
                return true;
            }
            if(node.left != null){
                st.push(node.left);
                tempVal.push(value + node.left.val);
            }
            if(node.right != null){
                st.push(node.right);
                tempVal.push(value + node.right.val);
            }
        }
        return false;
    }
}
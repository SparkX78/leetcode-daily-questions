/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        StringBuilder result = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode curr = queue.poll();
            if(result.length() > 0){
                result.append(",");
            }
            if(curr == null){
                result.append("n");
            }
            else{
                result.append(curr.val);
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }
        return result.toString();
        
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.isEmpty()){
            return null;
        }
        String[] parts = data.split(",");
        int i = 1;
        TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while(!queue.isEmpty() && i < parts.length){
            TreeNode parent = queue.poll();
            if(i < parts.length && !parts[i].equals("n") ){
                TreeNode left = new TreeNode(Integer.parseInt(parts[i]));
                parent.left = left;
                queue.offer(left);
            }
            i++;
            if(i < parts.length && !parts[i].equals("n") ){
                TreeNode right = new TreeNode(Integer.parseInt(parts[i]));
                parent.right = right;
                queue.offer(right);
            }
            i++;

        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;